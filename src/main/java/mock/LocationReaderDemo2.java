package mock;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Main class reporting all non-ASCII characters in the file given as command line argument.
 * Demonstrates the composition of a LocationReader from an internal LineNumberReader and an
 * imported FileReader. Demonstrates the usage of the composed LocationReader. When reading
 * characters from the Reader interface in a loop, the Location interface can easily be used to
 * report the exact location of each illegal character.
 */
public class LocationReaderDemo2 {

  public static void main(final String[] args) throws IOException {
    final String fileName =
        args.length == 0 ? "src/main/java/mock/LocationReaderDemo2.java" : args[0];
    new LocationReaderDemo2().reportNonAsciiCharacters(fileName);
  }

  /** Meldet Zeichen außerhalb des ASCII-Bereichs, z.B. äöüß. */
  void reportNonAsciiCharacters(final String fileName) throws IOException {
    final LocationReader locationReader = new LocationReader(new FileReader(fileName), fileName);
    final Reader reader = locationReader;
    final Location location = locationReader;
    for (; ; ) {
      final int code = reader.read();
      final char ch = (char) code;
      if (code < 0) {
        break;
      }
      if (code >= 128) {
        System.err.println(
            "Illegal character '"
                + ch
                + "' (code "
                + code
                + ") in file "
                + location.getSourceName()
                + " at line "
                + location.getLineNumber());
      }
    }
    reader.close();
  }
}
