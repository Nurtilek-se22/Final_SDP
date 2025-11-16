package utils;

import java.util.Arrays;

public class Error {
    private static final String INTERNAL_NAME = Error.class.getName();

    public static RuntimeException New() {
        return dropFrames(new RuntimeException());
    }
    public static RuntimeException New(Throwable t, String fmt, Object... args) {
        return dropFrames(new RuntimeException(String.format(fmt, args), t));
    }
    public static RuntimeException New(Throwable t, Object obj) {
        return dropFrames(new RuntimeException(obj.toString(), t));
    }
    public static RuntimeException New(Throwable t, String msg) {
        return dropFrames(new RuntimeException(msg, t));
    }
    public static RuntimeException New(String fmt, Object... args) {
        return dropFrames(new RuntimeException(String.format(fmt, args)));
    }
    public static RuntimeException New(Object obj) {
        return dropFrames(new RuntimeException(obj.toString()));
    }
    public static RuntimeException New(String msg) {
        return dropFrames(new RuntimeException(msg));
    }
    public static RuntimeException New(Throwable t) {
        return dropFrames(new RuntimeException(t));
    }
    
    public static RuntimeException TODO(String fmt, Object... args) { return New("NOT IMPLEMENTED: " + fmt, args); }
    public static RuntimeException TODO(String str) { return TODO("%s", str); }
    public static RuntimeException TODO(Object obj) { return TODO("%s", obj.toString()); }

    public static <T extends Throwable> T dropFrame(T t) {
        StackTraceElement[] stack = t.getStackTrace();
        if (stack.length > 0) {
            t.setStackTrace(Arrays.copyOfRange(stack, 1, stack.length));
        }
        return t;
    }

    private static RuntimeException dropFrames(RuntimeException e) {
        StackTraceElement[] stack = e.getStackTrace();
        stack = Arrays.stream(stack)
                .filter(el -> !el.getClassName().equals(INTERNAL_NAME))
                .toArray(StackTraceElement[]::new);
        e.setStackTrace(stack);
        return e;
    }
}
