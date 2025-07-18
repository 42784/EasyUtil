package dczx.axolotl.util.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/18 10:46
 */
public abstract class AbstractFileDataOperator extends AbstractDataOperator {

    private final File file;
    protected String text;

    public AbstractFileDataOperator(File file) {
        this.file = file;
        init();
    }

    @Override
    public void load() {
        if (file == null) {
            throw new IllegalStateException("File is not initialized.");
        }
        try (FileReader reader = new FileReader(file)) {
            StringBuilder stringBuilder = new StringBuilder();
            char[] buffer = new char[1024];
            int read;
            while ((read = reader.read(buffer)) != -1) {
                stringBuilder.append(buffer, 0, read);
            }
            text = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reload() {
        load();
    }

    @Override
    public abstract void reset();

    @Override
    public void save() {
        if (file == null) {
            throw new IllegalStateException("File is not initialized.");
        }
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
