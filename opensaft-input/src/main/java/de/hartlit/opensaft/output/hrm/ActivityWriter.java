package de.hartlit.opensaft.output.hrm;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 * 
 */
public interface ActivityWriter {

	void write(OutputStream outputStream) throws IOException;

}
