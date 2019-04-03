package cn.sd.jrz.jtool.function;

import java.util.function.Supplier;

public class JrzCatch {
    private JrzCatch() {
    }

    @FunctionalInterface
    public interface JrzRunnable {
        void run() throws Exception;
    }

    public static void run(JrzRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception ignored) {
        }
    }

    public static void runThrow(JrzRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Runnable runnable(JrzRunnable runnable) {
        return () -> run(runnable);
    }

    public static Runnable runnableThrow(JrzRunnable runnable) {
        return () -> runThrow(runnable);
    }

    @FunctionalInterface
    public interface JrzSupplier<T> {
        T get() throws Exception;
    }

    public static <T> T get(JrzSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T getThrow(JrzSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Supplier<T> supplier(JrzSupplier<T> supplier) {
        return () -> get(supplier);
    }

    public static <T> Supplier<T> supplierThrow(JrzSupplier<T> supplier) {
        return () -> getThrow(supplier);
    }

    public interface JrzBooleanSupplier {
        boolean getAsBoolean() throws Exception;
    }

    public static boolean isTrue(JrzBooleanSupplier supplier) {
        try {
            return supplier.getAsBoolean();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean notTrue(JrzBooleanSupplier supplier) {
        return !isTrue(supplier);
    }
}
