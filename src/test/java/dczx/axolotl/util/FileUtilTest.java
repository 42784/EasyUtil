package dczx.axolotl.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/10/1 19:22
 */
class FileUtilTest {

    @Test
    void keepExists() {
        FileUtil.keepFolderExists("./test/test01/1.txt");
        FileUtil.keepFolderExists("./test/test02");
    }
}