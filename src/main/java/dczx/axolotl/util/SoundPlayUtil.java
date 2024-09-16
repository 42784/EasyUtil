package dczx.axolotl.util;

import javazoom.jl.player.Player;
import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/16 22:56
 */
public class SoundPlayUtil {
    /**
     * 播放声音
     *
     * @param filepath 文件
     */
    @SneakyThrows
    public static void playerSound(String filepath) {
        new Thread(() -> {
            try (FileInputStream fileInputStream = new FileInputStream(filepath)) {
                Player player = new Player(fileInputStream);
                player.play();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    /**
     * 播放声音
     * @param inputStream 数据输入流
     * @param autoClose 是否关闭流
     */
        @SneakyThrows
        public static void playerSound(InputStream inputStream, boolean autoClose) {
        new Thread(() -> {
            try {
                Player player = new Player(inputStream);
                player.play();
                if (autoClose) inputStream.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @SneakyThrows
    public static void playerSound(InputStream inputStream) {
        playerSound(inputStream,true);
    }
}
