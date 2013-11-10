package de.hartlit.openseft;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

import de.hartlit.opensaft.input.hrm.PDDReader;

public class PDDReaderTest {

	@Test
	public void test() throws Exception {
		PDDReader pddReader = new PDDReader(new BufferedReader(new FileReader(
				"testdata/20061217.pdd")));
		
	}

}
