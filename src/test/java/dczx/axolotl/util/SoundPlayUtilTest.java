package dczx.axolotl.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/16 23:06
 */
class SoundPlayUtilTest {

    @Test
    @SneakyThrows
    void playerSoundA() {
        SoundPlayUtil.playerSound("./soundTest/bell.mp3");
//        Thread.sleep(1000);
    }

    @Test
    @SneakyThrows
    void playerSoundB() {
        SoundPlayUtil.playerSound(
                Files.newInputStream(Paths.get("./soundTest/bell.mp3"))
        );
//        Thread.sleep(1000);
    }
}