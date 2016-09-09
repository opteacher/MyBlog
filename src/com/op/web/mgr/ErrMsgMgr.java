package com.op.web.mgr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ErrMsgMgr extends DefaultHandler
{	
	static public final String resFile = "com/op/web/mgr/msg.xml";
	static public final String ErrNoUser = "ErrNoUser";
	static public final String ErrWrongPwd = "ErrWrongPwd";
	
	private Map<String, String> msgTbl = null;
	
	public Map<String, String> getMsgTbl()
	{
		if(msgTbl.isEmpty())
		{
			init();
		}
		return msgTbl;
	}

	public void setMsgTbl(Map<String, String> msgTbl)
	{
		this.msgTbl = msgTbl;
	}

	public void init()
	{
		try
		{
			SAXParserFactory saxFcty = SAXParserFactory.newInstance();
			SAXParser sax = saxFcty.newSAXParser();
			InputStream is = ErrMsgMgr.class.getClassLoader()
					.getResourceAsStream(resFile);
			sax.parse(is, this);
		}
		catch(ParserConfigurationException | SAXException e)
		{
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private boolean isBegParseMsg = false;
	private enum MsgType
	{
		NONE, ERROR
	}
	private MsgType curMsgTp = MsgType.NONE;
	private String curMsgId = "";
	private String curMsgTxt = "";

	@Override
	public void startElement(String uri, String lNam, String qNam, Attributes attr) throws SAXException
	{
		switch(qNam)
		{
		case "msg-root":
			isBegParseMsg = true;
			break;
		case "err":
			if(isBegParseMsg)
			{
				curMsgTp = MsgType.ERROR;
			}
			break;
		case "msg":
			if(isBegParseMsg)
			{
				switch(curMsgTp)
				{
				case ERROR:
					curMsgId = getMsgId(attr);
					break;
				default:
					break;
				}
			}
			break;
		}
	}
	
	private String getMsgId(Attributes attr)
	{
		for(int i = 0; i < attr.getLength(); ++i)
		{
			if(attr.getQName(i).equals("id"))
			{
				return attr.getValue(i);
			}
		}
		return "";
	}
	
	@Override
	public void endElement(String uri, String lNam, String qNam) throws SAXException
	{
		switch(qNam)
		{
		case "msg-root":
			isBegParseMsg = false;
			break;
		case "err":
			if(isBegParseMsg)
			{
				curMsgTp = MsgType.NONE;
			}
			break;
		case "msg":
			if(isBegParseMsg)
			{
				switch(curMsgTp)
				{
				case ERROR:
					msgTbl.put(curMsgId, curMsgTxt);
					break;
				default:
					break;
				}
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		if(isBegParseMsg && curMsgTp != MsgType.NONE)
		{
			curMsgTxt = new String(ch, start, length);
		}
	}
}
