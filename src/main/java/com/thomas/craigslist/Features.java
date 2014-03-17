package craigslist;

public class Features {
	protected Double price;
	protected Double latitude;
	protected Double longitude;
	protected Integer numberOfImages;
	protected Integer lengthOfTitle;

	public Features() {
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getNumberOfImages() {
		return numberOfImages;
	}

	public void setNumberOfImages(Integer numberOfImages) {
		this.numberOfImages = numberOfImages;
	}

	public Integer getLengthOfTitle() {
		return lengthOfTitle;
	}

	public void setLengthOfTitle(Integer lengthOfTitle) {
		this.lengthOfTitle = lengthOfTitle;
	}
}
