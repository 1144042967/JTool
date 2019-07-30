package cn.sd.jrz.jtool.function.exception;

/**
 * @author 江荣展
 * Date: 2019-04-11
 * Time: 19:42
 */
@FunctionalInterface
public interface JRunnable {

    static void empty() {
    }

    static void run(JRunnable runnable) {
        runnable.ignoreEx().run();
    }

    static JRunnable warp(JRunnable runnable) {
        return runnable;
    }

    void run() throws Exception;

    default Runnable catchEx() {
        return () -> {
            try {
                run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    default Runnable ignoreEx() {
        return () -> {
            try {
                run();
            } catch (Exception ignored) {
            }
        };
    }

    default Runnable throwEx() {
        return () -> {
            try {
                run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}