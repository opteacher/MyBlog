package com.op.web.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.op.web.pojo.Role;

public class RoleDao
{
	private HbntFactory hf = null;

	public HbntFactory getHf()
	{
		return hf;
	}

	public void setHf(HbntFactory hf)
	{
		this.hf = hf;
	}
	
	public List<Role> selAll()
	{
		hf.begin();
		Query<Role> qry = hf.getSession()
				.createQuery("FROM Role", Role.class);
		List<Role> lst = qry.getResultList();
		hf.end();
		return lst;
	}
}
