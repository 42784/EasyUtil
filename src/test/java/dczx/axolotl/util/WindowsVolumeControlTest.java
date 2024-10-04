package dczx.axolotl.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/10/4 11:06
 */
class WindowsVolumeControlTest {

    @Test
    void addVolume() {
        WindowsVolumeControl.addVolume(10);
    }

    @Test
    void reduceVolume() {
        WindowsVolumeControl.reduceVolume(10);
    }
}