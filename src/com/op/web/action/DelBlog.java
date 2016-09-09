package com.op.web.action;

import com.op.web.pojo.User;

import java.util.Map;

import com.op.web.dao.BlogDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DelBlog extends ActionSupport
{
	private int idx = 0;
	private BlogDao bd = null;

	public int getIdx()
	{
		return idx;
	}

	public void setIdx(int idx)
	{
		this.idx = idx;
	}

	public BlogDao getBd()
	{
		return bd;
	}

	public void setBd(BlogDao bd)
	{
		this.bd = bd;
	}

	@Override
	public String execute() throws Exception
	{
		Map<String, Object> ss = ActionContext
				.getContext().getSession();
		User usr = (User) ss.get(Login.USR_INF);
		String usrNam = usr.getNam();
		
		//删除博客
		bd.del(idx);
		
		//重新获取所有博客
		ss.put(com.op.web.action.Blog.BLG_CTS, bd.sel(usrNam));
		return SUCCESS;
	}
	
}
