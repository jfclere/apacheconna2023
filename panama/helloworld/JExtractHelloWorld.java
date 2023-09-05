import java.lang.foreign.MemorySegment;
import java.lang.foreign.Arena;
// import java.lang.foreign.MemorySession;
import static org.jfclere.hex_h.*;


public class HelloWorld {
    public static void main(String[] args) {
        try {

            // Arena arena = Arena.ofConfined();
            Arena arena = Arena.openConfined();
            MemorySegment nativeString = arena.allocateUtf8String("Hello World! Panama style");
            // MemorySession memorySession = MemorySession.openConfined();
            // MemorySegment nativeString = memorySession.allocateUtf8String("Hello World! Panama style");
            String string2 = nativeString.getUtf8String(0);
            System.out.printf("%s\n", string2);


            int len    = jfcconvert(nativeString);
            System.out.printf("%d\n", len);
            string2 = nativeString.getUtf8String(0);
            System.out.printf("%s\n", string2);
        } catch(Throwable e) {
            e.printStackTrace();
        }
    }
}
