import java.lang.foreign.MemorySegment;
import java.lang.foreign.Arena;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.AddressLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;


public class HelloWorld {
    public static void main(String[] args) {
        try {
            Arena arena = Arena.ofConfined();
            MemorySegment nativeString = arena.allocateUtf8String("Hello World! Panama style");
            String string2 = nativeString.getUtf8String(0);
            System.out.printf("%s\n", string2);

            SymbolLookup mylib = SymbolLookup.libraryLookup("hex.so", arena);
            Linker cLinker = Linker.nativeLinker();
            MethodHandle jfcconvert = cLinker.downcallHandle(mylib.find("jfcconvert").orElseThrow(),
                        FunctionDescriptor.of(ValueLayout.JAVA_INT, AddressLayout.ADDRESS));
            int len    = (int) jfcconvert.invoke(nativeString);
            System.out.printf("%d\n", len);
            string2 = nativeString.getUtf8String(0);
            System.out.printf("%s\n", string2);
        } catch(Throwable e) {
            e.printStackTrace();
        }
    }
}
