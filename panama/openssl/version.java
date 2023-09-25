import java.lang.foreign.MemorySegment;
import java.lang.foreign.Arena;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.AddressLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

class version { 
    void main() { 
        System.out.println("Hello, World!");
        System.loadLibrary("crypto");
        try {
            Arena arena = Arena.ofConfined();
            SymbolLookup libssl = SymbolLookup.libraryLookup("libcrypto.so", arena);
	    MemorySegment OpenSSL_versionSymbol = libssl.find("OpenSSL_version").orElseThrow();
	    MethodHandle OpenSSL_version = Linker.nativeLinker().downcallHandle(OpenSSL_versionSymbol, FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
            // 0 is OPENSSL_VERSION, 6 is OPENSSL_VERSION_STRING
            MemorySegment version = (MemorySegment) OpenSSL_version.invokeExact(0);
	    System.out.println("Hello " +  version);
            MemorySegment ptr = version.reinterpret(128); 
            String sversion = ptr.getUtf8String(0);
	    System.out.println("Hello " + sversion);
        } catch(Throwable e) {
            e.printStackTrace();
        }
    }
}

