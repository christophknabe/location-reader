package blackbox;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

/**
 * Reader component knowing its current location (source name and line number). Exports 2
 * interfaces: Reader for reading from it, Location for diagnostic purposes. Imports 1 interface:
 * String for identifying the file, where to read from. The composition is done in the constructor.
 */
public class LocationReader extends Reader implements Location {

  private final String _sourceName;
  private final LineNumberReader _lineNumberReader;

  /** Composes a LocationReader using a file name as import interface */
  public LocationReader(final String sourceName) throws IOException {
    _sourceName = sourceName;
    _lineNumberReader = new LineNumberReader(new FileReader(_sourceName));
  }

  // Implements for Reader
  public int read(final char[] cbuf, final int off, final int len) throws IOException {
    return _lineNumberReader.read(cbuf, off, len);
  }

  // Implements for Reader
  public void close() throws IOException {
    _lineNumberReader.close();
  }

  // Implements for Location
  public String getSourceName() {
    return _sourceName;
  }

  // Implements for Location
  public int getLineNumber() {
    return _lineNumberReader.getLineNumber() + 1;
  }
}
