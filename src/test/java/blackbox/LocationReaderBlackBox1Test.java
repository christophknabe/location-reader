package blackbox;

//LocationReaderBlackBoxTest.java

//2010-11-01  Knabe  Transformed to JUnit 4 test.
//2005-04-21  Knabe  Created: Example for component testing.

import java.io.PrintWriter;
import java.io.File;

import org.junit.Assert;
import org.junit.Test;



/**Makes a black-box test using JUnit 4, if a LocationReader, which is reading
*  from a file, gives the correct Location infos (file path, and line number).
*/
public class LocationReaderBlackBox1Test extends Assert {

    @Test
    public void location() throws Exception {
        final String name = getClass().getSimpleName();
        final String filePath = "out/" + name + ".dat";  //Depends on existence of subdirectory "out"!
        final PrintWriter writer = new PrintWriter(filePath);
        final String absFilePath = new File(filePath).getPath();
        final int lineCount = 5;
        //Write line number into its line:
        for(int lineNumber=1; lineNumber<=lineCount; lineNumber++){
            writer.println(lineNumber);
        }
        writer.close();
        //Read and check location:
        final LocationReader reader = new LocationReader(filePath);
        System.out.println("Now reading from generated testfile: " + reader.getSourceName());
        for(;;){
            final int code = reader.read();
            final char ch = (char)code;
            if(code<0){break;}
            if(Character.isDigit(ch)){
                assertEquals(""+ch, ""+reader.getLineNumber());
                assertEquals(absFilePath, reader.getSourceName());
            }
        }
        assertEquals(lineCount+1, reader.getLineNumber());
    }


}