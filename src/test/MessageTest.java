package test;

import bean.Message;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Lembre on 2018.5.31
 */
public class MessageTest {

    Reader reader = null;
    SqlSessionFactory sqlSessionFactory = null;
    SqlSession sqlSession = null;

    @Before
    public void init() {
        try {
            reader = Resources.getResourceAsReader("config/Configuration.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void destory() {
        sqlSession.close();
    }

    @Test
    public void  testinsertMessage(){
        Message message = new Message();
        message.setId("7");
        message.setCommand("测试");
        message.setDescription("测试");
        message.setContent("测试内容");
        sqlSession.insert("Message.insertMessageList",message);
        sqlSession.commit();
    }
}
