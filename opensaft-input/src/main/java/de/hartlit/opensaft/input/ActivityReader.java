package de.hartlit.opensaft.input;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 *
 */
public interface ActivityReader {

	void parse(InputStream inputStream) throws IOException;
	
}
