package mock;

//LocationReaderBlackBoxTest.java

//2005-04-21  Knabe  Created: Example for component testing.

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;



/**Makes a black-box test, if a LocationReader, which is reading
*  from a Reader, gives the correct Location infos (source name, and line number). */
public class LocationReaderBlackBox2Test extends Assert {

    @Test
    public void location() throws Exception {
        final String testText1Name = "testText1";  //Now independent of the file system!
        //We do not need a file system in order to test this LocationReader:
        final StringWriter testText1 = new StringWriter();
        final PrintWriter writer = new PrintWriter(testText1);
        final int lineCount = 5;
        //Write line number into its line:
        for(int lineNumber=1; lineNumber<=lineCount; lineNumber++){
            writer.println(lineNumber);
        }
        writer.close();
        //Read and check location:
        final LocationReader reader = new LocationReader(new StringReader(testText1.toString()), testText1Name);
        System.out.println("Now reading from StringReader on generated String named: " + reader.getSourceName());
        for(;;){
            final int code = reader.read();
            final char ch = (char)code;
            if(code<0){break;}
            if(Character.isDigit(ch)){
                assertEquals(""+ch, ""+reader.getLineNumber());
                assertEquals(testText1Name, reader.getSourceName());
            }
        }
        assertEquals(lineCount+1, reader.getLineNumber());
    }


}