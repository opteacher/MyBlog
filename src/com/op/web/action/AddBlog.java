package com.op.web.action;

import com.op.web.pojo.Blog;
import com.op.web.pojo.User;

import java.sql.Date;
import java.util.Map;

import com.op.web.dao.BlogDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AddBlog extends ActionSupport
{
	private BlogDao bd = null;
	private int blgIndex = -1;
	private String blgTitle = "";
	private String blgContents = "";
	private Blog blg = null;
	
	public BlogDao getBd()
	{
		return bd;
	}

	public void setBd(BlogDao bd)
	{
		this.bd = bd;
	}

	public int getBlgIndex()
	{
		return blgIndex;
	}

	public void setBlgIndex(int blgIndex)
	{
		this.blgIndex = blgIndex;
	}

	public String getBlgTitle()
	{
		return blgTitle;
	}

	public void setBlgTitle(String blgTitle)
	{
		this.blgTitle = blgTitle;
	}

	public String getBlgContents()
	{
		return blgContents;
	}

	public void setBlgContents(String blgContents)
	{
		this.blgContents = blgContents;
	}

	public Blog getBlg()
	{
		return blg;
	}

	public void setBlg(Blog blg)
	{
		this.blg = blg;
	}

	@Override
	public String execute() throws Exception
	{
		Map<String, Object> ss = ActionContext
				.getContext().getSession();
		User usr = (User) ss.get(Login.USR_INF);
		String usrNam = usr.getNam();
		
		blg.setTtl(blgTitle);
		blg.setCts(blgContents);
		blg.setNam(usrNam);
		blg.setPbc(new Date(System.currentTimeMillis()));
		if(blgIndex != -1)
		{
			blg.setIdx(blgIndex);
			bd.upd(blg);
			blgIndex = -1;
		}
		else
		{
			bd.add(blg);
		}
		
		ss.put(com.op.web.action.Blog.BLG_CTS, bd.sel(usrNam));
		return SUCCESS;
	}
}
