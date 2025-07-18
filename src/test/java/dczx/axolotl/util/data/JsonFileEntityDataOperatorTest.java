package dczx.axolotl.util.data;

import dczx.axolotl.util.FileUtil;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/18 11:17
 */
class JsonFileEntityDataOperatorTest {

    static File file = FileUtil.keepFileExists("./test/testEntity.json");

    @org.junit.jupiter.api.Test
    public void testLoadAndSaveEntity() {
        JsonFileEntityDataOperator<TestEntity> operator = new JsonFileEntityDataOperator<>(file);

        // Load entity
        TestEntity entity = operator.loadEntity(TestEntity.class);
        assertNotNull(entity);
        assertEquals("default", entity.getName());
        assertEquals(0, entity.getAge());

        // Modify and save entity
        entity = new TestEntity("John Doe", 30);
        operator.saveEntity(entity);

        // Reload to verify changes
        TestEntity loadedEntity = operator.loadEntity(TestEntity.class);
        assertEquals("John Doe", loadedEntity.getName());
        assertEquals(30, loadedEntity.getAge());
    }

    public class TestEntity {
        private String name;
        private int age;

        public TestEntity(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

}