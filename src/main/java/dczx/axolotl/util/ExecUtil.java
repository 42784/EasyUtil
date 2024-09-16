package dczx.axolotl.util;

import dczx.axolotl.interfaces.ParameterRunnable;
import lombok.SneakyThrows;

import java.io.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/16 17:24
 */
public class ExecUtil {
    /**
     * 异步运行命令
     *
     * @param command 命令
     * @param runPath 运行路径
     */
    @SneakyThrows
    public static void exec(String command, String runPath) {
        exec(command, runPath, null, null, null);
    }

    /**
     * 异步运行命令
     *
     * @param command   命令
     * @param runPath   运行路径
     * @param output    标准输出
     * @param errOutput 异常输出
     * @param exitCode  退出码
     */
    @SneakyThrows
    public static void exec(String command, String runPath,
                            ParameterRunnable<String> output,
                            ParameterRunnable<String> errOutput,
                            ParameterRunnable<Integer> exitCode) {
        Process process = Runtime.getRuntime().exec(command, null, new File(runPath));
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        //是空的就不运行了 防止性能的浪费
        if (output != null)
            BufferReaderUtil.autoReadLineSync(reader, output);

        if (errOutput != null)
            BufferReaderUtil.autoReadLineSync(errReader, errOutput);

        if (exitCode != null)
            ThreadUtil.runSync(() -> {
                try {
                    int waitFor = process.waitFor();
                    exitCode.run(waitFor);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
    }
}
