package dczx.axolotl.util.file;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/25 18:28
 */
public class FileTypeUtil {

    /**
     * 根据文件扩展名获取文件类型
     *
     * @param extension 文件扩展名
     * @return 对应的文件类型
     */
    public static FileType getFileTypeByExtension(String extension) {
        if (extension == null || extension.isEmpty()) {
            return FileType.UNKNOWN;
        }
        extension = extension.toLowerCase();

        for (FileType fileType : FileType.values()) {
            if (fileType.getExtensions().contains(extension)) return fileType;
        }
        return FileType.UNKNOWN;
    }

    /**
     * 根据文件名获取文件类型
     *
     * @param fileName 文件名
     * @return 对应的文件类型
     */
    public static FileType getFileTypeByFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return FileType.UNKNOWN;
        }
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            String extension = fileName.substring(lastDotIndex + 1);
            return getFileTypeByExtension(extension);
        }
        return FileType.UNKNOWN;
    }
}
