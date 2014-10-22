package me.airtext.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath*:/spring/spring.xml","classpath*:/mybatis/mybatis-config.xml","classpath*:/mybatis/mybatis-dao.xml"})
public class ISecretServiceTest {

    @Resource
    private ISecretService secretService;

    @Test
    public void testUpdateSecret() throws Exception {
        secretService.updateSecret("嘻嘻哈哈");
    }
}