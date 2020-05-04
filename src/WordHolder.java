import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordHolder {
	private Point start;
	public Point getStart() {
		return start;
	}
	public void setStart(Point start) {
		this.start = start;
	}
	public Point getEnd() {
		return end;
	}
	public void setEnd(Point end) {
		this.end = end;
	}
	private Point end;

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Set<String> getSetOfPossibles() {
		return SetOfPossibles;
	}
	public void setSetOfPossibles(Set<String> setOfPossibles) {
		System.out.println("Set of possible words");
		for(String s:setOfPossibles) {
			if(s.length()==getSize()) {
				SetOfPossibles.add(s);
				System.out.println(s);
			}
		}
		
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	private int size;
	private Set<String> SetOfPossibles=new HashSet<String>();
	private String direction;
	private String word;
	private List<Point> WordPath=new LinkedList<Point>();
	void makePath() {
		Point p=null;
		if(direction.equalsIgnoreCase("h")) {
			for(int i=start.getY();i<end.getY();i++) {
				p=new Point();
				p.setX(start.getX());
				p.setY(i);
				WordPath.add(p);
			}
			
		}
		else if(direction.equalsIgnoreCase("v")){
			
			for(int i=start.getX();i<end.getX();i++) {
				p=new Point();
				p.setX(i);
				p.setY(start.getY());
				WordPath.add(p);
			}
		}
		
		System.out.println(" Path generated");
		for(Point point:WordPath) {
			System.out.println(point.getX()+"   "+point.getY());
		}
	}
	public List<Point> getWordPath() {
		
		return WordPath;
	}

	public void setWordPath(List<Point> wordPath) {
		WordPath = wordPath;
	}

}
