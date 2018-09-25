package dao;

import bean.Command;
import bean.Message;
import db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lembre on 2018.6.2
 * 与指令表对应的数据库操作
 */
public class CommandDao {
    /**
     * 根据查询条件查询指令列表
     */
    public List<Command> queryCommandList(String name, String description){
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        List<Command> commandList = new ArrayList<>();

        try {
            sqlSession = dbAccess.getSqlSession();
            Command command = new Command();
            command.setName(name);
            command.setDescription(description);
            //通过sqlSession执行SQL语句
            commandList = sqlSession.selectList("Command.queryCommandList",command);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null)
            {
                sqlSession.close();
            }
        }
        return commandList;
    }
}
