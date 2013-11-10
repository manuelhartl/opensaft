package de.hartlit.opensaft.input.hrm.data;

import java.util.List;

import de.hartlit.opensaft.input.hrm.data.annotations.Data;
import de.hartlit.opensaft.input.hrm.data.annotations.MatrixBinding;
import de.hartlit.opensaft.input.hrm.data.annotations.PDDSectionTextnote;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 *
 */
public class ExerciseInfo implements TextRows {

	/**
	 * row1
	 */
	@MatrixBinding(row = 1, col = 1)
	public short noReport;

	@MatrixBinding(row = 1, col = 2)
	public short notEditedManually;

	@MatrixBinding(row = 1, col = 3)
	@Data(unit = Unit.METERS)
	public int distanceFromProduct;

	@MatrixBinding(row = 1, col = 4)
	@Data(unit = Unit.SECONDS_FROM_MIDNIGHT)
	public int startTime;

	@MatrixBinding(row = 1, col = 5)
	@Data(unit = Unit.SECONDS)
	public int totalTime;

	/**
	 * row2
	 */
	@MatrixBinding(row = 2, col = 0)
	public int sportID;

	@MatrixBinding(row = 2, col = 1)
	public int distanceOLD;

	@MatrixBinding(row = 2, col = 2)
	public int feeling;

	@MatrixBinding(row = 2, col = 3)
	public int recovery;

	@MatrixBinding(row = 2, col = 5)
	public int energyConsumption;

	/**
	 * row3
	 */
	@MatrixBinding(row = 3, col = 0)
	public int distance;

	@MatrixBinding(row = 3, col = 4)
	public int odometer;

	@MatrixBinding(row = 3, col = 5)
	public int ascent;

	/**
	 * row4
	 */
	@MatrixBinding(row = 4, col = 0)
	public int totalExertion;

	@MatrixBinding(row = 4, col = 1)
	public int powerAvgWithZeroValues;

	@MatrixBinding(row = 4, col = 2)
	public int vertSpeedUpMax;

	@MatrixBinding(row = 4, col = 3)
	public int vertSpeedDownMax;

	@MatrixBinding(row = 4, col = 5)
	public int vertSpeedUpAvg;

	/**
	 * row5
	 */
	@MatrixBinding(row = 5, col = 0)
	public int zone0time;

	@MatrixBinding(row = 5, col = 1)
	public int zone1time;

	@MatrixBinding(row = 5, col = 2)
	public int zone2time;

	@MatrixBinding(row = 5, col = 3)
	public int zone3time;

	@MatrixBinding(row = 5, col = 4)
	public int zone4time;

	@MatrixBinding(row = 5, col = 5)
	public int zone5time;

	/**
	 * row6
	 */
	@MatrixBinding(row = 6, col = 0)
	public int zone6time;

	@MatrixBinding(row = 6, col = 1)
	public int zone7time;

	@MatrixBinding(row = 6, col = 2)
	public int zone8time;

	@MatrixBinding(row = 6, col = 3)
	public int zone9time;

	@MatrixBinding(row = 6, col = 4)
	public int sportSpecificUnit;

	/**
	 * row7
	 */
	@MatrixBinding(row = 7, col = 0)
	public int zone0exertion;

	@MatrixBinding(row = 7, col = 1)
	public int zone1exertion;

	@MatrixBinding(row = 7, col = 2)
	public int zone2exertion;

	@MatrixBinding(row = 7, col = 3)
	public int zone3exertion;

	@MatrixBinding(row = 7, col = 4)
	public int zone4exertion;

	@MatrixBinding(row = 7, col = 5)
	public int zone5exertion;

	/**
	 * row8
	 */
	@MatrixBinding(row = 8, col = 0)
	public int zone6exertion;

	@MatrixBinding(row = 8, col = 1)
	public int zone7exertion;

	@MatrixBinding(row = 8, col = 2)
	public int zone8exertion;

	@MatrixBinding(row = 8, col = 3)
	public int zone9exertion;

	@MatrixBinding(row = 8, col = 4)
	public int recordingRate;

	@MatrixBinding(row = 8, col = 5)
	public int originalAscent;

	/**
	 * row9
	 */
	@MatrixBinding(row = 9, col = 0)
	public int hrAverage;

	@MatrixBinding(row = 9, col = 1)
	public int hrMax;

	@MatrixBinding(row = 9, col = 2)
	public int speedAverage;

	@MatrixBinding(row = 9, col = 3)
	public int speedMax;

	@MatrixBinding(row = 9, col = 4)
	public int cadenceAverage;

	@MatrixBinding(row = 9, col = 5)
	public int cadenceMax;

	/**
	 * row10
	 */
	@MatrixBinding(row = 10, col = 0)
	public int altidudeAverage;

	@MatrixBinding(row = 10, col = 1)
	public int altidudeMax;

	@MatrixBinding(row = 10, col = 2)
	public int powerAverage;

	@MatrixBinding(row = 10, col = 3)
	public int powerMax;

	@MatrixBinding(row = 10, col = 4)
	public int pedalingIndexAverage;

	@MatrixBinding(row = 10, col = 5)
	public int pedalingIndexMax;

	/**
	 * row11
	 */
	@MatrixBinding(row = 11, col = 4)
	public int slopeCount;

	@MatrixBinding(row = 11, col = 5)
	public int descent;

	/**
	 * row12
	 */
	@MatrixBinding(row = 12, col = 0)
	public int averageCaloryRate;

	@MatrixBinding(row = 12, col = 1)
	public int vertSpeedDownAvg;

	@MatrixBinding(row = 12, col = 2)
	public int beatSum;

	@MatrixBinding(row = 12, col = 3)
	public int lrBalanceAverage;

	@MatrixBinding(row = 12, col = 4)
	public int lrBalanceMax;

	@MatrixBinding(row = 12, col = 5)
	public int originalEnergyCons;

	/**
	 * row13
	 */
	@MatrixBinding(row = 13, col = 0)
	public int powerZone0time;

	@MatrixBinding(row = 13, col = 1)
	public int powerZone1time;

	@MatrixBinding(row = 13, col = 2)
	public int powerZone2time;

	@MatrixBinding(row = 13, col = 3)
	public int powerZone3time;

	@MatrixBinding(row = 13, col = 4)
	public int powerZone4time;

	@MatrixBinding(row = 13, col = 5)
	public int powerZone5time;

	/**
	 * row14
	 */
	@MatrixBinding(row = 14, col = 0)
	public int powerZone6time;

	@MatrixBinding(row = 14, col = 1)
	public int powerZone7time;

	@MatrixBinding(row = 14, col = 2)
	public int powerZone8time;

	@MatrixBinding(row = 14, col = 3)
	public int powerZone9time;

	/**
	 * row15
	 */
	@MatrixBinding(row = 15, col = 0)
	public int ascentPerHour;

	@MatrixBinding(row = 15, col = 1)
	public int exerciseRating;

	@MatrixBinding(row = 15, col = 2)
	public int memFull;

	@MatrixBinding(row = 15, col = 3)
	public int runningIndex;

	@MatrixBinding(row = 15, col = 5)
	public int inclineMax;

	/**
	 * row16
	 */
	@MatrixBinding(row = 16, col = 0)
	public int strideLengthAverage;

	@MatrixBinding(row = 16, col = 1)
	public int declineMax;

	@MatrixBinding(row = 16, col = 2)
	public int cyclingEfficiency;

	@MatrixBinding(row = 16, col = 3)
	public int footpodCalibrationFactor;

	@MatrixBinding(row = 16, col = 4)
	public int wheelSize;

	/**
	 * row17
	 */
	@MatrixBinding(row = 17, col = 0)
	public int exerciseType;

	public int exerciseID;

	public ExerciseInfo(int exerciseID) {
		this.exerciseID = exerciseID;
	}

	@PDDSectionTextnote(row = 0)
	public String exerciseName;

	@PDDSectionTextnote(row = 1)
	public String exerciseNoteText;

	@PDDSectionTextnote(row = 2)
	public String attachedHrmFile;

	@PDDSectionTextnote(row = 3)
	public String hyperlink;

	@PDDSectionTextnote(row = 4)
	public String hyperlinkInfoText;

	@PDDSectionTextnote(row = 5)
	public String attachedLocationFile;

	@PDDSectionTextnote(row = 6)
	public String attachedRRFile;

	@PDDSectionTextnote(row = 7)
	public String previousMultisportFile;

	@PDDSectionTextnote(row = 8)
	public String nextMultisportFile;

	/**
	 * text rows
	 */
	public List<String> textRows;

	@Override
	public List<String> getTextRows() {
		return textRows;
	}

	@Override
	public void setTextRows(List<String> textrows) {
		this.textRows = textrows;
	}

}
