package org.kabeja.dxf;

import junit.framework.TestCase;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by agdula on 13.10.16.
 */
public class DXFParserTest extends TestCase {

    String fileWithEncoding = "dach_1.dxf";

    public void testFallbackEncodingFailsOnToSmallBuffer() throws Exception {
        DXFParser dxfParser = new DXFParser();
        DXFParser.FALLBACK_ENCODING_DETECTION_BUFFER_SIZE_BYTES = 900;
        FileInputStream input = new FileInputStream("./samples/dxf/" + fileWithEncoding);
        try {
            dxfParser.parse(input, "");
        } catch (ParseException e) {
            assertTrue("Exception will be thrown",true);
            return;
        } finally {
            input.close();
        }
        assertTrue("Exception was not thrown when the buffer was too small",false);
    }

    public void testFallbackEncodingSucceedsWhenBufferLongEough() throws Exception {
        DXFParser dxfParser = new DXFParser();
        DXFParser.FALLBACK_ENCODING_DETECTION_BUFFER_SIZE_BYTES = 1024*1024;
        FileInputStream input = new FileInputStream("./samples/dxf/" + fileWithEncoding);
        try {
            dxfParser.parse(input, "");
        } catch (ParseException e) {
            assertTrue("Exception should not be thrown",false);
            return;
        } finally {
            input.close();
        }
        assertTrue("Exception was not thrown when the buffer was big enough",true);
    }
}
