package dczx.axolotl.util.file;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.nio.file.Path;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/25 16:22
 */
@Data
@AllArgsConstructor
public class FolderDataNoFiles {
    private Path path;
    private long size;
    private int fileCount;
    private int folderCount;
}