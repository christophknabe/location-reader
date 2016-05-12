package mock;

//LocationReaderDemo.java


import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;


/**Demonstriert (in einer Quelldatei) die Komposition eines LocationReaders
*  aus einem LineNumberReader und einem FileReader. */
public class LocationReaderDemo2 {

	public static void main(final String[] i_args) throws IOException {
        final String filePath = i_args.length == 0 ? "src/main/java/mock/LocationReaderDemo2.java" : i_args[0];
		_reportNonAsciiCharacter(filePath);
	}

    /**Meldet Zeichen außerhalb des ASCII-Bereichs, z.B. äöüß.*/
	private static void _reportNonAsciiCharacter(final String i_filePath) throws IOException {
		final Reader reader;
		final Location location;
		{
			final LocationReader locationReader = new LocationReader(new FileReader(i_filePath), i_filePath);
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