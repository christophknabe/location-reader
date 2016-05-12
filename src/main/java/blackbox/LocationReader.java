package blackbox;

import java.io.Reader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.IOException;

/**Reader component knowing its actual location (source name and line number).
*  Exports 2 interfaces: Reader for reading from it, Location for diagnostic purposes.
*  Imports 1 interface:  String for knowing the source, where to read from..
*  The composition is done in the constructor.
*/
public class LocationReader extends Reader implements Location {

    private final String           _sourceName;
    private final LineNumberReader _lineNumberReader;

    /**Composes a LocationReader using a File as import interface*/
    public LocationReader(final String i_fileName) throws IOException {
        _sourceName = new File(i_fileName).getAbsolutePath();
        _lineNumberReader = new LineNumberReader(new FileReader(i_fileName));
    }

    //Implements for Reader
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        return _lineNumberReader.read(cbuf, off, len);
    }

    //Implements for Reader
    public void close() throws IOException { _lineNumberReader.close(); }

    //Implements for Location
    public String getSourceName(){ return _sourceName; }

    //Implements for Location
    public int  getLineNumber(){ return _lineNumberReader.getLineNumber()+1; }


}
