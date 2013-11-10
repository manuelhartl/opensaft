package de.hartlit.opensaft.input.hrm.data;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 *
 */
public class InfoRow {

	private short fileVersion;
	
	private byte infoRows;
	
	private byte numRows;
	
	private byte numCols;
	
	private byte numTextRows;
	
	private short maxTextCharacters;

	public short getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(short fileVersion) {
		this.fileVersion = fileVersion;
	}

	public byte getInfoRows() {
		return infoRows;
	}

	public void setInfoRows(byte infoRows) {
		this.infoRows = infoRows;
	}

	public byte getNumRows() {
		return numRows;
	}

	public void setNumRows(byte numRows) {
		this.numRows = numRows;
	}

	public byte getNumCols() {
		return numCols;
	}

	public void setNumCols(byte numCols) {
		this.numCols = numCols;
	}

	public byte getNumTextRows() {
		return numTextRows;
	}

	public void setNumTextRows(byte numTextRows) {
		this.numTextRows = numTextRows;
	}

	public short getMaxTextCharacters() {
		return maxTextCharacters;
	}

	public void setMaxTextCharacters(short maxTextCharacters) {
		this.maxTextCharacters = maxTextCharacters;
	}
	
	
}
