package dczx.axolotl.util;

import lombok.SneakyThrows;

import static org.junit.jupiter.api.Assertions.*;

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
    void testExec() {
        ExecUtil.exec("java -version", System.getProperty("user.dir"),
                output -> {
                    System.out.println("output = " + output);
                },
                errOutput -> {
                    System.out.println("errOutput = " + errOutput);
                },
                exitCode -> {
                    System.out.println("exitCode = " + exitCode);
                });
        Thread.sleep(1000);
    }
}