package mock;

//2005-04-21  Knabe  Refactored with dependency injection
//2005-04-06  Knabe  Created: Example for component composition.

import java.io.Reader;
import java.io.LineNumberReader;
import java.io.IOException;

/**Reader component knowing its current location (source name and line number).
*  Exports 2 interfaces: Reader for reading from it, Location for diagnostic purposes.
*  !!Prepared for mock testing by dependency injection:
*  Imports 2 interfaces:  Reader, String sourceName.
*  The composition is done in the constructor. */
public class LocationReader extends Reader implements Location {

	private final String           _sourceName;
	private final LineNumberReader _lineNumberReader;

	/**Composes a LocationReader using a Reader as import interface,
	* and a name identifying it as a data source.	*/
	public LocationReader(final Reader reader, final String sourceName) {
		_sourceName = sourceName;
		_lineNumberReader = new LineNumberReader(reader);
	}

	//Implements for Reader
	@Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
		return _lineNumberReader.read(cbuf, off, len);
	}

	//Implements for Reader
	@Override
    public void close() throws IOException { _lineNumberReader.close(); }

	//Implements for Location
	public String getSourceName(){ return _sourceName; }

	//Implements for Location
	public int getLineNumber(){ return _lineNumberReader.getLineNumber()+1; }

}
