import java.lang.foreign.MemorySegment;
import java.lang.foreign.Arena;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.AddressLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;


public class HelloWorld {
    public static void main(String[] args) {
        try {
            Arena arena = Arena.ofConfined();
            MemorySegment nativeString = arena.allocateUtf8String("Hello World! Panama style");
            String string2 = nativeString.getUtf8String(0);
            System.out.printf("%s\n", string2);

            System.loadLibrary("hex");

            SymbolLookup mylib = SymbolLookup.libraryLookup("libhex.so", arena);
            Linker cLinker = Linker.nativeLinker();

            /* get the C method we want to test */
            MethodHandle doconvert = cLinker.downcallHandle(mylib.find("doconvert").orElseThrow(),
                        FunctionDescriptor.of(ValueLayout.JAVA_INT, AddressLayout.ADDRESS));

            /* just use the C part */
            int len    = (int) doconvert.invoke(nativeString);
            string2 = nativeString.getUtf8String(0);
            System.out.printf("default callback: %s\n", string2);

            /* get the C callback we want to use */
            FunctionDescriptor desc = FunctionDescriptor.of(ValueLayout.JAVA_INT, AddressLayout.ADDRESS);
            MethodHandle set_add_cb = cLinker.downcallHandle(mylib.find("set_add_cb").orElseThrow(),
                        desc);

            /* get the java method we want to use */
            FunctionDescriptor jfcconvertdesc = FunctionDescriptor.of(ValueLayout.JAVA_INT, AddressLayout.ADDRESS);
            MethodHandle jfcconvert = MethodHandles.lookup().findStatic(Hex.class, "jfcconvert", MethodType.methodType(int.class, MemorySegment.class));

            /* set the call back to it */
            MemorySegment call = Linker.nativeLinker().upcallStub(jfcconvert, jfcconvertdesc, arena);
            set_add_cb.invoke(call);

            /* call the C routine that calls the java method */

            len    = (int) doconvert.invoke(nativeString);
            string2 = nativeString.getUtf8String(0);
            System.out.printf("java callback: %s\n", string2);
        } catch(Throwable e) {
            e.printStackTrace();
        }
    }
}
