package code;
public class Server {

	String name;
	Integer count;

	public Server(String name, Integer count) {
		super();
		this.name = name;
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "[name:" + name + " , count:" + count + "]";
	}

}
