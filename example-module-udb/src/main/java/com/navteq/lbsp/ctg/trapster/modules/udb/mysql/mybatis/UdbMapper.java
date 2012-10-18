package com.navteq.lbsp.ctg.trapster.modules.udb.mysql.mybatis;

import org.apache.ibatis.annotations.Param;
import com.navteq.lbsp.ctg.trapster.modules.udb.domain.Udb;

public interface UdbMapper
{
	void deleteById(Long id);
	int insert(Udb user);
	Udb selectById(@Param(value= "id") Long id);
	Udb selectByName(@Param(value= "name") String name);
	Udb selectByNameOrUname(@Param(value= "nameOrUname") String nameOrUname);
	Udb selectByUname(@Param(value= "uname") String uname);
	void update(Udb user);
}
