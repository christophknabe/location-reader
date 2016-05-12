package blackbox;

//LocationReaderDemo.java

//2005-04-06  Knabe  Created: Example for component composition.

import java.io.Reader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.IOException;


/**Main class reporting all non-ASCII characters in the file given as command line argument.
*  Demonstrates the usage of the composed LocationReader.
*  When reading characters from the LocationReader in a loop,
*  the LocationReader can easily be used to report the exact location
*  of the illegal character.
*/
public class LocationReaderDemo1 {

    public static void main(final String[] i_args) throws IOException {
        final String filePath = i_args.length == 0 ? "src/main/java/blackbox/LocationReaderDemo1.java" : i_args[0];
        _reportNonAsciiCharacter(filePath);
    }

    /**Meldet Zeichen außerhalb des ASCII-Bereichs, z.B. äöüß.*/
    private static void _reportNonAsciiCharacter(final String i_filePath) throws IOException {
        final Reader reader;
        final Location location;
        {
            final LocationReader locationReader = new LocationReader(i_filePath);
            reader = locationReader;
            location = locationReader;
        }
        for(;;){
            final int code = reader.read();
            final char ch = (char)code;
            if(code<0){break;}
            if(code>=128){
                System.err.println(
                    "Illegal character '" + ch + "' (code " + code + ") in file "
                    + location.getSourceName() + " at line " + location.getLineNumber()
                );
                break;
            }
        }
    }
}