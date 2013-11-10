package de.hartlit.opensaft.input.hrm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PolarReader {

	public static void main(String[] args) throws IOException {
		{
//			File hrmFile = new File("testdata/06121701.hrm");
//			File hrmFile = new File("testdata/07021801.hrm");
			File hrmFile = new File("testdata/08022401.hrm");
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(hrmFile));
			HRMReader hrmReader = new HRMReader(bufferedReader);
		}
//		{
//			File pddFile = new File("data/20061217.pdd");
//			BufferedReader bufferedReader = new BufferedReader(new FileReader(pddFile));
//			PDDReader pddReader = new PDDReader(bufferedReader);
//		}
	}

}
