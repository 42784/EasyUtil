package dczx.axolotl.util.file;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/25 18:28
 */
public enum FileType {
    EXECUTABLE("可执行文件"),
    TEXT("文本文件"),
    SYSTEM("依赖库"),
    DOCUMENT("文档"),
    CODE("代码"),
    CONFIG("配置文件"),
    IMAGE("图片"),
    AUDIO("音频"),
    VIDEO("视频"),
    ARCHIVE("压缩包"),
    UNKNOWN("未知文件");


    private final String description;

    FileType(String description) {
        this.description = description;
    }
}

