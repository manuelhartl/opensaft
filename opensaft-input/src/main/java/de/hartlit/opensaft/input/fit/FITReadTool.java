package de.hartlit.opensaft.input.fit;

import java.io.FileInputStream;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 *
 */
public class FITReadTool {

	public static void main(String[] args) throws Exception {
		new FITReader().parse(new FileInputStream(new java.io.File("testdata/swim/fit/19891231-105716-1-1499-ANTFS-4-0.FIT")));
		new FITReader().parse(new FileInputStream(new java.io.File("testdata/settings/fit/1-1499-ANTFS-2.FIT")));
	}

}
