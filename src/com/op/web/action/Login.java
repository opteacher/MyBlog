package com.op.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.op.web.dao.TempDao;
import com.op.web.dao.UserDao;
import com.op.web.dao.UsrSttDao;
import com.op.web.mgr.ErrMsgMgr;
import com.op.web.pojo.Temp;
import com.op.web.pojo.User;
import com.op.web.pojo.UserState;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Login extends ActionSupport implements ServletRequestAware
{
	static public final String ERR_MSG = "errMsg";
	static public final String WNG_MSG = "wngMsg";
	static public final String USR_NAM = "usrNam";
	static public final String USR_INF = "userInfo";
	
	private UserDao ud = null;
	private UsrSttDao usd = null;
	private TempDao td = null;
	private UserState us = null;
	private String userName = "";
	private String password = "";
	private String rmbUsr = "";

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
	public TempDao getTd()
	{
		return td;
	}
	public void setTd(TempDao td)
	{
		this.td = td;
	}
	public UserState getUs()
	{
		return us;
	}
	public void setUs(UserState us)
	{
		this.us = us;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getRmbUsr()
	{
		return rmbUsr;
	}
	public void setRmbUsr(String rmbUsr)
	{
		this.rmbUsr = rmbUsr;
	}
	@Override
	public String execute() throws Exception
	{
		// 1)��������Դ��ȡ���ݣ�����ԣ�
		// 2)�����ݽ������ݿ��޹��Լ�⣨�ű����Ի����������㣩
		//�õ�һ��ActionContext���ڴ����ת���¸�ҳ�������
		ActionContext actCtx = ActionContext.getContext();
		//�õ�ApplicationContext���ڵõ�Bean��ErrMsgMgr����
		ApplicationContext appCtx = 
				WebApplicationContextUtils
				.getWebApplicationContext(request.getServletContext());
		ErrMsgMgr emm = (ErrMsgMgr) appCtx.getBean("errMsgMgr");
		//���������Ϣ
		String msg = "";
		// 3)����DAO�������ݿ�
		User u = ud.sel(userName);
		// 4)�����ݽ������ݿ��й��Լ��
		if(u == null)
		{
			// ���ݿ�û������û�
			// 5)���������ת�ɽ���룬�����ݽ�����ҵ���Ӧ��˵������
			msg = emm.getMsgTbl()
					.get(ErrMsgMgr.ErrNoUser)
					.replace("%user", userName);
			// 6)��˵������װ���Session����Parameter�Թ���תҳ��ʹ��
			actCtx.getSession().put(ERR_MSG, msg);
			return ERROR;
		}
		if(u.getPwd().equals(password))
		{
			//���û�״̬��Ϊ��½
			us.setNam(userName);
			us.setLgn(true);
			usd.upd(us);
			
			//�����û���
			if(!rmbUsr.isEmpty())
			{
				Temp tmp = td.sel();
				tmp.setDftUser(userName);
				td.upd(tmp);
			}
			
			//���û����Ž�Session��
			actCtx.getSession().put(USR_INF, u);
			
			//������ȷ����½�ɹ�
			return SUCCESS;
		}
		else
		{
			//���������ʾ�û�
			msg = emm.getMsgTbl()
					.get(ErrMsgMgr.ErrWrongPwd);
			actCtx.getSession().put(ERR_MSG, msg);
			return ERROR;
		}
	}
	private HttpServletRequest request = null;
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

}
