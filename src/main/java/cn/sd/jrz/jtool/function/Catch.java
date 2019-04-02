package cn.sd.jrz.jtool.function;

import java.util.function.Function;

public class Catch {
    private Catch() {
    }

    public static void run(ThrowRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception ignored) {
        }
    }

    @FunctionalInterface
    public interface ThrowRunnable {
        void run() throws Exception;
    }

    public static Runnable runnableOf(ThrowRunnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception ignored) {
            }
        };
    }

    public interface ThrowFunction<In, Out> {
        Out apply(In input) throws Exception;
    }

    public static <In, Out> Function<In, Out> functionOf(ThrowFunction<In, Out> function) {
        return in -> {
            try {
                return function.apply(in);
            } catch (Exception e) {
                return null;
            }
        };
    }
}
