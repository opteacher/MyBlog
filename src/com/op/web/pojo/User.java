package com.op.web.pojo;

public class User
{
	private String nam = "";
	private String pwd = "";
	private int rol = -1;
	private boolean mgr = false;
	private String pfl = "";
	
	public String getNam()
	{
		return nam;
	}
	public void setNam(String nam)
	{
		this.nam = nam;
	}
	public String getPwd()
	{
		return pwd;
	}
	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}
	public int getRol()
	{
		return rol;
	}
	public void setRol(int rol)
	{
		this.rol = rol;
	}
	public boolean isMgr()
	{
		return mgr;
	}
	public void setMgr(boolean mgr)
	{
		this.mgr = mgr;
	}
	public String getPfl()
	{
		return pfl;
	}
	public void setPfl(String pfl)
	{
		this.pfl = pfl;
	}
}
