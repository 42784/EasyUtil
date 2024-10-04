package dczx.axolotl.util;

import lombok.Setter;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/10/4 9:32
 * <p>
 * 通过nircmd调节音量的工具库
 * 该工具仅适用于Windows
 * <p>
 * 音量可调范围
 * int : 1~100
 * float : 1~65535
 */
public class WindowsVolumeControl {
    @Setter
    private static String nircmdPath = "D:\\soundvolumeview-x64";

    public static void setVolume(int volume) {
        String command = String.format("cmd /c \"nircmd.exe setsysvolume %d\"", (int) (volume * 655.35));
        ExecUtil.exec(
                command,
                nircmdPath,
                true
        );
    }

    public static void addVolume(int volume) {
        addVolume(volume * 655.35);
    }

    public static void addVolume(double volume) {
        String command = String.format("cmd /c nircmd.exe changesysvolume %d", (int) (volume));
        ExecUtil.exec(
                command,
                nircmdPath
        );
    }

    public static void reduceVolume(int volume) {
        reduceVolume(volume * 655.35);
    }

    public static void reduceVolume(double volume) {
        String command = String.format("cmd /c nircmd.exe changesysvolume -%d", (int) (volume));
        ExecUtil.exec(
                command,
                nircmdPath
        );
    }
}
