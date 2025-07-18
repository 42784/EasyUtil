package dczx.axolotl.util.data;

import com.alibaba.fastjson2.JSONObject;

import java.io.File;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/18 11:09
 */
public class JsonFileEntityDataOperator<T> extends JsonFileDataOperator {
    private T tempEntity;
    public JsonFileEntityDataOperator(File file, String defaultJsonObjectText) {
        super(file, defaultJsonObjectText);
    }

    public JsonFileEntityDataOperator(File file) {
        super(file);
    }

    public T loadEntity(Class<T> entityClass) {
        load();
       tempEntity = jsonObject.toJavaObject(entityClass);
        return tempEntity;
    }

    public T getEntity() {
        return tempEntity;
    }


    public void saveEntity(T entity) {
        jsonObject = JSONObject.parseObject(JSONObject.toJSONString(entity));
        save();
    }
}
