package cn.calendo;

import cn.calendo.service.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    public Task task;

    @Test
    void contextLoads() {
        task.task();
    }

}
