package com.op.web.pojo;

import java.sql.Date;

public class Blog
{
	private int idx = 0;
	private String ttl = "";
	private Date pbc = null;
	private String nam = "";
	private String cts = "";
	
	public int getIdx()
	{
		return idx;
	}
	public void setIdx(int idx)
	{
		this.idx = idx;
	}
	public String getTtl()
	{
		return ttl;
	}
	public void setTtl(String ttl)
	{
		this.ttl = ttl;
	}
	public Date getPbc()
	{
		return pbc;
	}
	public void setPbc(Date pbc)
	{
		this.pbc = pbc;
	}
	public String getNam()
	{
		return nam;
	}
	public void setNam(String nam)
	{
		this.nam = nam;
	}
	public String getCts()
	{
		return cts;
	}
	public void setCts(String cts)
	{
		this.cts = cts;
	}
}
