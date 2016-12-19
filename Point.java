public class Point implements Comparable<Point>{

	public int x;
	public int y;
	private int height;
	public Point (int x, int y, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
	}

	@Override
	public int compareTo(Point other) {

		if (other == null) throw new NullPointerException();

		return ((this.height) - (other.height));

	} 

	@Override
	public String toString(){
		return x + "x" + y;
	}


	@Override
	public boolean equals(Object obj){
		
		Point other;

		if (obj == null) return false;
		if (obj instanceof Point){
			other = (Point) obj;
		} else return false;

		if (other.x == this.x && other.y == this.y) return true;
		return false;
	}

	@Override
	public int hashCode() {
		return this.x * 7 + this.y;
	}

}