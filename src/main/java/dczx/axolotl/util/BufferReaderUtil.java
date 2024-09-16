package dczx.axolotl.util;

import dczx.axolotl.interfaces.ParameterRunnable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/16 22:32
 */
public class BufferReaderUtil {
    /**
     * 异步形式读取Reader内容
     * 并且执行代码块
     *
     * @param reader   读取器
     * @param runnable 代码块
     */
    public static void autoReadLineSync(BufferedReader reader, ParameterRunnable<String> runnable) {
        new Thread(() -> {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    runnable.run(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
