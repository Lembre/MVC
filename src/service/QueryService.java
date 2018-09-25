package service;

import bean.Command;
import bean.CommandContent;
import bean.Message;
import dao.CommandDao;
import dao.MessageDao;
import util.Iconst;

import java.util.List;
import java.util.Random;

/**
 * Created by Lembre on 2018.5.28
 * 列表相关业务功能
 */
public class QueryService {
    public List<Message> queryMessageList(String command, String description){
        MessageDao messageDao = new MessageDao();
        return messageDao.queryMessageList(command, description);
    }

    /**
     * Created by Lembre on 2018.5.28
     * 通过指令查询自动回复的内容
     */
    public String queryByCommand(String command) {
        CommandDao commandDao = new CommandDao();
        List<Command> commandList;
        if(Iconst.HELP_COMMAND.equals(command)) {
            commandList = commandDao.queryCommandList(null, null);
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < commandList.size(); i++) {
                if(i != 0) {
                    result.append("<br/>");
                }
                result.append("回复[" + commandList.get(i).getName() + "]可以查看" + commandList.get(i).getDescription());
            }
            System.out.println(result.toString());
            return result.toString();
        }
        commandList = commandDao.queryCommandList(command, null);
        System.out.println(commandList.size());
        if(commandList.size() > 0) {
            List<CommandContent> contentList = commandList.get(0).getContentList();
            int i = new Random().nextInt(contentList.size());
            return contentList.get(i).getContent();
        }
        return Iconst.NO_MATCHING_CONTENT;
    }
   /*
    public String queryByCommand(String command){
        MessageDao messageDao = new MessageDao();
        List<Message> messageList;
        //但是其实这些关键代码再这里写死并不好，还是因给放到util中的接口中
        if (Iconst.HELP_COMMAND.equals(command))
        {
            messageList = messageDao.queryMessageList(null,null );
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < messageList.size(); i++) {
                if (i != 0)
                {
                    result.append("<br/>");
                }
                result.append("回复["+messageList.get(i).getCommand()+"]可以查看"+messageList.get(i).getDescription());
            }
            return result.toString();
        }

        messageList = messageDao.queryMessageList(command,null );
        if (messageList.size()>0)
        {
            return messageList.get(0).getContent();*//*如果相同指令有多条，取第一条*//*
        }
        return Iconst.NO_MATCHING_CONTENT;
    }*/
}
