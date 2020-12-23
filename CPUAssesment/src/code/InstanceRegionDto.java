package code;
public class InstanceRegionDto {

	Double large;
	Double xlarge;
	Double twoLarge;
	Double fourLarge;
	Double eightLarge;
	Double tenLarge;

	public InstanceRegionDto(double d, double e, double f, double g, double h, double i) {
		super();
		this.large = d;
		this.xlarge = e;
		this.twoLarge = f;
		this.fourLarge = g;
		this.eightLarge = h;
		this.tenLarge = i;
	}

	@Override
	public String toString() {
		return "InstanceRegionDto [large=" + large + ", xlarge=" + xlarge + ", twoLarge=" + twoLarge + ", fourLarge="
				+ fourLarge + ", eightLarge=" + eightLarge + ", tenLarge=" + tenLarge + "]";
	}

	public Double getLarge() {
		return large;
	}

	public void setLarge(Double large) {
		this.large = large;
	}

	public Double getXlarge() {
		return xlarge;
	}

	public void setXlarge(Double xlarge) {
		this.xlarge = xlarge;
	}

	public Double getTwoLarge() {
		return twoLarge;
	}

	public void setTwoLarge(Double twoLarge) {
		this.twoLarge = twoLarge;
	}

	public Double getFourLarge() {
		return fourLarge;
	}

	public void setFourLarge(Double fourLarge) {
		this.fourLarge = fourLarge;
	}

	public Double getEightLarge() {
		return eightLarge;
	}

	public void setEightLarge(Double eightLarge) {
		this.eightLarge = eightLarge;
	}

	public Double getTenLarge() {
		return tenLarge;
	}

	public void setTenLarge(Double tenLarge) {
		this.tenLarge = tenLarge;
	}

}