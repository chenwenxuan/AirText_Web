package me.airtext.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:/spring/spring.xml")
public class ISecretServiceTest {

    Logger logger = LoggerFactory.getLogger(ISecretServiceTest.class);

    @Resource
    private ISecretService secretService;

    @Test
    public void testUpdateSecret() throws Exception {
        secretService.updateSecret("嘻嘻哈哈");
    }

    @Test
    public void testSecretExists() {
        boolean exists = secretService.secretExists("嘻嘻哈");
        logger.debug("exists : "+exists);
    }
}