package com.navteq.lbsp.ctg.trapster.modules.udb.mysql.mybatis;

import javax.inject.Named;
import javax.inject.Singleton;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.navteq.lbsp.ctg.trapster.modules.udb.TrapsterUdbException;
import com.navteq.lbsp.ctg.trapster.modules.udb.domain.Udb;
import com.navteq.lbsp.ctg.trapster.modules.udb.domain.UdbDao;

@Singleton
@Named("udbDao")
public class UdbDaoImpl implements UdbDao
{
	private final static Log LOGGER= LogFactory.getLog(UdbDaoImpl.class);
	//
	@Autowired
	@Qualifier("udbMapper")
	private UdbMapper mapper;
	//
	public int create(Udb user) throws TrapsterUdbException
	{
		if (LOGGER.isDebugEnabled())
		{
			LOGGER.debug("UserDAOMybatisImpl.insert: inserting user " + user.toString());
		}
		// throw exception for user with same email or name
		Udb userCheck= this.getByName(user.getUname());
		if (userCheck != null)
		{
			throw new TrapsterUdbException("User: " + user.getUname() + " already exists.");
		}
		int rc= this.mapper.insert(user);
		LOGGER.debug("insert: insert returning " + rc);
		return rc;
	}
	public Udb getById(Long id)
	{
		return mapper.selectById(id);
	}
	public Udb getByName(String name)
	{
		return mapper.selectByName(name);
	}
	public Udb getByNameOrUname(String nameOrUname)
	{
		return mapper.selectByNameOrUname(nameOrUname);
	}
	public Udb getByUname(String uname)
	{
		return mapper.selectByUname(uname);
	}
	public void removeById(Long id)
	{
		mapper.deleteById(id);
	}
	public void update(Udb user)
	{
		mapper.update(user);
	}
}
