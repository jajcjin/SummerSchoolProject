package com.example.demo.service;

import com.example.demo.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;

/*由于方法函数的设置问题，每次使用测试类之前需要执行sql文件重置相关数据库*/

@RunWith(SpringJUnit4ClassRunner.class) //指定当前运行环境
@Transactional
@SpringBootTest     //测试类核心标记，不可缺
public class UserService_test {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserService userservice;
    @Before
    public void before() throws Exception {
        System.out.print("正在测试：");
    }
    @After
    public void after() throws Exception {
        System.out.println("测试完毕");
    }

    @Test
    public void getAllUser_test(){
        System.out.println("getAllUser方法");
        //因集合元素的具体参数在dao层其他方法的测试中有所涉及，此处只测试集合内元素个数的变化
        long number_1 = (userservice.getAllUser()).size();
        User user = new User();
        userservice.insert(user);
        long number_2 = (userservice.getAllUser()).size();
        Assert.assertEquals(number_1+1,number_2);
    }

    @Test //指定当前方法是一个单元测试方法
    public void insert_test(){
        System.out.println("insert方法");
        //通过同时测试用户集合元素个数变化和新插入的用户信息（同时调用getAllUser和getUserbyEmail方法）来达到测试insert方法的目的
        long number_1 = (userservice.getAllUser()).size();
        User user = new User();
        //model.addAttribute已经使用Setter方法注入,要求无参构造函数，故此处使用set方法赋值,后同理
        user.setUsername("Gabe Newell");
        user.setGender("男");
        user.setBirthday(new java.sql.Date(1980,1,1));
        user.setAddress("America");
        user.setEmail("gaben@valvesoftware.com");
        user.setOrg("steam");
        user.setCommunity("steam");
        user.setPasswd("steam");
        userservice.insert(user);
        userservice.givevip(user);
        long number_2 = (userservice.getAllUser()).size();
        Assert.assertEquals(number_1+1,number_2);
        Assert.assertEquals(java.util.Optional.of("Gabe Newell"),java.util.Optional.of(userservice.getUserbyEmail(user.getEmail()).getUsername()));
    }

    @Test
    public void getUserbyEmail_test(){
        System.out.println("getUserbyEmail方法");
        User user = new User();
        user.setUsername("Gabe Newell");
        user.setEmail("gaben@valvesoftware.com");
        userservice.insert(user);
        Assert.assertEquals(java.util.Optional.of("Gabe Newell"),java.util.Optional.of(userservice.getUserbyEmail(user.getEmail()).getUsername()));
    }

    @Test
    public void findUser_test(){
        System.out.println("findUser方法");
        User user = new User();
        user.setId(7);  //前置条件
        user.setUsername("Gabe Newell");
        user.setEmail("gaben@valvesoftware.com");
        user.setPasswd("steam");
        userservice.insert(user);
        userservice.givevip(user);  //findUser方法前置条件
        Assert.assertEquals(java.util.Optional.of("Gabe Newell"),java.util.Optional.of(userservice.findUser(user.getEmail(),user.getPasswd()).getUsername()));
    }

    @Test
    public void delete_test(){
        System.out.println("delete方法");
        User user = new User();
        user.setId(5);  //前置条件
        user.setUsername("Gabe Newell");
        user.setEmail("gaben@valvesoftware.com");
        user.setPasswd("steam");
        userservice.insert(user);
        long number_1 = (userservice.getAllUser()).size();
        userservice.delete(user);
        long number_2 = (userservice.getAllUser()).size();
        Assert.assertEquals(number_1-1,number_2);
    }

//    @Test
//    public void update_test(){
//        System.out.println("update方法");
//        User user = new User();
//        user.setId(3);    //前置条件
//        user.setUsername("Gabe Newell");
//        user.setGender(false);
//        user.setBirthday(new java.sql.Date(1980,1,1));
//        user.setAddress("America");
//        user.setEmail("gaben@valvesoftware.com");
//        user.setOrg("steam");
//        user.setCommunity("steam");
//        user.setPasswd("steam");
//        userservice.insert(user);
//        user.setUsername("修改测试");
//        userservice.update(user); //error
////        Assert.assertEquals(java.util.Optional.of("修改测试"),java.util.Optional.of(userservice.findUser(user.getEmail(),user.getPasswd()).getUsername()));
//    }

    @Test
    public void givevip_test(){
        System.out.println("givevip方法");
        User user = new User();
        user.setId(3);  //前置条件
        user.setUsername("Gabe Newell");
        user.setEmail("gaben@valvesoftware.com");
        user.setPasswd("steam");
        userservice.insert(user);
        Assert.assertEquals("NO",userservice.getUserbyEmail(user.getEmail()).getVip());
        userservice.givevip(user);
        Assert.assertEquals("YES",userservice.getUserbyEmail(user.getEmail()).getVip());
    }
}
