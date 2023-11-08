package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.Arena;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.AddressLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String version = getopensslversion();
        return "what: " + version + " Hello from RESTEasy Reactive";
    }
    public String getopensslversion() {
        try {
            System.loadLibrary("ssl");
	    MemorySegment OpenSSL_versionSymbol = SymbolLookup.loaderLookup().find("OpenSSL_version").orElseThrow();
	    MethodHandle OpenSSL_version = Linker.nativeLinker().downcallHandle(OpenSSL_versionSymbol, FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
            MemorySegment version = (MemorySegment) OpenSSL_version.invokeExact(0);
            MemorySegment ptr = version.reinterpret(128); 
            String sversion = ptr.getUtf8String(0);
	    return sversion;
        } catch(Throwable e) {
            e.printStackTrace();
        }
        return "je sais pas!";
    }
}
