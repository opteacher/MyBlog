package com.op.web.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.op.web.pojo.UserState;

public class UsrSttDao
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
	
	public int add(UserState us)
	{
		hf.begin();
		hf.getSession().save(us);
		hf.end();
		return 1;
	}
	
	public int upd(UserState us)
	{
		hf.begin();
		hf.getSession().update(us);
		hf.end();
		return 1;
	}
	
	public List<UserState> selLgnUsr(boolean isLgn)
	{
		hf.begin();
		Query<UserState> qry = hf.getSession()
				.createQuery(
						"FROM UserState WHERE isLogin=?", 
						UserState.class);
		qry.setParameter(0, isLgn);
		List<UserState> lst = qry.getResultList();
		hf.end();
		return lst;
	}
}
