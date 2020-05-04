import java.util.Objects;

public class Point {

	private int x;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	private int y;
	@Override
	public boolean equals(Object obj) {
		
		
		if((obj!=null)&& obj instanceof Point ) {
			return Objects.equals(getX(), ((Point) obj).getX())
	                && Objects.equals(getY(), ((Point) obj).getY()); 
		}
		 return false;
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	
	
}
