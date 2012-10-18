package com.navteq.lbsp.ctg.trapster.modules.udb.domain;

import com.navteq.lbsp.ctg.trapster.modules.udb.TrapsterUdbException;

public interface UdbDao
{
	int create(Udb user) throws TrapsterUdbException;
	Udb getById(Long id);
	Udb getByName(String name);
	Udb getByNameOrUname(String nameOrUname);
	Udb getByUname(String uname);
	void removeById(Long id);
	void update(Udb user);
}
