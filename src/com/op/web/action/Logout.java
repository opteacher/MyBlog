package com.op.web.action;

import java.util.Map;

import com.op.web.dao.UsrSttDao;
import com.op.web.pojo.User;
import com.op.web.pojo.UserState;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Logout extends ActionSupport
{
	private UsrSttDao usd = null;
	private UserState us = null;
	
	public UsrSttDao getUsd()
	{
		return usd;
	}

	public void setUsd(UsrSttDao usd)
	{
		this.usd = usd;
	}

	public UserState getUs()
	{
		return us;
	}

	public void setUs(UserState us)
	{
		this.us = us;
	}

	@Override
	public String execute() throws Exception
	{
		Map<String, Object> ss = ActionContext.getContext().getSession();
		
		//将用户状态改为退出
		User usr = (User) ss.get(Login.USR_INF);
		us.setNam(usr.getNam());
		us.setLgn(false);
		usd.upd(us);
		
		return SUCCESS;
	}
}
