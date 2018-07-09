package @PACKAGE@;

import @IMPORTBEANS@;

import java.util.*;

public interface @CLASSNAME@Dao {

    void save@CLASSNAME@(@CLASSNAME@ bean);
	
	void save@CLASSNAME@s(List<@CLASSNAME@> beans);
	
	void remove@CLASSNAME@(Long id);
	
	void remove@CLASSNAME@(@CLASSNAME@ bean);
	
	void remove@CLASSNAME@s(List<Long> ids);
	
	void update@CLASSNAME@(@CLASSNAME@ bean);
	
	void update@CLASSNAME@s(List<@CLASSNAME@> beans);
	
	@CLASSNAME@ get@CLASSNAME@(Long id);
	
	long get@CLASSNAME@Count(String queryString);
	
	long get@CLASSNAME@Count(String queryString, String value);
	
	long get@CLASSNAME@Count(String queryString, String[] value);
	
	List<@CLASSNAME@> search@CLASSNAME@s(String queryString);
	
	List<@CLASSNAME@> search@CLASSNAME@s(String queryString, String value);
	
	List<@CLASSNAME@> search@CLASSNAME@s(String queryString, String[] value);
	
	@CLASSNAME@ search@CLASSNAME@(String queryString);
	
	@CLASSNAME@ search@CLASSNAME@(String queryString, String value);
	
	@CLASSNAME@ search@CLASSNAME@(String queryString, String[] value);
	
	List<@CLASSNAME@> search@CLASSNAME@s(String queryString, String value, int start, int number);
	
	List<@CLASSNAME@> search@CLASSNAME@s(String queryString, String[] value, int start, int number);
	
	
	
}