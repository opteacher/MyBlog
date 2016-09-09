package com.op.web.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.op.web.pojo.User;
import com.op.web.dao.HbntFactory;

public class UserDao
{
	private HbntFactory hf;

	public HbntFactory getHf()
	{
		return hf;
	}

	public void setHf(HbntFactory hf)
	{
		this.hf = hf;
	}

	protected void finalize()
	{
		hf.clear();
		hf = null;
	}
	
	public int add(User usr)
	{
		hf.begin();
		hf.getSession().save(usr);
		hf.end();
		return 1;
	}
	public int del(User usr)
	{
		return 0;
	}
	public int upd(User usr)
	{
		return 0;
	}
	public User sel(String nam)
	{
		hf.begin();
		Query<User> qry = hf.getSession()
				.createQuery("FROM User WHERE name=?", User.class);
		qry.setParameter(0, nam);
		List<User> rstLst = qry.getResultList();
		if(rstLst == null || rstLst.isEmpty())
		{
			return null;
		}
		User usr = qry.getResultList().get(0);
		hf.end();
		return usr;
	}
	public List<User> selAll()
	{
		return null;
	}
}
