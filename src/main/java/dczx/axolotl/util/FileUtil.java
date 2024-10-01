package dczx.axolotl.util;

import lombok.SneakyThrows;

import java.io.File;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/10/1 19:15
 */
public class FileUtil {
    /**
     * 保证文件存在
     * 如果不存在则创建文件(及其父目录)
     *
     * @param fileName 文件名
     */
    @SneakyThrows
    public static void keepFileExists(String fileName) {
        File file = new File(fileName);
        File folder = file.getParentFile();
        if (!folder.exists() && !folder.mkdirs()) {
            folder.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * 保证文件夹存在
     * 如果不存在则创建文件夹(及其父目录)
     *
     * @param folderName 文件夹名
     */
    @SneakyThrows
    public static void keepFolderExists(String folderName) {
        File file = new File(folderName);
        File folder = file.getParentFile();
        if (!folder.exists() && !folder.mkdirs()) {
            folder.mkdirs();
        }
        if (!file.exists() && !file.mkdirs()) {
            file.mkdirs();
        }
    }
}
