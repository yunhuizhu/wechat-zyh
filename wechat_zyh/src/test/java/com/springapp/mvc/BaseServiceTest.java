package com.springapp.mvc;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-4-28
 * Time: 上午10:51
 * To change this template use File | Settings | File Templates.
 */
import org.apache.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = {"classpath*:static/spring/applicationContext.xml"})
public class BaseServiceTest {
    Logger log = Logger.getLogger(this.getClass());
}

