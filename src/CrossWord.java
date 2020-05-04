import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class CrossWord {

	public static void main(String[] args) throws Exception {
		
		FillInPuzzle fp=new FillInPuzzle();
		
		FileReader fr=new FileReader(new File("input.txt"));
	
		BufferedReader br=new BufferedReader(fr);
		System.out.println(fp.loadPuzzle(br));
		fp.solve();



	}

}
