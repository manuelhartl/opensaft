package de.hartlit.opensaft.input.hrm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hartlit.opensaft.common.NotSupportedException;
import de.hartlit.opensaft.input.hrm.data.InfoRow;
import de.hartlit.opensaft.input.hrm.data.TextRows;
import de.hartlit.opensaft.input.hrm.data.annotations.Data;
import de.hartlit.opensaft.input.hrm.data.annotations.MatrixBinding;
import de.hartlit.opensaft.input.hrm.data.annotations.PDDSectionTextnote;
import de.hartlit.opensaft.input.hrm.data.annotations.ParamBinding;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 *
 */
public class PolarParserUtils {

	public static final Logger logger = LoggerFactory.getLogger(PolarParserUtils.class);

	public static String getNextSection(BufferedReader bufferedReader) throws IOException {
		String buffer;
		do {
			buffer = bufferedReader.readLine();
			if (buffer == null) {
				return null;
			}
			buffer = buffer.trim();
		} while (!buffer.matches("\\[.*\\]"));
		return buffer.substring(1, buffer.length() - 1);
	}

	private static InfoRow parseInfoRow(BufferedReader bufferedReader) throws IOException {
		InfoRow infoRow = new InfoRow();
		String[] row0_inforow = bufferedReader.readLine().split("\t");
		infoRow.setFileVersion(Short.parseShort(row0_inforow[0]));
		infoRow.setInfoRows(Byte.parseByte(row0_inforow[1]));
		infoRow.setNumRows(Byte.parseByte(row0_inforow[2]));
		infoRow.setNumCols(Byte.parseByte(row0_inforow[3]));
		infoRow.setNumTextRows(Byte.parseByte(row0_inforow[4]));
		infoRow.setMaxTextCharacters(Short.parseShort(row0_inforow[5]));
		return infoRow;
	}

	public static void fillMatrixObject(Object toFill, int textNotesOffset, List<String[]> matrix) {

		Field[] fields = toFill.getClass().getFields();
		for (Field field : fields) {
			try {
				PDDSectionTextnote pddSectionTextnote = field.getAnnotation(PDDSectionTextnote.class);
				if (pddSectionTextnote != null) {
					logger.debug("found annotation " + PDDSectionTextnote.class.getSimpleName() + " on "
							+ field.getName());

					field.set(toFill, matrix.get(textNotesOffset + pddSectionTextnote.row())[0]);

				}
				MatrixBinding matrixBinding = field.getAnnotation(MatrixBinding.class);
				if (matrixBinding != null) {
					logger.debug("found annotation " + MatrixBinding.class.getSimpleName() + " on " + field.getName());
					String[] row = matrix.get(matrixBinding.row());
					String value = row[matrixBinding.col()];
					logger.debug(field.getType() + ":" + field.getName() + ":" + matrixBinding.row() + ","
							+ matrixBinding.col() + ":" + value);
					fillField(toFill, field, value);
				}
			} catch (NumberFormatException e) {
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		/**
		 * fill text rows
		 */
		if (toFill instanceof TextRows) {
			List<String> textRows = new ArrayList<String>();
			for (int i = textNotesOffset; i < matrix.size(); i++) {
				textRows.add(matrix.get(i)[0]);
			}
			((TextRows)toFill).setTextRows(textRows);
		}
	}

	private static void fillField(Object toFill, Field field, String value) throws IllegalAccessException,
			ParseException {
		if (field.getType().equals(byte.class)) {
			field.setByte(toFill, (Byte.parseByte(value)));
		} else if (field.getType().equals(String.class)) {
			field.set(toFill, value);
		} else if (field.getType().equals(int.class)) {
			field.setInt(toFill, Integer.parseInt(value));
		} else if (field.getType().equals(short.class)) {
			field.setShort(toFill, Short.parseShort(value));
		} else if (field.getType().equals(Calendar.class)) {
			Data data = field.getAnnotation(Data.class);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(data.dateformat());
			Date date = simpleDateFormat.parse(value);
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(date);
			field.set(toFill, calendar);
		} else {
			throw new RuntimeException("unknown type " + field.getType());
		}
	}

	public static void readMatrixObject(BufferedReader bufferedReader, Object toFill) throws IOException {
		InfoRow infoRow = parseInfoRow(bufferedReader);
		if (infoRow.getFileVersion() != 100) {
			throw new NotSupportedException("DayInfo section is version " + infoRow.getFileVersion());
		}
		List<String[]> matrix = parseMatrix(bufferedReader, infoRow);
		fillMatrixObject(toFill, infoRow.getInfoRows() + infoRow.getNumRows(), matrix);
	}

	public static List<String[]> parseMatrix(BufferedReader bufferedReader, InfoRow infoRow) throws IOException {
		List<String[]> result = new ArrayList<String[]>();
		result.add(null); // infoRow
		int totalRows = infoRow.getNumRows() + infoRow.getNumTextRows();
		for (int rows = 0; rows < totalRows; rows++) {
			String[] row = bufferedReader.readLine().split("\t");
			result.add(row);
		}

		return result;
	}

	public static void parseProperties(BufferedReader bufferedReader, Object toFill) throws IOException {
		Map<String,String> keyValueMap = new HashMap<String,String>();
		/**
		 * fill key value map
		 */
		String buffer;
		while (!(buffer = bufferedReader.readLine()).isEmpty()) {
			buffer = buffer.trim();
			String[] keyValue = buffer.split("=");
			keyValueMap.put(keyValue[0], keyValue[1]);
		}
		/**
		 * fill object
		 */
		Field[] fields = toFill.getClass().getFields();
		for (Field field : fields) {
			try {
				ParamBinding paramBinding = field.getAnnotation(ParamBinding.class);
				if (paramBinding != null) {
					logger.debug("found annotation " + ParamBinding.class.getSimpleName() + " on " + field.getName());
					String value = keyValueMap.get(paramBinding.name());
					logger.debug(field.getType() + ":" + field.getName() + ":" + value);
					fillField(toFill, field, value);
					keyValueMap.remove(paramBinding.name());
				}
			} catch (NumberFormatException e) {
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		if (keyValueMap.size() != 0) {
			throw new RuntimeException("values unmapped: "+keyValueMap);
		}
	}

	public static String parseLine(BufferedReader bufferedReader) throws IOException {
		return bufferedReader.readLine();
	}

}
