package dczx.axolotl.util.data;

import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/18 10:50
 * <p>
 * 需要maven导入fastjson2
 */
public class JsonFileDataOperator extends AbstractFileDataOperator {
    @Getter
    protected JSONObject jsonObject;
    @Setter
    private String defaultJsonObjectText;

    public JsonFileDataOperator(File file) {
        this(file,"{}");
    }

    public JsonFileDataOperator(File file, String defaultJsonObjectText) {
        super(file);
        this.defaultJsonObjectText = defaultJsonObjectText;

        jsonObject = JSONObject.parse(text);
        if (jsonObject == null||jsonObject.toString().isEmpty()) {
            reset();
        }
    }

    @Override
    public void reset() {
        jsonObject = JSONObject.parse(defaultJsonObjectText);
        save();
    }

    @Override
    public void save() {
        text = jsonObject.toString();
        super.save();
    }
}
