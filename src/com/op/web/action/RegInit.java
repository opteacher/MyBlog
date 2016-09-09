package com.op.web.action;

import java.util.Map;

import com.op.web.dao.RoleDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RegInit extends ActionSupport
{
	static public final String ROLE_MAP = "roleMap";
	
	private RoleDao rd = null;

	public RoleDao getRd()
	{
		return rd;
	}

	public void setRd(RoleDao rd)
	{
		this.rd = rd;
	}

	@Override
	public String execute() throws Exception
	{
		Map<String, Object> ss = 
				ActionContext
				.getContext()
				.getSession();
		ss.put(ROLE_MAP, rd.selAll());
		return SUCCESS;
	}
}
