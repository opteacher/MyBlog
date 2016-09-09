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
		//���Session
		Map<String, Object> ss = ActionContext.getContext().getSession();
		//��Session��ȡ�ô�Login���洫�������û���Ϣ
		User usr = (User) ss.get(Login.USR_INF);
		//�����û�������blog���л�����и��û�����Ĳ���
		List<com.op.web.pojo.Blog> lst = bd.sel(usr.getNam());
		ss.put(BLG_CTS, lst);
		
		//�������в��ͣ������еķ������ڹ���
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
		//����ǰ��¼�ŵ��û�д��Session
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
