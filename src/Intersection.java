import java.util.Objects;

public class Intersection {
	WordHolder HolderOne;
	WordHolder HolderTwo;
	Point p;
	public WordHolder getHolderOne() {
		return HolderOne;
	}
	public void setHolderOne(WordHolder holderOne) {
		HolderOne = holderOne;
	}
	public WordHolder getHolderTwo() {
		return getHolderTwo();
	}
	public void setHolderSecond(WordHolder holderTwo) {
		HolderTwo = holderTwo;
	}
	public Point getP() {
		return p;
	}
	public void setP(Point p) {
		this.p = p;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		String s="First holder  "+HolderOne+" Second holder  "+HolderTwo+" intersection point is "+p.getX()+" "+p.getY();
		return s;
	}
	@Override
	public boolean equals(Object obj) {

		if((obj!=null)&& obj instanceof Point ) {
			return Objects.equals(getHolderOne(), ((Intersection) obj).getHolderOne())
	                && Objects.equals(getHolderTwo(), ((Intersection) obj).getHolderTwo()); 
		}
		 return false;
	}
	
}
