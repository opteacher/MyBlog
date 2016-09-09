package com.op.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.op.web.dao.BlogDao;
import com.op.web.dao.UserDao;
import com.op.web.dao.UsrSttDao;
import com.op.web.pojo.User;
import com.op.web.pojo.UserState;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Blog extends ActionSupport
{
	static public final String BLG_CTS = "blogContents";
	static public final String PBC_MON = "pbcMonth";
	static public final String LGN_USR = "lgnUsr";
	
	private BlogDao bd = null;
	private UserDao ud = null;
	private UsrSttDao usd = null;
	
	public BlogDao getBd()
	{
		return bd;
	}

	public void setBd(BlogDao bd)
	{
		this.bd = bd;
	}

	public UserDao getUd()
	{
		return ud;
	}

	public void setUd(UserDao ud)
	{
		this.ud = ud;
	}

	public UsrSttDao getUsd()
	{
		return usd;
	}

	public void setUsd(UsrSttDao usd)
	{
		this.usd = usd;
	}

	@Override
	public String execute() throws Exception
	{
		//获得Session
		Map<String, Object> ss = ActionContext.getContext().getSession();
		//从Session中取得从Login画面传过来的用户信息
		User usr = (User) ss.get(Login.USR_INF);
		//根据用户名，从blog表中获得所有该用户发表的博客
		List<com.op.web.pojo.Blog> lst = bd.sel(usr.getNam());
		ss.put(BLG_CTS, lst);
		
		//遍历所有博客，将其中的发表日期归类
		List<String> pbcMthLst = new ArrayList<String>();
		for(com.op.web.pojo.Blog blg : lst)
		{
			String pbcMonth = new SimpleDateFormat("yyyy-MM")
					.format(blg.getPbc());
			if(!pbcMthLst.contains(pbcMonth))
			{
				pbcMthLst.add(pbcMonth);
			}
		}
		ss.put(PBC_MON, pbcMthLst);
		//将当前登录着的用户写入Session
		List<UserState> usrSttLst = usd.selLgnUsr(true);
		List<User> usrLst = new ArrayList<User>();
		for(UserState us : usrSttLst)
		{
			usrLst.add(ud.sel(us.getNam()));
		}
		ss.put(LGN_USR, usrLst);
		
		return SUCCESS;
	}
}
