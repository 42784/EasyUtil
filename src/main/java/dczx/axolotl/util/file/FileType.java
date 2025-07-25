package dczx.axolotl.util.file;

import lombok.Getter;

import java.util.Set;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/25 18:28
 * <p>
 * 文件细分类型
 */

public enum FileType {
    // 用双枚举把“分类”与“格式”解耦

    EXECUTABLE(FileCategory.EXECUTABLE, "exe", "msi", "deb", "rpm", "appimage", "dmg", "pkg"),
    TEXT(FileCategory.TEXT, "txt", "md", "license", "log", "readme"),
    SYSTEM(FileCategory.SYSTEM, "dll", "so", "sys", "db", "drv"),
    DOCUMENT(FileCategory.DOCUMENT, "ppt", "pptx", "xls", "xlsx", "doc", "docx", "csv", "pdf"),
    OPEN_DOCUMENT(FileCategory.DOCUMENT, "odt", "ods", "odp"),

    CODE(FileCategory.CODE, "java", "cpp", "h", "py", "ts", "js", "html", "css", "bat", "vbs", "sh", "ino", "jsp", "php", "sql", "rb", "go", "rs", "c", "cc", "cxx", "hpp", "kt", "swift", "scala", "dart", "lua", "pl", "asm", "s", "r", "m", "mm"),

    CODE_Config(FileCategory.CONFIG, "groovy", "gradle", "makefile", "cmake", "dockerfile"),
    CONFIG(FileCategory.CONFIG, "yaml", "yml", "json", "xml", "xhtml", "pom", "properties", "gitignore", "cfg", "toml", "ini", "iml", "conf", "env", "htaccess", "editorconfig", "eslintignore", "prettierrc", "babelrc"),
    IMAGE(FileCategory.IMAGE, "jpg", "jpeg", "png", "gif", "webp", "svg", "bmp", "tiff", "ico", "heic", "heif", "raw", "cr2", "nef", "arw", "dng", "orf", "rw2", "psd", "ai", "eps"),
    AUDIO(FileCategory.AUDIO, "mp3", "wav", "flac", "aac", "ogg", "wma", "m4a", "opus", "aiff", "au", "mid", "midi"),
    VIDEO(FileCategory.VIDEO, "mp4", "avi", "mov", "wmv", "flv", "mkv", "webm", "m4v", "3gp", "ogv", "mpg", "mpeg", "mts", "m2ts", "vob"),
    ARCHIVE(FileCategory.ARCHIVE, "zip", "7z", "rar", "gz", "tar", "war", "jar", "apk", "xz", "bz2", "zst", "lz", "lz4", "cab", "iso", "img"),
    UNKNOWN(FileCategory.UNKNOWN);

    @Getter
    private final FileCategory category;
    @Getter
    private final Set<String> extensions;

    FileType(FileCategory category, String... extensions) {
        this.category = category;
        this.extensions = Set.of(extensions);
    }

}

