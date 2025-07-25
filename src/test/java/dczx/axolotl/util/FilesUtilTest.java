package dczx.axolotl.util;

import dczx.axolotl.util.file.FilesUtil;
import dczx.axolotl.util.file.FolderDataNoFiles;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/23 12:50
 */
class FilesUtilTest {

    @Test
    @SneakyThrows
    void createFile() {
//        FilesUtil.createFile("./test/","testFile.txt", false);
        FilesUtil.createFile("./test/", "test/testFile.txt", false);

    }


    @Test
    @SneakyThrows
    void getFolderDataNoFiles() {
        Path path = Path.of("./target");
        FolderDataNoFiles folderData = FilesUtil.getFolderDataNoFiles(path);
        System.out.println("folderData = " + folderData);

    }




    @TempDir
    Path tempDir;
//            = Paths.get("./test/");

    private Path testFile;
    private Path testDir;
    private Path subDir;
    private Path nestedFile;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        testFile = tempDir.resolve("test.txt");
        testDir = tempDir.resolve("test-folder");
        subDir = testDir.resolve("subdir");
        nestedFile = subDir.resolve("nested.txt");


        FilesUtil.keepFolderExists(subDir);
    }

    @Test
    @DisplayName("应成功创建文件及其父目录")
    void keepFileExists_createsFileAndParents() throws IOException {
        Path target = tempDir.resolve("deep/path/test.txt");

        Path result = FilesUtil.keepFileExists(target);

        assertTrue(Files.exists(result), "文件应存在");
        assertTrue(Files.isRegularFile(result), "应为普通文件");
        assertEquals(target, result);
    }

    @Test
    @DisplayName("已存在的文件不应报错")
    void keepFileExists_existingFile() throws IOException {
        Files.createDirectories(testFile.getParent());
        Files.write(testFile, "hello".getBytes());

        Path result = FilesUtil.keepFileExists(testFile);

        assertTrue(Files.exists(testFile));
        assertEquals(testFile, result);
    }

    @Test
    @DisplayName("目标路径为已存在目录时应抛出异常")
    void keepFileExists_whenPathIsDirectory_shouldThrow() throws IOException {
        Files.createDirectory(testFile); // 创建一个同名目录

        assertThrows(IOException.class, () -> {
            FilesUtil.keepFileExists(testFile);
        });
    }

    @Test
    @DisplayName("应成功创建目录及其父目录")
    void keepFolderExists_createsDirectoryAndParents() throws IOException {
        Path target = tempDir.resolve("deep/path/folder");

        Path result = FilesUtil.keepFolderExists(target);

        assertTrue(Files.exists(result), "目录应存在");
        assertTrue(Files.isDirectory(result), "应为目录");
        assertEquals(target, result);
    }

    @Test
    @DisplayName("已存在的目录不应报错")
    void keepFolderExists_existingDirectory() throws IOException {
        Files.createDirectory(tempDir.resolve("keepFolderExists_existingDirectory"));

        Path result = FilesUtil.keepFolderExists(tempDir.resolve("keepFolderExists_existingDirectory"));

        assertTrue(Files.isDirectory(tempDir.resolve("keepFolderExists_existingDirectory")));
        assertEquals(tempDir.resolve("keepFolderExists_existingDirectory"), result);
    }

    @Test
    @DisplayName("目标路径为已存在文件时应抛出异常")
    void keepFolderExists_whenPathIsFile_shouldThrow() throws IOException {
        Files.createFile(tempDir.resolve("keepFolderExists_whenPathIsFile_shouldThrow")); // 创建一个同名文件

        assertThrows(Exception.class, () -> {
            FilesUtil.keepFolderExists(tempDir.resolve("keepFolderExists_whenPathIsFile_shouldThrow"));
        });
    }

    @Test
    @DisplayName("应成功复制单个文件")
    void copy_singleFile() throws IOException {
        Path src = tempDir.resolve("src.txt");
        Path dest = tempDir.resolve("dest.txt");
        Files.write(src, "Hello World".getBytes());

        FilesUtil.copy(src, dest);

        assertTrue(Files.exists(dest));
        assertTrue(Files.isRegularFile(dest));
        assertArrayEquals(Files.readAllBytes(src), Files.readAllBytes(dest));
    }

    @Test
    @DisplayName("应成功递归复制整个目录")
    void copy_directory() throws IOException {
        // 准备源目录结构
        Files.createDirectories(subDir);
        Files.write(nestedFile, "nested content".getBytes());

        // 复制
        Path destDir = tempDir.resolve("copied-folder");
        FilesUtil.copy(testDir, destDir);

        // 验证
        assertTrue(Files.exists(destDir));
        assertTrue(Files.exists(destDir.resolve("subdir/nested.txt")));

        List<String> lines = Files.readAllLines(destDir.resolve("subdir/nested.txt"));
        assertEquals(1, lines.size());
        assertEquals("nested content", lines.get(0));
    }

    @Test
    @DisplayName("应列出目录中所有文件（递归）")
    void listAllFiles_listsAllFilesRecursively() throws IOException {
        Files.createDirectories(subDir);
        Files.write(testFile, "data".getBytes());
        Files.write(nestedFile, "data".getBytes());

        List<Path> files = FilesUtil.listAllFiles(tempDir);
        System.out.println("files = " + files);

        assertEquals(2, files.size());
        assertTrue(files.contains(testFile));
        assertTrue(files.contains(nestedFile));
    }

    @Test
    @DisplayName("非目录路径应抛出异常")
    void listAllFiles_nonDirectory_shouldThrow() throws IOException {
        Files.createFile(testFile);

        assertThrows(IllegalArgumentException.class, () -> {
            FilesUtil.listAllFiles(testFile);
        });
    }

    @Test
    @DisplayName("应成功移动文件")
    void move_file() throws IOException {
        Path src = tempDir.resolve("source.txt");
        Path dest = tempDir.resolve("moved.txt");
        Files.write(src, "move me".getBytes());

        FilesUtil.move(src, dest);

        assertFalse(Files.exists(src), "原文件应被移动");
        assertTrue(Files.exists(dest), "目标文件应存在");
        assertArrayEquals("move me".getBytes(), Files.readAllBytes(dest));
    }

    @Test
    @DisplayName("应成功移动目录")
    void move_directory() throws IOException {
        Files.createDirectories(subDir);
        Files.write(nestedFile, "content".getBytes());

        Path dest = tempDir.resolve("moved-folder");

        FilesUtil.move(testDir, dest);

        assertFalse(Files.exists(testDir));
        assertTrue(Files.exists(dest.resolve("subdir/nested.txt")));
    }


    @Test
    @DisplayName("应正确计算文件大小")
    void calculateSize_singleFile() throws IOException {
        Files.write(testFile, "12345".getBytes()); // 5 字节

        long size = FilesUtil.calculateSize(testFile);

        assertEquals(5, size);
    }

    @Test
    @DisplayName("应正确计算目录总大小")
    void calculateSize_directory() throws IOException {
        Files.createDirectories(subDir);
        Files.write(testFile, "abc".getBytes());       // 3 字节
        Files.write(nestedFile, "123456789".getBytes()); // 9 字节

        System.out.println("testFile size: " + Files.readAttributes(testFile, BasicFileAttributes.class).size());
        System.out.println("nestedFile size: " + Files.readAttributes(nestedFile, BasicFileAttributes.class).size());

        long size = FilesUtil.calculateSize(tempDir);
        System.out.println("size = " + size);

        assertEquals(12, size);
    }


    @Test
    @DisplayName("String 重载方法 copy 应正常工作")
    void copy_stringOverload() throws IOException {
        Path src = tempDir.resolve("a.txt");
        Path dest = tempDir.resolve("b.txt");
        Files.write(src, "hello".getBytes());

        FilesUtil.copy(src.toString(), dest.toString());

        assertTrue(Files.exists(dest));
    }

    @Test
    @DisplayName("String 重载方法 calculateSize 应正常工作")
    void calculateSize_stringOverload() throws IOException {
        Files.write(testFile, "test".getBytes());

        long size = FilesUtil.calculateSize(testFile.toString());

        assertEquals(4, size);
    }
}