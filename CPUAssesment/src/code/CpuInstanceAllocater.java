package code;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CpuInstanceAllocater {

	static HashMap<String, LinkedList<Double>> dataCenter = new HashMap<>();

	public static enum CPU {
		LARGE(1), XLARGE(2), TWO_LARGE(4), FOUR_LARGE(8), EIGHT_LARGE(16), TEN_LARGE(32);

		private int value;

		private CPU(int value) {
			this.value = value;
		}
	}

	static {
// dataCenter.put("us-east", new InstanceRegionDto(0.12, 0.23, 0.45, 0.774, 1.4, 2.82));
// dataCenter.put("us-west", new InstanceRegionDto(0.14,0 , 0.413, 0.89, 1.3, 2.97));
// dataCenter.put("us-asia", new InstanceRegionDto(0.11,0 , 0.20, 0, 0.67, 1.8));

		dataCenter.put("us-east", new LinkedList<Double>(Arrays.asList(0.12, 0.23, 0.45, 0.774, 1.4, 2.82)));
		dataCenter.put("us-west", new LinkedList<Double>(Arrays.asList(0.14, 0.0, 0.413, 0.89, 1.3, 2.97)));
		dataCenter.put("us-asia", new LinkedList<Double>(Arrays.asList(0.11, 0.0, 0.20, 0.0, 0.67, 1.8)));
	}

	public List<OptimizedResults> getCost(int hours, int minCpus ,float maxCost) {

		List<CPU> processedCpu = Arrays.asList(CPU.values());

//		System.out.println("" + processedCpu.stream().map(s -> s.value + hours).toArray()[0]);
		List<Integer> li = processedCpu.stream().map(s -> s.value + hours).collect(Collectors.toList());
		List<OptimizedResults> result = findTriplets(li, minCpus ,maxCost);

		return result;
	}

	public static List<OptimizedResults> findTriplets(List<Integer> li, int sum ,float maxCost) {

		/* Sort the elements */
		Collections.sort(li);

		List<OptimizedResults> pair = new ArrayList<>();
		TreeSet<String> set = new TreeSet<String>();
//        List<Integer> triplets
//          = new ArrayList<>();

		ArrayList<Server> serverTuples = new ArrayList<>();
		Map<String, OptimizedResults> minCost = new LinkedHashMap<>();

		/*
		 * Iterate over the array from the start and consider it as the first element
		 */
		for (int i = 0; i < li.size() - 2; i++) {

			// index of the first element in the
			// remaining elements
			int j = i + 1;

			// index of the last element
			int k = li.size() - 1;

			while (j < k) {

				if (li.get(i) + li.get(j) + li.get(k) <= sum + 5 && li.get(i) + li.get(j) + li.get(k) >= sum) {

					String str = li.get(i) + ":" + li.get(j) + ":" + li.get(k);

//					System.out.println(CPU.values()[i] + "=" + (li.get(i) / CPU.values()[i].value) + "--"
//							+ CPU.values()[j] + " =" + (li.get(j) / CPU.values()[j].value) + "--" + CPU.values()[k]
//							+ "=" + (li.get(k) / CPU.values()[k].value) + "====" + str);

					if (!set.contains(str)) {
						for (Entry<String, LinkedList<Double>> region : dataCenter.entrySet()) {
							OptimizedResults result = new OptimizedResults();
							int first = (li.get(i) / CPU.values()[i].value);
							int second = (li.get(j) / CPU.values()[j].value);
							int third = (li.get(k) / CPU.values()[k].value);

							double totalCost = 0;
							// To check for the unique triplet
							serverTuples.add(new Server(CPU.values()[i].toString(), first));
							serverTuples.add(new Server(CPU.values()[j].toString(), second));
							serverTuples.add(new Server(CPU.values()[k].toString(), third));

							// Total cost for each tuple servers
							totalCost = ((first) * (24 * region.getValue().get(i)))
									+ ((second) * (24 * region.getValue().get(j)))
									+ ((third) * (24 * region.getValue().get(k)));

//							System.out.println(region.getKey() + "--reg=" + totalCost);

							result.setServers(serverTuples);
							result.setRegion(region.getKey());
							DecimalFormat df = new DecimalFormat("#.##");
							result.setTotalCost(df.format(totalCost));

//                        System.out.println(region.getKey()+"--reg="+totalCost );
//							System.out.println("---" + minCost.containsKey(region.getKey()));
							
							
							if (!minCost.containsKey(region.getKey()) && maxCost==0) {
								minCost.put(region.getKey(), result);
							} else if (maxCost == 0 && (null != minCost
									&& totalCost < Double.parseDouble(minCost.get(region.getKey()).totalCost))) {

//								System.out.println(region.getKey() + "--reg=" + totalCost);

								minCost.put(region.getKey(), result);

//								System.out.println("---" + minCost);

							}
							 else if (maxCost > totalCost && !minCost.containsKey(region.getKey())) {
//										
									minCost.put(region.getKey(), result);

								}
							
							else if (maxCost > totalCost && (null != minCost
									&& totalCost < Double.parseDouble(minCost.get(region.getKey()).totalCost))) {
//									
								minCost.put(region.getKey(), result);

							}

//							pair.add(minCost.values());
							serverTuples = new ArrayList<>();
							set.add(str);
						}
					}

					// increment the second value index
					j++;

					// decrement the third value index
					k--;
				} else if (li.get(i) + li.get(j) + li.get(k) < sum)
					j++;

				else
					k--;
			}
		}
		
		pair.addAll(minCost.values());
		return pair;
	}

	public static void main(String[] args) throws IOException {

		CpuInstanceAllocater cpu = new CpuInstanceAllocater();
		
		List<OptimizedResults> resOptimized=cpu.getCost(24, 115,0);
		
		if(resOptimized.size()>0)		
		System.out.println(resOptimized);
		else
			System.out.println("No Results for this cost/cpu");
		
		
		


	}

}
