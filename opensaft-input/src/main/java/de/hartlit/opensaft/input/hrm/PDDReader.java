package de.hartlit.opensaft.input.hrm;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hartlit.opensaft.input.hrm.data.DailyInformation;
import de.hartlit.opensaft.input.hrm.data.ExerciseInfo;
import de.hartlit.opensaft.input.hrm.util.PolarParserUtils;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 * 
 */
public class PDDReader {

	public static final Logger logger = LoggerFactory.getLogger(PDDReader.class);

	private BufferedReader bufferedReader;

	public PDDReader(BufferedReader bufferedReader) throws IOException {
		this.bufferedReader = bufferedReader;
		while (parseNextSection()) {
		}
	}

	private boolean parseNextSection() throws IOException {
		String section = PolarParserUtils.getNextSection(bufferedReader);
		if (section == null) {
			return false;
		}
		logger.debug("found section: " + section);
		switch (section) {
		case "DayInfo": {
			DailyInformation dailyInformation = new DailyInformation();
			PolarParserUtils.readMatrixObject(bufferedReader, dailyInformation);
		}
			break;
		default: {
			Pattern pattern = Pattern.compile("(ExerciseInfo)([0-9])");
			Matcher matcher = pattern.matcher(section);
			if (matcher.matches()) {
				int exerciseID = Integer.parseInt(matcher.group(2));
				ExerciseInfo exerciseInfo = new ExerciseInfo(exerciseID);
				PolarParserUtils.readMatrixObject(bufferedReader, exerciseInfo);
			}
		}
		}
		return true;
	}
}