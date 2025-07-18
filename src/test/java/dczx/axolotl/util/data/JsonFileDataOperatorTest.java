package dczx.axolotl.util.data;

import com.alibaba.fastjson2.JSONObject;
import dczx.axolotl.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/18 10:58
 */
class JsonFileDataOperatorTest {
    static File file = FileUtil.keepFileExists("./test/test.json");

    @Test
    public void testLoad() {
        JsonFileDataOperator jsonFileDataOperator = new JsonFileDataOperator(file, "{\"test\":\"default\"}");
        JSONObject jsonObject = jsonFileDataOperator.getJsonObject();
        System.out.println("data = " + jsonObject);

        jsonObject.put("test", "default2");
        jsonFileDataOperator.save();
    }


}