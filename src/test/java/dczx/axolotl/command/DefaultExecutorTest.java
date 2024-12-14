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
        DefaultExecutor defaultExecutor = new DefaultExecutor("D:\\");
        CommandResult result = defaultExecutor.runCommand("cmd /c dir");
        System.out.println("result.getOut() = " + result.getOut());
    }
}