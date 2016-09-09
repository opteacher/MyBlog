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
		// 1)从数据来源获取数据（已配对）
		// 2)对数据进行数据库无关性检测（脚本语言基本可以满足）
		//得到一个ActionContext用于存放跳转到下个页面的数据
		ActionContext actCtx = ActionContext.getContext();
		//得到ApplicationContext用于得到Bean中ErrMsgMgr对象
		ApplicationContext appCtx = 
				WebApplicationContextUtils
				.getWebApplicationContext(request.getServletContext());
		ErrMsgMgr emm = (ErrMsgMgr) appCtx.getBean("errMsgMgr");
		//操作结果消息
		String msg = "";
		// 3)调用DAO操作数据库
		User u = ud.sel(userName);
		// 4)对数据进行数据库有关性检测
		if(u == null)
		{
			// 数据库没有这个用户
			// 5)将操作结果转成结果码，并根据结果码找到对应的说明文字
			msg = emm.getMsgTbl()
					.get(ErrMsgMgr.ErrNoUser)
					.replace("%user", userName);
			// 6)将说明文字装填进Session或者Parameter以供跳转页面使用
			actCtx.getSession().put(ERR_MSG, msg);
			return ERROR;
		}
		if(u.getPwd().equals(password))
		{
			//将用户状态改为登陆
			us.setNam(userName);
			us.setLgn(true);
			usd.upd(us);
			
			//保存用户名
			if(!rmbUsr.isEmpty())
			{
				Temp tmp = td.sel();
				tmp.setDftUser(userName);
				td.upd(tmp);
			}
			
			//将用户名放进Session中
			actCtx.getSession().put(USR_INF, u);
			
			//密码正确，登陆成功
			return SUCCESS;
		}
		else
		{
			//密码错误，提示用户
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
