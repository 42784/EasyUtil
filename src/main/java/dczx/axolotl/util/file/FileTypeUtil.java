package dczx.axolotl.util.file;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/25 18:28
 */
public class FileTypeUtil {

    // 根据文件扩展名获取对应的文件类型
    public static FileType getByExtension(String extension) {
        if (extension == null || extension.isEmpty()) {
            return FileType.UNKNOWN;
        }
        return switch (extension.toLowerCase()) {
            case "exe" -> FileType.EXECUTABLE;
            case "txt", "md", "license", "log", "readme" -> FileType.TEXT;
            case "dll", "so", "sys", "db", "drv" -> FileType.SYSTEM;
            case "ppt", "pptx", "xls", "xlsx", "doc", "docx", "csv", "pdf" -> FileType.DOCUMENT;
            case "java", "cpp", "h", "py", "ts", "js", "html", "css", "bat", "vbs", "sh", "ino", "jsp", "php", "sql", "rb", "go", "rs" -> FileType.CODE;
            case "yaml", "yml", "json", "xml", "xhtml", "pom", "properties", "gitignore", "cfg", "toml", "ini", "iml", "conf" -> FileType.CONFIG;
            case "jpg", "jpeg", "png", "gif", "webp", "svg", "bmp", "tiff", "ico" -> FileType.IMAGE;
            case "mp3", "wav", "flac", "aac", "ogg", "wma" -> FileType.AUDIO;
            case "mp4", "avi", "mov", "wmv", "flv", "mkv" -> FileType.VIDEO;
            case "zip", "7z", "rar", "gz", "tar", "war", "jar", "apk" -> FileType.ARCHIVE;
            default -> FileType.UNKNOWN;
        };
    }

    /**
     * 根据文件名获取文件类型
     * @param fileName 文件名
     * @return 对应的文件类型
     */
    public static FileType getByFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return FileType.UNKNOWN;
        }
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            String extension = fileName.substring(lastDotIndex + 1);
            return getByExtension(extension);
        }
        return FileType.UNKNOWN;
    }
}
