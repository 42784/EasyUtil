package dczx.axolotl.util.data;

import com.alibaba.fastjson2.JSONObject;
import dczx.axolotl.util.file.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/18 10:58
 */
class JsonFileDataOperatorTest {
    static File file = FileUtil.keepFileExists("./test/test.json");

    @Test
    public void testLoad() {
        JsonFileDataOperator jsonFileDataOperator = new JsonFileDataOperator(file);
        JSONObject jsonObject = jsonFileDataOperator.getJsonObject();
        System.out.println("data = " + jsonObject);

        jsonObject.put("test", "default2");
        jsonFileDataOperator.save();
    }


}