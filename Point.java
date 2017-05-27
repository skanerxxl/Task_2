package ru.rambler.skanerxxl;

public class Point {
	private String myPointName;
	private int price = -1;
	private String pointOfDeparture;
	private boolean pointStatus;

	public Point() {
	}

	public Point(String cityPoint) {
		this.myPointName = cityPoint;
	}

	public Point(String cityPoint, int price, String cityOfDeparture, boolean pointStatus) {
		this.myPointName = cityPoint;
		this.price = price;
		this.pointOfDeparture = cityOfDeparture;
		this.pointStatus = pointStatus;
	}

	@Override
	public String toString() {
		return "myPointName=" + myPointName + ", price= " + price + ", pointOfDeparture= " + pointOfDeparture
				+ ", pointStatus= " + pointStatus;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (pointStatus ? 1231 : 1237);
		result = prime * result + ((pointOfDeparture == null) ? 0 : pointOfDeparture.hashCode());
		result = prime * result + price;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (pointStatus != other.pointStatus)
			return false;
		if (pointOfDeparture == null) {
			if (other.pointOfDeparture != null)
				return false;
		} else if (!pointOfDeparture.equals(other.pointOfDeparture))
			return false;
		if (price != other.price)
			return false;
		return true;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isPointStatus() {
		return pointStatus;
	}

	public void setPointStatus(boolean status) {
		this.pointStatus = status;
	}

	public String getPointOfDeparture() {
		return pointOfDeparture;
	}

	public void setPointOfDeparture(String cityOfDeparture) {
		this.pointOfDeparture = cityOfDeparture;
	}

	public String getMyPointName() {
		return myPointName;
	}

	public void setMyPointName(String pointName) {
		this.myPointName = pointName;
	}

}
