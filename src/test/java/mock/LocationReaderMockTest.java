package mock;

//LocationReaderMockTest.java

//2005-04-21  Knabe  Created: Example for component testing.

import java.io.Reader;

import org.junit.Assert;
import org.junit.Test;


/**Makes a white-box test, using an active mock object, if a LocationReader, which is reading
*  from a Reader, gives the correct Location infos (source name, and line number). */
public class LocationReaderMockTest extends Assert {

    @Test
    public void location() throws Exception {
        final char nl = '\n';
        final char[] testdata = {'1', nl, '2', nl, '3', nl, '4', nl, '5', nl};
        final int lineCount = testdata.length / 2;

        class MyMockReader extends Reader {

            private int callCount = 0;

            @Override
            public int read(final char[] cbuf, final int off, final int len){
                this.callCount++;
                if(this.callCount>1){
                    return -1; //meaning End-Of-File
                }
                //Called with cbuf=char[8192], off=0, len=8192
                assertEquals(0, off);
                assertEquals(8192, len);
                assertEquals(8192, cbuf.length);
                System.arraycopy(testdata, 0, cbuf, 0, testdata.length);
                return testdata.length;
            }

            @Override
            public void close(){}

        }

        //We do not need to write any content into the data source, as it is actively participating in the test!
        final MyMockReader myMockReader = new MyMockReader();        
        final String readerName = myMockReader.getClass().getSimpleName();
        
        //Read and check location:
        final LocationReader reader = new LocationReader(myMockReader, readerName);
        for(;;){
            final int code = reader.read();
            if(code<0){break;}
            final char ch = (char)code;
            if(Character.isDigit(ch)){
                assertEquals(Character.toString(ch), Integer.toString(reader.getLineNumber()));
                assertEquals(readerName, reader.getSourceName());
            }
        }
        assertEquals(lineCount+1, reader.getLineNumber());
    }

}