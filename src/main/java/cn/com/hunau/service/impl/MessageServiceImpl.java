package cn.com.hunau.service.impl;

import cn.com.hunau.dao.ArticleDAO;
import cn.com.hunau.dao.CommentDAO;
import cn.com.hunau.dao.DAOFactory;
import cn.com.hunau.dao.UserDAO;
import cn.com.hunau.po.ArticlePo;
import cn.com.hunau.po.CommentPo;
import cn.com.hunau.po.UserPo;
import cn.com.hunau.service.MessageService;
import cn.com.hunau.vo.ArticleVo;
import cn.com.hunau.vo.MessageVo;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MessageServiceImpl implements MessageService {
    @Override
    public List<MessageVo> findMessageByUser(int user_id) {
        List<MessageVo> messageVoList = new ArrayList<MessageVo>();
        //得到该用户发布的文章
        ArticleDAO articleDAO = DAOFactory.buildDAOFactory().createArticleDAO();
        List<ArticlePo> ArticleList = articleDAO.searchMyArticle(user_id, 1, 200);
        //得到该用户参与过的评论
        CommentDAO commentDAO = DAOFactory.buildDAOFactory().createCommentDAO();
        UserDAO userDAO = DAOFactory.buildDAOFactory().createUserDAO();
        UserPo user = userDAO.findUserioByUser_id(user_id);
        List<CommentPo> commentsByUser = commentDAO.findCommentsByUser(user_id);
        for (CommentPo commentpo : commentsByUser
        ) {
            //得到该文章的信息
            ArticlePo articlePo = articleDAO.findArticleByid(commentpo.getArticle_id());
            //得到文章的作者
            UserPo userPo = userDAO.findUserioByUser_id(articlePo.getUser_id());
            ArticleVo articleVo = new ArticleVo();
            articleVo.setUser_name(userPo.getUser_name());
            articleVo.setArticle_cover(articlePo.getArticle_cover());
            articleVo.setArticle_date(articlePo.getArticle_date());
            articleVo.setArticle_id(articlePo.getArticle_id());
            articleVo.setArticle_private(articlePo.getArticle_private());
            articleVo.setArticle_title(articlePo.getArticle_title());
            articleVo.setArticle_type(articlePo.getArticle_type());
            articleVo.setArticle_viewcount(articlePo.getArticle_viewcount());
            articleVo.setUser_id(articlePo.getUser_id());
            //对应文章的最新评论时间
            Timestamp time = commentDAO.findCommentsByTime(commentpo.getArticle_id());
            if (time == null) {
                time = articlePo.getArticle_date();
            }
            MessageVo messageVo = new MessageVo();
            messageVo.setTheLastTime(time);
            messageVo.setYear(transfromTimeToYear(time));
            messageVo.setMonth(transfromTimeToMonth(time));
            messageVo.setDay(transfromTimeToDay(time));
            messageVo.setMin(transfromTimeToMin(time));
            messageVo.setAboutMeArticles(articleVo);
            if (user.getUser_lastLogin().getTime() < time.getTime()) {
//                System.out.println("isNew");
                messageVo.setNewMessage("isNew");
            }
            messageVoList.add(messageVo);
        }
        for (ArticlePo po : ArticleList
        ) {
            MessageVo messageVo = new MessageVo();
//            messageVo.setAboutMeArticles(po);
            //得到文章的作者
            UserPo userPo = userDAO.findUserioByUser_id(po.getUser_id());

            ArticleVo articleVo = new ArticleVo();
            articleVo.setUser_name(userPo.getUser_name());
            articleVo.setArticle_cover(po.getArticle_cover());
            articleVo.setArticle_date(po.getArticle_date());
            articleVo.setArticle_id(po.getArticle_id());
            articleVo.setArticle_private(po.getArticle_private());
            articleVo.setArticle_title(po.getArticle_title());
            articleVo.setArticle_type(po.getArticle_type());
            articleVo.setArticle_viewcount(po.getArticle_viewcount());
            articleVo.setUser_id(po.getUser_id());

            //对应文章的最新评论时间
            Timestamp time = commentDAO.findCommentsByTime(po.getArticle_id());
            if (time == null) {
                time = po.getArticle_date();
            }
            if (user.getUser_lastLogin().getTime() < time.getTime()) {
//                System.out.println("isNew");
                messageVo.setNewMessage("isNew");
            }
            messageVo.setTheLastTime(time);
            messageVo.setYear(transfromTimeToYear(time));
            messageVo.setMonth(transfromTimeToMonth(time));
            messageVo.setDay(transfromTimeToDay(time));
            messageVo.setMin(transfromTimeToMin(time));
            messageVo.setAboutMeArticles(articleVo);
            messageVoList.add(messageVo);
        }
        //去重
        messageVoList = removeDupMenu(messageVoList);
        //根据时间进行排序 倒序排列
        Collections.sort(messageVoList, new Comparator<MessageVo>() {
            public int compare(MessageVo arg0, MessageVo arg1) {
                return arg1.getTheLastTime().compareTo(arg0.getTheLastTime());
            }
        });
//        messageVoList = messageVoList.stream().sorted((a, b) -> (int) (a.getTheLastTime().getTime() - b.getTheLastTime().getTime())).collect(Collectors.toList());

        return messageVoList;
    }

    //id相同即为重复
    private List<MessageVo> removeDupMenu(List<MessageVo> menus) {
        Map<ArticleVo, MessageVo> map = new HashMap<ArticleVo, MessageVo>();
        for (MessageVo menu : menus) {
            map.put(menu.getAboutMeArticles(), menu);
        }
        menus.clear();
        menus.addAll(map.values());
        return menus;
    }

    //将最新时间转换成年 月 日
    String transfromTimeToYear(Timestamp timestamp) {
        String year = "";
        DateFormat sdf = new SimpleDateFormat("yyyy");
        try {
            //方法一
            year = sdf.format(timestamp);
//            System.out.println(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return year;
    }

    String transfromTimeToMonth(Timestamp timestamp) {
        String year = "";
        DateFormat sdf = new SimpleDateFormat("MM");
        try {
            //方法一
            year = sdf.format(timestamp);
//            System.out.println(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return year;
    }

    String transfromTimeToDay(Timestamp timestamp) {
        String year = "";
        DateFormat sdf = new SimpleDateFormat("dd");
        try {
            //方法一
            year = sdf.format(timestamp);
//            System.out.println(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return year;
    }

    String transfromTimeToMin(Timestamp timestamp) {
        String year = "";
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            //方法一
            year = sdf.format(timestamp);
//            System.out.println(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return year;
    }

    public static void main(String[] args) {
        List<MessageVo> messageByUser = new MessageServiceImpl().findMessageByUser(27969433);

        for (MessageVo vo : messageByUser
        ) {
            System.out.println(vo.getAboutMeArticles().getArticle_id());
            System.out.println(vo.getTheLastTime());
            System.out.println(vo.getNewMessage());
        }
    }
}
