package dczx.axolotl.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/12/14 22:18
 */
class DefaultExecutorTest {

    @Test
    void runCommand() {
        DefaultExecutor defaultExecutor = new DefaultExecutor("./");
        CommandResult result = defaultExecutor.runCommand("java -version");
        System.out.println("result.getOut() = " + result.getOut());
        System.out.println("result.getErr() = " + result.getErr());
    }
}