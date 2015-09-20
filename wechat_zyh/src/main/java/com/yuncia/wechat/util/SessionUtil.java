package com.yuncia.wechat.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-4
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 */
public class SessionUtil {
    public static void setSessionOpenId(HttpServletRequest request,String openId){
        request.getSession().setAttribute("openId",openId);
    }
    public static String getSessionOpenId(HttpServletRequest request){
        return (String)request.getSession().getAttribute("openId");
//        return "oJWuvuGDR-H9Fjv7Lnw7zJuJRffg";
    }
    public static void setSessionCustomerID(HttpServletRequest request,String customerId){
        request.getSession().setAttribute("customerId",customerId);
    }
    public static String getSessionCustomerID(HttpServletRequest request){
        return (String)request.getSession().getAttribute("customerId");
//        return "269945";
    }
    public static void setSessionPrizeID(HttpServletRequest request,String prizeId){
        request.getSession().setAttribute("prizeId",prizeId);
    }
    public static String getSessionPrizeID(HttpServletRequest request){
        return (String)request.getSession().getAttribute("prizeId");
//        return "342";
    }
    public static void setSessionVoteInfoAddr(HttpServletRequest request,String voteInfoAddr){
        request.getSession().setAttribute("voteInfoAddr",voteInfoAddr);
    }
    public static String getSessionVoteInfoAddr(HttpServletRequest request){
        return (String)request.getSession().getAttribute("voteInfoAddr");
//        return "342";
    }
    public static void setSessionVoteCommentAddr(HttpServletRequest request,String voteCommentAddr){
        request.getSession().setAttribute("voteCommentAddr",voteCommentAddr);
    }
    public static String getSessionVoteCommentAddr(HttpServletRequest request){
        return (String)request.getSession().getAttribute("voteCommentAddr");
//        return "342";
    }
    
    /**
     * 把userid放进session
     * @param request
     * @param userId
     */
    public static void setSessionUserId(HttpServletRequest request,String userId){
        request.getSession().setAttribute("userId",userId);
    }
    /**
     * 获取session中的userid
     * @param request
     * @param userId
     */
    public static String getSessionUserId(HttpServletRequest request){
        return (String)request.getSession().getAttribute("userId");
    }
    
}
