package de.hartlit.opensaft.input.hrm.data;

import java.util.Calendar;
import java.util.List;

import de.hartlit.opensaft.input.hrm.data.annotations.Data;
import de.hartlit.opensaft.input.hrm.data.annotations.MatrixBinding;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 *
 */
public class DailyInformation implements TextRows {

	/**
	 * row1
	 */
	@MatrixBinding(row = 1, col = 0)
	@Data(dateformat = "yyyymmdd")
	public Calendar day;

	@MatrixBinding(row = 1, col = 1)
	public byte numberExercises;

	@MatrixBinding(row = 1, col = 2)
	public short restingHR;

	@MatrixBinding(row = 1, col = 3)
	public short orthostaticTestHR;

	@MatrixBinding(row = 1, col = 4)
	@Data(scale = 100)
	public short weight;

	@MatrixBinding(row = 1, col = 5)
	public int sleepingSeconds;

	/**
	 * row2
	 */
	@MatrixBinding(row = 2, col = 0)
	public byte sleepingPattern;

	/**
	 * row3
	 */
	/**
	 * 1 = Travelling<br/>
	 * 2 = Sick<br/>
	 * 4 = Injured<br/>
	 * 8 = Fitness Test<br/>
	 * 16 = Massage<br/>
	 * 32 = Game / match<br/>
	 */
	@MatrixBinding(row = 3, col = 0)
	public byte dayInfoData;

	@MatrixBinding(row = 3, col = 2)
	public short polarHRmaxp;

	@MatrixBinding(row = 3, col = 3)
	public int overtrainingTestResult;

	@MatrixBinding(row = 3, col = 4)
	@Data(scale = 10)
	public int userDefinedItem1;

	@MatrixBinding(row = 3, col = 5)
	@Data(scale = 10)
	public int userDefinedItem2;

	/**
	 * row4
	 */
	@MatrixBinding(row = 4, col = 0)
	@Data(scale = 10)
	public int userDefinedItem3;

	@MatrixBinding(row = 4, col = 2)
	public int polarOwnIndex;

	@MatrixBinding(row = 4, col = 3)
	public byte weather;

	@MatrixBinding(row = 4, col = 4)
	@Data(scale = 10)
	public short temperature;

	/**
	 * row5
	 */
	@MatrixBinding(row = 4, col = 2)
	public int numberOfExercisePlans;

	/**
	 * text rows
	 */
	public List<String> textRows;

	@Override
	public String toString() {
		return "DailyInformation [day=" + day + ", numberExercises="
				+ numberExercises + ", restingHR=" + restingHR
				+ ", orthostaticTestHR=" + orthostaticTestHR + ", weight="
				+ weight + ", sleepingSeconds=" + sleepingSeconds
				+ ", sleepingPattern=" + sleepingPattern + ", dayInfoData="
				+ dayInfoData + ", polarHRmaxp=" + polarHRmaxp
				+ ", overtrainingTestResult=" + overtrainingTestResult
				+ ", userDefinedItem1=" + userDefinedItem1
				+ ", userDefinedItem2=" + userDefinedItem2
				+ ", userDefinedItem3=" + userDefinedItem3 + ", polarOwnIndex="
				+ polarOwnIndex + ", weather=" + weather + ", temperature="
				+ temperature + ", numberOfExercisePlans="
				+ numberOfExercisePlans + ", dayNotes=" + textRows + "]";
	}

	@Override
	public List<String> getTextRows() {
		return textRows;
	}

	@Override
	public void setTextRows(List<String> textrows) {
		this.textRows = textrows;
	}

}
