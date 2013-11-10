package de.hartlit.opensaft.input.hrm.data;

import java.util.Calendar;

import de.hartlit.opensaft.input.hrm.data.annotations.Data;
import de.hartlit.opensaft.input.hrm.data.annotations.ParamBinding;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 * 
 */
public class Params {

	// mode/smode positions
	private static final int positionA = 0;
	private static final int positionB = 1;
	private static final int positionC = 2;
	private static final int positionD = 3;
	private static final int positionE = 4;
	private static final int positionF = 5;
	private static final int positionG = 6;
	private static final int positionH = 7;
	private static final int positionI = 8;

	@ParamBinding(name = "Version")
	public int version = 102;

	@ParamBinding(name = "Monitor")
	public int monitor;

	@ParamBinding(name = "Mode")
	public String mode;

	@ParamBinding(name = "SMode")
	public String smode;

	@ParamBinding(name = "Date")
	@Data(dateformat = "yyyymmdd")
	public Calendar date;

	@ParamBinding(name = "StartTime")
	@Data(dateformat = "yyyymmdd")
	public String startTime;

	@ParamBinding(name = "Length")
	public String length;

	@ParamBinding(name = "Interval")
	public short inverval;

	@ParamBinding(name = "Upper1")
	public int upper1;

	@ParamBinding(name = "Lower1")
	public int lower1;

	@ParamBinding(name = "Upper2")
	public int upper2;

	@ParamBinding(name = "Lower2")
	public int lower2;

	@ParamBinding(name = "Upper3")
	public int upper3;

	@ParamBinding(name = "Lower3")
	public int lower3;

	@ParamBinding(name = "Timer1")
	public String timer1;

	@ParamBinding(name = "Timer2")
	public String timer2;

	@ParamBinding(name = "Timer3")
	public String timer3;

	@ParamBinding(name = "ActiveLimit")
	public int activeLimit;

	@ParamBinding(name = "MaxHR")
	public int maxHR;

	@ParamBinding(name = "RestHR")
	public int restHR;

	@ParamBinding(name = "StartDelay")
	public int startDelay;

	@ParamBinding(name = "VO2max")
	public int vo2max;

	@ParamBinding(name = "Weight")
	public int weight;

	public boolean hasCadence() {
		if (version <= 105) {
			return mode.charAt(positionA) == '0';
		}
		return smode.charAt(positionB) == '1'; // >=106
	}

	public boolean hasAlt() {
		if (version <= 105) {
			return mode.charAt(positionA) == '1';
		}
		return smode.charAt(positionC) == '1'; // >=106
	}

	public boolean isEuro() {
		if (version <= 105) {
			return mode.charAt(positionC) == '0';
		}
		return smode.charAt(positionH) == '0'; // >=106
	}

	public boolean onlyHR() {
		if (version <= 105) {
			return mode.charAt(positionB) == '0';
		}
		return smode.charAt(positionG) == '0'; // >=106
	}

	public boolean hasSpeed() {
		return version >= 106 ? smode.charAt(positionA) == '1' : false;
	}

	public boolean hasPower() {
		return version >= 106 ? smode.charAt(positionD) == '1' : false;
	}

	public boolean hasPowerLRBalance() {
		return version >= 106 ? smode.charAt(positionE) == '1' : false;
	}

	public boolean hasPowerPedalingIndex() {
		return version >= 106 ? smode.charAt(positionF) == '1' : false;
	}

	public boolean hasAirpressure() {
		return version >= 107 ? smode.charAt(positionI) == '1' : false;
	}

}
