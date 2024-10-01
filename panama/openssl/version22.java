import java.lang.foreign.MemorySegment;
import java.lang.foreign.Arena;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.AddressLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

public class version22 { 
    public static void main(String[] argv) { 
        try {
            System.loadLibrary("ssl");
	    MemorySegment OpenSSL_versionSymbol = SymbolLookup.loaderLookup().find("OpenSSL_version").orElseThrow();
	    MethodHandle OpenSSL_version = Linker.nativeLinker().downcallHandle(OpenSSL_versionSymbol, FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
            MemorySegment version = (MemorySegment) OpenSSL_version.invokeExact(0);
            MemorySegment ptr = version.reinterpret(128); 
            String sversion = ptr.getString(0);
	    System.out.println("Hello " + sversion);
        } catch(Throwable e) {
            e.printStackTrace();
        }
    }
}

