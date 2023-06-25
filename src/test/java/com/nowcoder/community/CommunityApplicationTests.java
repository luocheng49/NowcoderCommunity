package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void testApplicationCOntext() {
        System.out.println(applicationContext);
        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());//Mybatis

        alphaDao = applicationContext.getBean("AlphaHibernate", AlphaDao.class);
        System.out.println(alphaDao.select());//Hibernate
    }

    @Test
    public void testBeanManagement(){
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
        /*
        实例化AlphaService
        初始化AlphaService
        com.nowcoder.community.service.AlphaService@4f1afe8f
        销毁AlphaService
         */
        alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
        /*
        实例化AlphaService
        初始化AlphaService
        com.nowcoder.community.service.AlphaService@45554613
        实例化AlphaService
        初始化AlphaService
        com.nowcoder.community.service.AlphaService@63e5b8aa
         */

    }

    @Test
    public void testBeanConfig(){
        SimpleDateFormat simpleDateFormat =
                applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));//2023-06-18 16:36:47
    }

    @Autowired
    @Qualifier("AlphaHibernate")
    private AlphaDao alphaDao;

    @Test
    public void testDI(){
        System.out.println(alphaDao);//com.nowcoder.community.dao.AlphaDaoMybatisImpl@3815146b
        //com.nowcoder.community.dao.AlphaDaoHibernateImpl@5c8d58ed
    }
}
