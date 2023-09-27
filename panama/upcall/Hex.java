/*
 * convert a string to uppercase or lower case
 */
import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;

public class  Hex {
   public static String jfcconvert(String string) {
       return string.toUpperCase();
   }
   public static int jfcconvert(MemorySegment mem) {
       MemorySegment ptr = mem.reinterpret(128);
       String string = ptr.getUtf8String(0);
       string = jfcconvert(string);
       // put the string back in mem...
       ptr.setUtf8String(0, string);
       return 0;
   }
}
