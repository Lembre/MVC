package dao;

import bean.Message;
import db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * Created by Lembre on 2018.5.31
 * 和Message表相关的数据库操作
 */
public class MessageDao {
    /**
     * 根据查询条件查询消息列表
     */
    public List<Message> queryMessageList(String command, String description){
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        List<Message> messageList = new ArrayList<>();

        try {
            sqlSession = dbAccess.getSqlSession();
            Message message = new Message();
            message.setCommand(command);
            message.setDescription(description);
            //通过sqlSession执行SQL语句
            messageList = sqlSession.selectList("Message.queryMessageList",message);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null)
            {
                sqlSession.close();
            }
        }
        return messageList;
    }

    /**
     * 根据ID进行单条删除
     */
    public void deleteOne(int id){
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;

        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.delete("Message.deleteOne",id);
            sqlSession.commit();
            /*增删改与查询不一样，MyBatis查询默认有事务提交，但增删改需要手动提交事务*/

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null)
            {
                sqlSession.close();
            }
        }
    }

    /**
     * 根据ID进行批量删除
     */
    public void deleteBatch(List<Integer> ids){
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;

        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.delete("Message.deleteBatch",ids);
            sqlSession.commit();
            /*增删改与查询不一样，MyBatis查询默认有事务提交，但增删改需要手动提交事务*/

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null)
            {
                sqlSession.close();
            }
        }
    }

    public static void main(String[] args) {
        MessageDao messageDao = new MessageDao();
        messageDao.queryMessageList("" , "" );
        Map<String,Message> messageMap = new HashMap<String, Message>();
        messageMap.put("key",new Message());
        Logger log;
       /* log.debug("Lembre");*/
       /* log.debug("abc");
        log.info(message);
        log.warn(message);
        log.error(message);*/
    }
   /* public List<Message> queryMessageList(String command, String description){
        List<Message> messageList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "root", "123456");
            StringBuilder sql = new StringBuilder("select ID, COMMAND, DESCRIPTION, CONTENT from MESSAGE where 1 = 1") ;
            List<String> paramList = new ArrayList<String>();//缓冲,添加动态传参
            if (command != null && !"".equals(command.trim()))
            {
                sql.append( " and COMMAND = ?");
                paramList.add(command);
            }
            if (description != null && !"".equals(description.trim()))
            {
                sql.append( " and DESCRIPTION like '%' ? '%'");
                paramList.add(description);
            }
            PreparedStatement statement = conn.prepareStatement(sql.toString());
            for (int i = 0; i < paramList.size(); i++) {
                statement.setString(i+1, paramList.get(i));
            }
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                Message message = new Message();
                //先放和后放是一样的，因为容器里面放的都是引用，不是真正的对象本身，所以接下来对这个对象操作依然有效
                messageList.add(message);
                message.setId(rs.getString("ID"));
                message.setCommand(rs.getString("COMMAND"));
                message.setDescription(rs.getString("DESCRIPTION"));
                message.setContent(rs.getString("CONTENT"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }*/
}
