package com.op.web.action;

import java.util.Map;

import com.op.web.dao.TempDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LgnInit extends ActionSupport
{
	static public String DFT_USR = "dftUsr";
	
	private TempDao td = null;

	public TempDao getTd()
	{
		return td;
	}

	public void setTd(TempDao td)
	{
		this.td = td;
	}

	@Override
	public String execute() throws Exception
	{
		Map<String, Object> ss = ActionContext
				.getContext().getSession();
		ss.put(DFT_USR, td.sel().getDftUser());
		return SUCCESS;
	}
}
