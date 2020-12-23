package code;
import java.util.ArrayList;

public class OptimizedResults {
	String region;
	String totalCost;
	ArrayList<Server> servers;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String string) {
		this.totalCost = string;
	}

	public ArrayList<Server> getServers() {
		return servers;
	}

	public void setServers(ArrayList<Server> servers) {
		this.servers = servers;
	}

	@Override
	public String toString() {
		return "OptimizedResults [region=" + region + ", totalCost=" + totalCost + ", servers=" + servers + "]";
	}

}