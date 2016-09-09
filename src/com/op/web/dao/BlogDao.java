package com.op.web.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.op.web.pojo.Blog;

public class BlogDao
{
	private HbntFactory hf = null;
	private Blog blg = null;

	public HbntFactory getHf()
	{
		return hf;
	}

	public void setHf(HbntFactory hf)
	{
		this.hf = hf;
	}
	
	public Blog getBlg()
	{
		return blg;
	}

	public void setBlg(Blog blg)
	{
		this.blg = blg;
	}

	protected void finalize()
	{
		hf.clear();
		hf = null;
	}
	
	public int add(Blog blg)
	{
		hf.begin();
		hf.getSession().save(blg);
		hf.end();
		return 1;
	}
	
	public int del(Blog blg)
	{
		hf.begin();
		hf.getSession().delete(blg);
		hf.end();
		return 1;
	}
	
	public int del(int idx)
	{
		blg.setIdx(idx);
		hf.begin();
		hf.getSession().delete(blg);
		hf.end();
		return 1;
	}
	
	public int upd(Blog blg)
	{
		hf.begin();
		hf.getSession().update(blg);
		hf.end();
		return 1;
	}
	
	public Blog sel(int idx)
	{
		hf.begin();
		Query<Blog> lst = hf.getSession()
				.createQuery("FROM Blog WHERE idx=?", Blog.class);
		lst.setParameter(0, idx);
		List<Blog> rst = lst.getResultList();
		hf.end();
		if(rst == null || rst.isEmpty())
		{
			return null;
		}
		else
		{
			return rst.get(0);
		}
	}
	
	public List<Blog> sel(String nam)
	{
		hf.begin();
		Query<Blog> lst = hf.getSession()
				.createQuery("FROM Blog WHERE name=? ORDER BY public", Blog.class);
		lst.setParameter(0, nam);
		List<Blog> rst = lst.getResultList();
		hf.end();
		return rst;
	}
}
