package dczx.axolotl.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/10/1 19:22
 */
class FileUtilTest {

    @Test
    void keepExists() {
        FileUtil.keepFileExists("./test/test01/1.txt");
        FileUtil.keepFolderExists("./test/test02");
    }

    @Test
    void listAllFiles() {
        List<File> files = FileUtil.listAllFiles(new File("./"));
        System.out.println("files = " + files);

    }
    @Test
    void calculateSize() {
        long size = FileUtil.calculateSize("./");
        System.out.printf("size = %d B\n", size);
        System.out.printf("size = %.2f KB\n", size/1024.0);
        System.out.printf("size = %.2f MB\n", size/1024.0/1024);
    }
    @Test
    void move() {
        FileUtil.move("./test/test01/","./test/test02");
    }
    @Test
    void copy() {
        FileUtil.copy("./test/test02/","./test/test01");
    }
}