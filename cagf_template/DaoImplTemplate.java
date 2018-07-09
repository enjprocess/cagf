package @PACKAGE@;

import @IMPORTBEANS@;
import @IMPORTDAOINTERFACE@
import java.util.*;
import com.cagf.tool.util.*;


public class @CLASSNAME@DaoImpl extends BaseDao<@CLASSNAME@> implements @CLASSNAME@Dao {
	
	public void save@CLASSNAME@(@CLASSNAME@ bean) {
		storeObj(bean);
	}
	
	public void save@CLASSNAME@s(List<@CLASSNAME@> beans) {
		storeObjs(beans);
	}

	public void remove@CLASSNAME@(Long id) {
		removeObj(@CLASSNAME@.class, id);
	}

	public void remove@CLASSNAME@(@CLASSNAME@ bean) {
		this.remove@CLASSNAME@(bean.getId());
	}

	public void remove@CLASSNAME@s(List<Long> ids) {
		removeObjs(@CLASSNAME@.class, ids);
	}

	public void update@CLASSNAME@(@CLASSNAME@ bean) {
		updateObj(bean);
	}

	public void update@CLASSNAME@s(List<@CLASSNAME@> beans) {
		updateObjs(beans);
	}

	public @CLASSNAME@ get@CLASSNAME@(Long id) {
		return retrieveObj(@CLASSNAME@.class, id);
	}

	public long get@CLASSNAME@Count(String queryString) {
		return retrieveObjsCount(queryString);
	}

	public long get@CLASSNAME@Count(String queryString, String value) {
		return retrieveObjsCount(queryString, value);
	}

	public long get@CLASSNAME@Count(String queryString, String[] value) {
		return retrieveObjsCount(queryString, value);
	}

	public List<@CLASSNAME@> search@CLASSNAME@s(String queryString) {
		return retrieveObjs(queryString);
	}

	public List<@CLASSNAME@> search@CLASSNAME@s(String queryString, String value) {
		return retrieveObjs(queryString, value);
	}

	public List<@CLASSNAME@> search@CLASSNAME@s(String queryString, String[] value) {
		return retrieveObjs(queryString, value);
	}

	public @CLASSNAME@ search@CLASSNAME@(String queryString) {
		return retrieveObj(queryString);
	}

	public @CLASSNAME@ search@CLASSNAME@(String queryString, String value) {
		return retrieveObj(queryString, value);
	}

	public @CLASSNAME@ search@CLASSNAME@(String queryString, String[] value) {
		return retrieveObj(queryString, value);
	}

	public List<@CLASSNAME@> search@CLASSNAME@s(String queryString, String value, int start, int number) {
		return retrieveObjs(queryString, value, start, number);
	}

	public List<@CLASSNAME@> search@CLASSNAME@s(String queryString, String[] value, int start, int number) {
		return retrieveObjs(queryString, value, start, number);
	}
    
	
	
}