package com.op.web.dao;

import org.hibernate.query.Query;

import com.op.web.pojo.Temp;

public class TempDao
{
	private HbntFactory hf = null;
	private Temp tmp = null;

	public HbntFactory getHf()
	{
		return hf;
	}

	public void setHf(HbntFactory hf)
	{
		this.hf = hf;
	}

	public Temp getTmp()
	{
		return tmp;
	}

	public void setTmp(Temp tmp)
	{
		this.tmp = tmp;
	}
	
	public int upd(Temp tmp)
	{
		hf.begin();
		hf.getSession().update(tmp);
		hf.end();
		return 1;
	}
	
	public Temp sel()
	{
		if(tmp.getId() != -1)
		{
			return tmp;
		}
		hf.begin();
		Query<Temp> qry = hf.getSession()
				.createQuery("FROM Temp WHERE id=0", Temp.class);
		tmp = qry.getResultList().get(0);
		hf.end();
		return tmp;
	}
}
