package de.hartlit.opensaft.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayDeque;
import java.util.Deque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hartlit.opensaft.input.ActivityReader;
import de.hartlit.opensaft.input.ActivityReaderEvent;
import de.hartlit.opensaft.input.fit.FITReader;
import de.hartlit.opensaft.model.Scope;
import de.hartlit.opensaft.output.hrm.HRMWriter;

public class Transform {

	private final static Logger logger = LoggerFactory
			.getLogger(Transform.class);

	private static class Transformer implements ActivityReaderEvent {

		private Deque<Scope> currentScope = new ArrayDeque<Scope>();

		@Override
		public void startedScope(Scope scope) {
			logger.debug("started scope " + scope);
			currentScope.push(scope);
		}

		@Override
		public void receivedData() {
			logger.debug("got data in scope " + currentScope.peek());
		}

		@Override
		public void finishedScope() {
			Scope finishedScope = currentScope.pop();
			logger.debug("finsihed scope " + finishedScope);
		}

	}

	public static void main(String[] args) throws Exception {
		Transformer transformer = new Transformer();
		// new FITReader(new FileInputStream(new
		// File("testdata/swim/fit/19891231-105716-1-1499-ANTFS-4-0.FIT")));
		ActivityReader activityReader = new FITReader();
		activityReader.parse(new FileInputStream(new File(
				"testdata/running/fit/2013-11-09-17-18-41.fit")), transformer);
		new HRMWriter().write(new FileOutputStream(new File("output.hrm")));

	}

}
