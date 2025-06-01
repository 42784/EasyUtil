package dczx.axolotl.util;

import dczx.axolotl.obsolete.CommandResult;
import dczx.axolotl.obsolete.DefaultExecutor;
import dczx.axolotl.command.ExecUtil;
import lombok.SneakyThrows;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/16 22:05
 */
class ExecUtilTest {

    @org.junit.jupiter.api.Test
    void exec() {
//        ExecUtil.exec("java", System.getProperty("user.dir"), br -> {
//
//        });
    }

    @org.junit.jupiter.api.Test
    @SneakyThrows
    void simpleExec() {
        ExecUtil.exec("java -version", System.getProperty("user.dir"),true,
                output -> System.out.println("output = " + output),
                errOutput -> System.out.println("errOutput = " + errOutput),
                exitCode -> System.out.println("exitCode = " + exitCode));
    }
    @org.junit.jupiter.api.Test
    @SneakyThrows
    void simpleExec2() {
        DefaultExecutor defaultExecutor = new DefaultExecutor("./");
        CommandResult commandResult = defaultExecutor.runCommand("java -version");
        System.out.println("commandResult = " + commandResult);
    }
}