package com.op.web.action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.op.web.dao.TempDao;
import com.op.web.dao.UserDao;
import com.op.web.dao.UsrSttDao;
import com.op.web.pojo.User;
import com.op.web.pojo.UserState;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Register extends ActionSupport
{
	private UserDao ud = null;
	private TempDao td = null;
	private UsrSttDao usd = null;
	private User usr = null;
	private UserState ust = null;
	private String userName = "";
	private String password = "";
	private String mgrCode = "";
	private int role = 0;
	private File profile = null;
	private String profileFileName = "";
	private String profileContentType = "";
	
	public UserDao getUd()
	{
		return ud;
	}

	public void setUd(UserDao ud)
	{
		this.ud = ud;
	}
	
	public TempDao getTd()
	{
		return td;
	}

	public void setTd(TempDao td)
	{
		this.td = td;
	}

	public UsrSttDao getUsd()
	{
		return usd;
	}

	public void setUsd(UsrSttDao usd)
	{
		this.usd = usd;
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

	public String getMgrCode()
	{
		return mgrCode;
	}

	public void setMgrCode(String mgrCode)
	{
		this.mgrCode = mgrCode;
	}

	public int getRole()
	{
		return role;
	}

	public void setRole(int role)
	{
		this.role = role;
	}
	
	public User getUsr()
	{
		return usr;
	}

	public void setUsr(User usr)
	{
		this.usr = usr;
	}

	public UserState getUst()
	{
		return ust;
	}

	public void setUst(UserState ust)
	{
		this.ust = ust;
	}

	public File getProfile()
	{
		return profile;
	}

	public void setProfile(File profile)
	{
		this.profile = profile;
	}

	public String getProfileFileName()
	{
		return profileFileName;
	}

	public void setProfileFileName(String profileFileName)
	{
		this.profileFileName = profileFileName;
	}

	public String getProfileContentType()
	{
		return profileContentType;
	}

	public void setProfileContentType(String profileContentType)
	{
		this.profileContentType = profileContentType;
	}

	@Override
	public String execute() throws Exception
	{
		//上传用户选择的头像文件
		String path = ServletActionContext
				.getServletContext()
				.getRealPath("/assets/img");
		if(profile != null)
		{
			File ph = new File(path);
			File fl = new File(ph, profileFileName);
			if(!ph.exists())
			{
				ph.mkdirs();
			}
			FileUtils.copyFile(profile, fl);
		}
		
		//将用户表中插入注册的数据
		usr.setNam(userName);
		usr.setPwd(password);
		usr.setMgr(mgrCode.equals(td.sel().getMgrCode()));
		usr.setRol(role);
		if(!profileFileName.isEmpty())
		{
			usr.setPfl("assets/img/" + profileFileName);
		}
		ud.add(usr);
		
		//在用户状态表中新建用户条目
		ust.setNam(userName);
		ust.setLgn(false);
		usd.add(ust);
		
		//将插入的用户名装入Session自动填到登陆页面的用户名输入框
		ActionContext.getContext()
			.getSession()
			.put(LgnInit.DFT_USR, usr.getNam());
		
		return SUCCESS;
	}
}
