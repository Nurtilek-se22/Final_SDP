package utils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public abstract class Logger {
    public static final Logger
        info = new PrintInfo(),
        warn = new PrintWarn(),
        err  = new PrintError();

    private Logger() {}

    public static void outWrite(OutputStream out, String str) {
        try {
            out.write(str.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.err.println(String.format("Couldn't write into %s stream because:", out.toString()));
            throw new RuntimeException(e);
        }
    }

    public abstract void osprintf(OutputStream out, String fmt, Object... args);
    public void osprintf(String fmt, Object... args) { osprintf(System.out, fmt, args); }
    public void osprintf(OutputStream out, String str) { osprintf(out, "%s", str); }
    public void osprintf(String str) { osprintf(System.out, "%s", str); }
    public void osprintf(OutputStream out, Object obj) { osprintf(out, "%s", obj.toString()); }
    public void osprintf(Object obj) { osprintf(System.out, "%s", obj.toString()); }
    
    public void osprintfn(OutputStream out, String fmt, Object... args) { osprintf(out, fmt + "\r\n", args); }
    public void osprintfn(String fmt, Object... args) { osprintfn(System.out, fmt, args); }
    public void osprintfn(OutputStream out, String str) { osprintfn(out, "%s", str); }
    public void osprintfn(String str) { osprintfn(System.out, "%s", str); }
    public void osprintfn(OutputStream out, Object obj) { osprintfn(out, "%s", obj.toString()); }
    public void osprintfn(Object obj) { osprintfn(System.out, "%s", obj.toString()); }

    public void printf(String fmt, Object... args) { osprintf(System.out, fmt, args); }
    public void printf(String str) { printf("%s", str); }
    public void printf(Object obj) { printf("%s", obj.toString()); }

    public void printfn(String fmt, Object... args) { osprintfn(System.out, fmt, args); }
    public void printfn(String str) { printfn("%s", str); }
    public void printfn(Object obj) { printfn("%s", obj.toString()); }

    public void eprintf(String fmt, Object... args) { osprintf(System.err, fmt, args); }
    public void eprintf(String str) { eprintf("%s", str); }
    public void eprintf(Object obj) { eprintf("%s", obj.toString()); }

    public void eprintfn(String fmt, Object... args) { osprintfn(System.err, fmt, args); }
    public void eprintfn(String str) { eprintfn("%s", str); }
    public void eprintfn(Object obj) { eprintfn("%s", obj.toString()); }

    private static class PrintError extends Logger {
        @Override public void osprintf(OutputStream out, String fmt, Object... args) {
            Logger.outWrite(out, String.format("ERROR: " + fmt, args));
        }
    }
    
    private static class PrintInfo extends Logger {
        @Override public void osprintf(OutputStream out, String fmt, Object... args) {
            Logger.outWrite(out, String.format("INFO: " + fmt, args));
        }
    }
    
    private static class PrintWarn extends Logger {
        @Override public void osprintf(OutputStream out, String fmt, Object... args) {
            Logger.outWrite(out, String.format("WARN: " + fmt, args));
        }
    }
}
