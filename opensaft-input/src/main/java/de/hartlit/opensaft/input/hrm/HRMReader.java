package de.hartlit.opensaft.input.hrm;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hartlit.opensaft.input.hrm.data.Coach;
import de.hartlit.opensaft.input.hrm.data.InfoRow;
import de.hartlit.opensaft.input.hrm.data.IntTimes;
import de.hartlit.opensaft.input.hrm.data.Note;
import de.hartlit.opensaft.input.hrm.data.Params;
import de.hartlit.opensaft.input.hrm.util.PolarParserUtils;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 * 
 */
public class HRMReader {

	public static final Logger logger = LoggerFactory.getLogger(HRMReader.class);

	public HRMReader(BufferedReader bufferedReader) throws IOException {
		parseSections(bufferedReader);
	}

	private static void parseSections(BufferedReader bufferedReader) throws IOException {
		Params params = null;
		while (true) {
			String section = PolarParserUtils.getNextSection(bufferedReader);
			if (section == null) {
				return;
			}
			logger.debug("found section: " + section);
			switch (section) {
			case "Params":
				params = new Params();
				PolarParserUtils.parseProperties(bufferedReader, params);
				break;
			case "Coach":
				Coach coach = new Coach(); // TODO
				PolarParserUtils.parseProperties(bufferedReader, coach);
				break;
			case "Note":
				Note note = new Note();
				note.note = PolarParserUtils.parseLine(bufferedReader);
				break;
			case "IntTimes":
				IntTimes intTimes = new IntTimes();
				InfoRow infoRow = new InfoRow();
				infoRow.setNumRows((byte) 5);
				infoRow.setNumCols((byte) 5);
				List<String[]> matrix = PolarParserUtils.parseMatrix(bufferedReader, infoRow);
				PolarParserUtils.fillMatrixObject(intTimes, infoRow.getInfoRows() + infoRow.getNumRows(), matrix);
				break;
			case "HRData":
				handleHRdata(bufferedReader, params);
				break;
			}
		}
	}

	private static void handleHRdata(BufferedReader bufferedReader, Params params) throws IOException {
		String buffer;
		while ((buffer = bufferedReader.readLine()) != null) {
			buffer = buffer.trim();
			int heartRate;
			int speed = -1;
			int cadence = -1;
			int alt = -1;
			int power = -1;
			int powerLRBalance = -1;
			int powerPedalingIndex = -1;
			int airPressure = -1;
			String[] values = buffer.split("\t");
			if (params.onlyHR()) {
				heartRate = Integer.parseInt(values[0]);
			} else if (params.version <= 105) {
				heartRate = Integer.parseInt(values[0]);
				speed = Integer.parseInt(values[1]); // scale 10
				if (params.hasCadence()) {
					cadence = Integer.parseInt(values[2]);
				}
				if (params.hasAlt()) {
					alt = Integer.parseInt(values[2]);
					if (params.version <= 102) {
						alt *= 10;
					}
				}
			} else {
				int position = 0;
				heartRate = Integer.parseInt(values[position++]);
				if (params.hasSpeed()) {
					speed = Integer.parseInt(values[position++]);
				}
				if (params.hasCadence()) {
					cadence = Integer.parseInt(values[position++]);
				}
				if (params.hasAlt()) {
					alt = Integer.parseInt(values[position++]);
				}
				if (params.hasPower()) {
					power = Integer.parseInt(values[position++]);
				}
				if (params.hasPowerLRBalance()) {
					int combinedPowerValue = Integer.parseInt(values[position++]);
					powerLRBalance = combinedPowerValue & 0x00ff;
					powerPedalingIndex = (combinedPowerValue & 0xff00) >> 8;
				}
				if (params.hasAirpressure()) {
					airPressure = Integer.parseInt(values[position++]);
				}
			}

			System.out.print("HR=" + heartRate);
			if (params.hasSpeed()) {
				System.out.print(" Speed=" + speed);
			}
			if (params.hasCadence()) {
				System.out.print(" cadence=" + cadence);
			}
			if (params.hasAlt()) {
				System.out.print(" alt=" + alt);
			}
			if (params.hasPower()) {
				System.out.print(" power=" + power);
			}
			if (params.hasPowerLRBalance()) {
				System.out.print(" PowerLRBalance=" + powerLRBalance);
			}
			if (params.hasPowerPedalingIndex()) {
				System.out.print(" PowerPedalingIndex=" + powerPedalingIndex);
			}
			if (params.hasAirpressure()) {
				System.out.print(" airpressur=" + airPressure);
			}
			System.out.println();
		}
	}
}
