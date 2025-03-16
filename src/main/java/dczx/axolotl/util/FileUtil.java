package dczx.axolotl.util;

import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/10/1 19:15
 */
public class FileUtil {
    /**
     * 确保指定文件存在，不存在则创建（包括父目录）
     * @param fileName 文件名
     * @return 文件对象
     */
    @SneakyThrows
    public static File keepFileExists(String fileName) {
        File file = new File(fileName);
        File parent = file.getParentFile();

        // 创建父目录（如果必要）
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IOException("无法创建父目录: " + parent.getAbsolutePath());
        }

        // 创建文件（如果不存在）
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("无法创建文件: " + file.getAbsolutePath());
        }
        return file;
    }

    /**
     * 确保指定目录存在，不存在则创建（包括父目录）
     * @param folderName 目录名
     * @return 目录对象
     */
    @SneakyThrows
    public static File keepFolderExists(String folderName) {
        File folder = new File(folderName);
        File parent = folder.getParentFile();

        // 创建父目录（如果必要）
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IOException("无法创建父目录: " + parent.getAbsolutePath());
        }

        // 创建目录或检查类型
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new IOException("无法创建目录: " + folder.getAbsolutePath());
            }
        } else if (!folder.isDirectory()) {
            throw new IOException("路径已存在但不是目录: " + folder.getAbsolutePath());
        }
        return folder;
    }

    /**
     * 复制文件或目录
     * @param source 源路径
     * @param target 目标路径
     */
    @SneakyThrows
    public static void copy(String source, String target) {
        Path src = Paths.get(source);
        Path dest = Paths.get(target);
        if (Files.isDirectory(src)) {
            Files.walkFileTree(src, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetDir = dest.resolve(src.relativize(dir));
                    Files.createDirectories(targetDir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.copy(file, dest.resolve(src.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * 获取目录下所有文件（递归）
     * @param dir 目录
     * @return 文件列表
     */
    @SneakyThrows
    public static List<File> listAllFiles(File dir) {
        List<File> fileList = new ArrayList<>();
        Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                fileList.add(file.toFile());
                return FileVisitResult.CONTINUE;
            }
        });
        return fileList;
    }

    /**
     * 移动文件或目录
     * @param source 源路径
     * @param target 目标路径
     */
    @SneakyThrows
    public static void move(String source, String target) {
        Files.move(Paths.get(source), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 计算文件或目录大小（字节）
     * @param path 路径
     * @return 大小（字节）
     */
    @SneakyThrows
    public static long calculateSize(String path) {
        final long[] size = {0};
        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                size[0] += attrs.size();
                return FileVisitResult.CONTINUE;
            }
        });
        return size[0];
    }

}
