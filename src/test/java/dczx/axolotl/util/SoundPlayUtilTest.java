package dczx.axolotl.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/16 23:06
 */
class SoundPlayUtilTest {

    @Test
    @SneakyThrows
    void playerSound() {
        SoundPlayUtil.playerSound("E:\\AxolotlXM工作间\\【编程项目】班级程序\\LessonToolbox(旧)\\LessonToolbox项目\\抽奖音效.mp3");
//        Thread.sleep(3000);
    }
    @Test
    @SneakyThrows
    void playerSound2() {
        SoundPlayUtil.playerSound(
                new FileInputStream("E:\\AxolotlXM工作间\\【编程项目】班级程序\\LessonToolbox(旧)\\LessonToolbox项目\\抽奖音效.mp3")
        );
//        Thread.sleep(3000);
    }
}