package com.op.web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HbntFactory
{
	private boolean isInit = false;
	private SessionFactory sf = null;
	private Session ss = null;
	private Transaction ts = null;
	
	static public final String DftCfgPh = "com/op/web/dao/hbnt-cfg.xml";
	
	public void init()
	{
		sf = new Configuration()
				.configure(DftCfgPh)
				.buildSessionFactory();
		isInit = true;
	}
	
	public void clear()
	{
		sf.close();
		isInit = false;
	}
	
	public boolean isInit()	{ return isInit; }
	
	public Session getSession()
	{
		return ss;
	}
	
	public void begin()
	{
		if(!isInit)
		{
			init();
		}
		ss = sf.openSession();
		ts = ss.beginTransaction();
	}
	
	public void end()
	{
		ts.commit();
		ss.close();
		ts = null;
		ss = null;
	}
}
