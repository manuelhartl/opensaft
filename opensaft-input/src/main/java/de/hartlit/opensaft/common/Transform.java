package de.hartlit.opensaft.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import de.hartlit.opensaft.input.fit.FITReader;
import de.hartlit.opensaft.output.hrm.HRMWriter;

public class Transform {

	public static void main(String[] args) throws Exception {

//		new FITReader(new FileInputStream(new File("testdata/swim/fit/19891231-105716-1-1499-ANTFS-4-0.FIT")));
		new FITReader(new FileInputStream(new File("testdata/running/fit/2013-11-09-17-18-41.fit")));

		new HRMWriter(new FileOutputStream(new File("output.hrm")));
		
	}

}
