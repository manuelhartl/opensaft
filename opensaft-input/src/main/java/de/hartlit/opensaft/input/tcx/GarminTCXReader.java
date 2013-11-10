package de.hartlit.opensaft.input.tcx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GarminTCXReader {

	public static void main(String[] args) throws IOException, Exception {
		File tcxFile = new File("testdata/2013-11-07-193007.TCX");
		
		TCXReader tcxReader = new TCXReader(new FileInputStream(tcxFile));
	}

}
