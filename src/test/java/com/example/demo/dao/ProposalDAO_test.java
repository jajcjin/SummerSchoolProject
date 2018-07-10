package com.example.demo.dao;

import com.example.demo.entity.Proposal;
import com.example.demo.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/*由于方法函数的设置问题，每次使用测试类之前需要执行sql文件重置相关数据库*/

@RunWith(SpringJUnit4ClassRunner.class) //指定当前运行环境
@Transactional
@SpringBootTest     //测试类核心标记，不可缺
public class ProposalDAO_test {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProposalDAO proposaldao;
    @Before
    public void before() throws Exception {
        System.out.print("正在测试：");
    }
    @After
    public void after() throws Exception {
        System.out.println("测试完毕");
    }

    @Test
    public void getAllProposal_test(){
        System.out.println("getAllProposal方法");
        //前置条件：insert方法测试正确
        //因集合元素的具体参数在dao层其他方法的测试中有所涉及，此处只测试集合内元素个数的变化
        long number_1 = (proposaldao.getAllproposal()).size();
        Proposal proposal = new Proposal();
        User user = new User(); //用于proposal的insert方法
        proposaldao.insert(proposal,user);
        long number_2 = (proposaldao.getAllproposal()).size();
        Assert.assertEquals(number_1+1,number_2);
    }

    @Test
    public void getPassproposal_test(){
        System.out.println("getPassProposal方法");
        long number_1 = (proposaldao.getPassproposal()).size();
        Proposal proposal = new Proposal();
        int id = 10; //标注在编译器中测试的顺序，用于集合类信息获取
        proposal.setPpid(id);    //pass方法前置条件
        User user = new User(); //用于proposal的insert方法
        proposaldao.insert(proposal,user);
        long number_2 = (proposaldao.getPassproposal()).size();
        Assert.assertNotEquals(number_1+1,number_2);
        proposaldao.pass(proposal);
        long number_3 = (proposaldao.getPassproposal()).size();
        Assert.assertEquals(number_1+1,number_3);
    }

    @Test
    public void insert_test(){
        System.out.println("insert方法");
        Proposal proposal = new Proposal();
        User user = new User();
        long number_1 = (proposaldao.getAllproposal()).size();
        //model.addAttribute已经使用Setter方法注入,要求无参构造函数，故此处使用set方法赋值,后同理
        proposal.setAuthor("阿杰");
        proposal.setPpname("如果早知道");
        proposal.setPpcontent("好康，是新游戏哦");
        proposaldao.insert(proposal,user);
        long number_2 = (proposaldao.getAllproposal()).size();
        Assert.assertEquals(number_1+1,number_2);
        int id = 11; //标注在编译器中测试的顺序，用于集合类信息获取
        Assert.assertEquals("如果早知道",proposaldao.getAllproposal().get(6).getPpname());
    }

    @Test
    public void delete_test(){
        System.out.println("delete方法");
        Proposal proposal = new Proposal();
        proposal.setPpid(7);    //前置条件
        User user = new User();
        proposaldao.insert(proposal,user);
        long number_1 = (proposaldao.getAllproposal()).size();
        proposaldao.delete(proposal);
        long number_2 = (proposaldao.getAllproposal()).size();
        Assert.assertEquals(number_1-1,number_2);
    }

//    @Test
//    public void update_test(){
//        System.out.println("update方法");
//        Proposal proposal = new Proposal();
//        int id = 7;
//        proposal.setPpid(id);    //前置条件
//        User user = new User();
//        proposaldao.insert(proposal,user);
//        proposal.setPpname("修改测试");
//        proposaldao.update(proposal); //error
////        Assert.assertEquals(java.util.Optional.of("修改测试"),java.util.Optional.of(proposaldao.getAllproposal().get(id-1).getPpname()));
//    }

    @Test
    public void pass_test(){
        System.out.println("pass方法");
        Proposal proposal = new Proposal();
        int id = 8; //标注在编译器中测试的顺序，用于集合类信息获取
        proposal.setPpid(id);    //前置条件
        User user = new User();
        proposaldao.insert(proposal,user);
        Assert.assertEquals("NOT PASS",proposaldao.getAllproposal().get(6).getStatus());
        proposaldao.pass(proposal);
        Assert.assertEquals("PASS",proposaldao.getAllproposal().get(6).getStatus());
    }

    @Test
    public void vote_test(){
        System.out.println("vote方法");
        Proposal proposal = new Proposal();
        int id = 12; //标注在编译器中测试的顺序，用于集合类信息获取
        proposal.setPpid(id);    //前置条件
        User user = new User();
        proposaldao.insert(proposal,user);
        Assert.assertEquals("0",proposaldao.getAllproposal().get(6).getVote());
        proposaldao.vote(proposal);
        Assert.assertEquals("1",proposaldao.getAllproposal().get(6).getVote());
    }

    @Test
    public void disvote_test(){
        System.out.println("disvote方法");
        Proposal proposal = new Proposal();
        int id = 9; //标注在编译器中测试的顺序，用于集合类信息获取
        proposal.setPpid(id);    //前置条件
        User user = new User();
        proposaldao.insert(proposal,user);
        Assert.assertEquals("0",proposaldao.getAllproposal().get(6).getDisvote());
        proposaldao.disvote(proposal);
        Assert.assertEquals("1",proposaldao.getAllproposal().get(6).getDisvote());
    }

}