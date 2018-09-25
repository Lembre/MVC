package service;

import dao.MessageDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lembre on 2018.6.1
 * 维护相关的业务功能（新增，删除等）
 */
public class MaintainService {
    /**
     * 单条删除
     */
    public void deleteOne(String id){
        if (id != null && !"".equals(id.trim()));
        {
            MessageDao messageDao = new MessageDao();
            messageDao.deleteOne(Integer.valueOf(id));
        }
    }

    /**
     * 批量删除
     */
    public void deleteBatch(String[] ids){
        MessageDao messageDao = new MessageDao();
        List<Integer> idlist = new ArrayList<>();
        for (String id : ids)
        {
            idlist.add(Integer.valueOf(id));
        }
        messageDao.deleteBatch(idlist);
    }
}
