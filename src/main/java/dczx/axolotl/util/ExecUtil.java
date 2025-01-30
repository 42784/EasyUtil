package dczx.axolotl.util;

import dczx.axolotl.interfaces.ParameterRunnable;
import lombok.SneakyThrows;
import sun.nio.ch.ThreadPool;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/16 17:24
 */
public class ExecUtil {
    /**
     * 同步运行命令
     *
     * @param command 命令
     * @param runPath 运行路径
     */
    @SneakyThrows
    public static void exec(String command, String runPath) {
        exec(command, runPath, true);
    }

    /**
     * 异步运行命令
     *
     * @param command 命令
     * @param runPath 运行路径
     * @param isBlank 是否阻塞主线程
     */
    @SneakyThrows
    public static void exec(String command, String runPath, boolean isBlank) {
        exec(command, runPath, isBlank, null, null, null);
    }


    /**
     * 异步运行命令
     *
     * @param command   命令
     * @param runPath   运行路径
     * @param isBlock   是否阻塞指令执行
     * @param output    标准输出
     * @param errOutput 异常输出
     * @param exitCode  退出码
     */
    @SneakyThrows
    public static void exec(String command, String runPath, boolean isBlock,
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

        if (exitCode != null) {
            Runnable runnable = () -> {
                try {
                    int waitFor = process.waitFor();
                    exitCode.run(waitFor);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };

            if (isBlock)//是否阻塞线程去执行
                runnable.run();
            else
                new Thread(runnable).start();
        }
    }

    /**
     * 异步运行命令
     *
     * @param command         命令
     * @param runPath         运行路径
     * @param executorService 线程池
     */
    @SneakyThrows
    public static void exec(String command, String runPath, ExecutorService executorService) {
        executorService.submit(() -> exec(command, runPath, true));
    }

    /**
     * 异步运行命令
     *
     * @param command         命令
     * @param runPath         运行路径
     * @param executorService 线程池
     */
    @SneakyThrows
    public static void exec(String command, String runPath, ExecutorService executorService,
                            ParameterRunnable<String> output,
                            ParameterRunnable<String> errOutput,
                            ParameterRunnable<Integer> exitCode) {
        executorService.submit(() -> exec(command, runPath, true, output, errOutput, exitCode));
    }

}
