package de.hartlit.opensaft.input;

import de.hartlit.opensaft.model.Scope;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 *
 */
public interface ActivityReaderEvent {

	void startedScope(Scope scope);
	
	void receivedData();
	
	void finishedScope();
	
}
