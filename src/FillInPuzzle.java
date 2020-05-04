import java.io.BufferedReader;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

public class FillInPuzzle {
	
	List<WordHolder> visited =new ArrayList<WordHolder>();
	static int s=0;
	Stack<WordHolder> tempholders=null;
	List<Intersection> visitedintersection=new ArrayList<Intersection>();
	List<WordHolder> WordHolders=new ArrayList<WordHolder>();
    List<Intersection> Intersections  =new ArrayList<Intersection>();
    WordHolder wh=null;
    Point p=null;
	private char grid [][];
	public char[][] getGrid() {
		return grid;
	}

	public void setGrid(char[][] grid) {
		this.grid = grid;
	}

	private Set<String> stringset=new HashSet <String>();
	
	Boolean loadPuzzle(BufferedReader br) {
		boolean flag=true;
		
		String IndexLine;
		int i=0;
		int NoOfColumns = 0;
		int NoOfRows=0;
		int NoOfWord = 0;
		int StringRead=0;
		try {
			IndexLine=br.readLine();
			while(IndexLine!=null) {
				 if (IndexLine.isEmpty()) {
					 if(i==0) {
						 flag=false;
					 }
				        break;
				    }
				 else if(i==0) {
					 i++;
					String line[]=IndexLine.split(" ");
					NoOfColumns=Integer.parseInt(line[0]);
					NoOfRows=Integer.parseInt(line[1]);
				    NoOfWord=Integer.parseInt(line[2]);
					grid=new char[NoOfRows][NoOfColumns];
					for (char[] array : grid) {
			            Arrays.fill(array, '*');
			        }
					/*for (char[] array : grid) {
			            System.out.println(array);
			        }*/
					IndexLine=br.readLine();
				}
				else {
					if(i-1<NoOfWord) {
						i++;
						String line[]=IndexLine.split(" ");
						int column=Integer.parseInt(line[0]);
						int row=NoOfRows-Integer.parseInt(line[1])-1;
						int length=Integer.parseInt(line[2]);
						String direction=line[3];
						
						if(direction.equalsIgnoreCase("h")) {
							
							wh=new WordHolder();
							p=new Point();
							p.setX(row);
							p.setY(column);
							wh.setStart(p);
							wh.setDirection(direction);
							wh.setSize(length);
							
							int j;
							for(j=column;j<column+length;j++) {
								
									grid[row][j]='+';	
								
							}
							p=new Point();
							p.setX(row);
							p.setY(j);
							wh.setEnd(p);
							WordHolders.add(wh);
							
						}
						else if(direction.equalsIgnoreCase("v")) {
							
							wh=new WordHolder();
							p=new Point();
							p.setX(row);
							p.setY(column);
							wh.setStart(p);
							wh.setSize(length);
							wh.setDirection(direction);
							int j;
							for(j=row;j<row+length;j++) {
								
									grid[j][column]='+';
								
								
							}
							p=new Point();
							p.setX(j);
							p.setY(column);
							wh.setEnd(p);
							WordHolders.add(wh);
							
						}
						else {
							throw new InvalidAlgorithmParameterException("The directional is given wrong");
							
						}
						
						
						IndexLine=br.readLine();
					}
					else {
						if(StringRead<NoOfWord) {
							stringset.add(IndexLine);
							StringRead++;
						}
						IndexLine=br.readLine();
					}
				}
				
				
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			flag=false;
		}
		catch(InvalidAlgorithmParameterException e) {
			flag=false;
		}
		catch(IOException e) {
			flag=false;
		}	
		if(flag==true) {
			assignWords();
			makePath();
			System.out.println("number of word holders are:");
			for(WordHolder w:WordHolders) {
				System.out.println(w);
			}
			findIntersections();
		}
		return flag;
		
	}

	private void makePath() {
		
		for(WordHolder w:WordHolders) {
			w.makePath();
		}
	}

	private void assignWords() {	
		System.out.println("Given Strings are:");
		for(WordHolder w:WordHolders) {
			w.setSetOfPossibles(stringset);
		}
		
	}
	public boolean solve() {
		
		
		tempholders=new Stack<WordHolder>();
		for(WordHolder w:WordHolders) {
			
			tempholders.push(w);
			
		}
		
		do {
			
			WordHolder w=tempholders.pop();
			fitWords(w);
			
		}while(tempholders.isEmpty());
		
		
		System.out.println("Sunil Sunny");
		
		for(WordHolder w:visited) {
			
			System.out.println(w+"  filled with "+w.getWord());
			
		}
		
		
		
		return false;
		
	}
	private boolean fitWords(WordHolder wh) {
		
System.out.println(s);
s++;
				List<Intersection> intersections=null;
				tempholders.pop();
				if(!visited.contains(wh)) {
					visited.add(wh);
					
				for(String s:wh.getSetOfPossibles()) {
						wh.setWord(s);
						intersections=getIntersections(wh);
						System.out.println(intersections);
						for(Intersection in:intersections) {
							
							if(!visitedintersection.contains(in)) {
								visitedintersection.add(in);
								
								//System.out.println("second is"+in.getHolderTwo());
								System.out.println("First holder is"+in.getHolderOne());
								System.out.println("second is"+in.getHolderTwo());
								int index=wh.getWordPath().indexOf(in.getP());
								char comparing=s.charAt(index);
							
								WordHolder test=null;
								if(Objects.equals(wh, in.getHolderOne())) {
									System.out.println("fgfhj");
									test=in.getHolderTwo();
								}
								else {
									test=in.getHolderOne();
								}
								for(String st:test.getSetOfPossibles()) {
									
									
									int indextwo=test.getWordPath().indexOf(in.getP());
									char comparingTwo=s.charAt(indextwo);
									if(comparing==comparingTwo) {
										
										System.out.println("Fitted"+s+" between  "+wh.toString()+"  and "+test.toString());
										test.setWord(st);
										
										fitWords(test);
										return true;
									}
									else {
										return false;
									}
									
								}
								
								
							}
						
							
						}
					
					
				}
				
				}
				return false;	
	}

	

	private List<Intersection> getIntersections(WordHolder connection) {
		
		List<Intersection> intersections=new ArrayList<Intersection>();
		
		for(Intersection in:Intersections) {
			
			if((Objects.equals(connection, in.HolderOne))||(Objects.equals(connection, in.HolderTwo))) {
				intersections.add(in);
			}
			
		}
		
		
		return intersections;
	}

	private void findIntersections() {

		
		for(int k=0;k<WordHolders.size();k++) {
			System.out.println("------------------------------------");
			List <Point> first=WordHolders.get(k).getWordPath();
			/*System.out.println("First place header points:");
			for(Point point:WordHolders.get(k).getWordPath()) {
				System.out.println(point.getX()+"  "+point.getY());
			}*/
			for(int l=k+1;l<WordHolders.size();l++) {
				
				List<Point> CPoint=new LinkedList<Point>(first);
			
			
				List<Point> second=WordHolders.get(l).getWordPath();
				/*System.out.println("Second header points");
				 for(Point point:second) {
					System.out.println(point.getX()+"  "+point.getY());
				}*/
				CPoint.retainAll(second);
				/*System.out.println("Intersection points:");
				for(Point point:CPoint) {
					System.out.println(point.getX()+"  "+point.getY());
				}*/
				if(CPoint.size()==1) {
					Intersection CommonPoint=new Intersection();
					CommonPoint.setHolderOne(WordHolders.get(k));
					CommonPoint.setHolderSecond(WordHolders.get(l));
					Point PointOfIntersection=CPoint.iterator().next();
					CommonPoint.setP(PointOfIntersection);
					Intersections.add(CommonPoint);
				}
				else if(CPoint.size()==0) {
					System.out.println("No intersection point");
				}
				else {
					System.out.println("here is no case like this");
				}
				
			}

			
		}
		System.out.println("Printing Intersections");
		for(Intersection intersect:Intersections) {
			System.out.println(intersect.toString());
		}  
		
	}

}
