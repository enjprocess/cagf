package com.cagf.tool.util;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * 该类用来处理数据库通信
 * 被所有*DaoImpl所继承
 * @param <T>
 */
@SuppressWarnings(value = "unchecked")
public abstract class BaseDao<T> extends HibernateDaoSupport {

    /*创建*/

    //保存单一对象
    protected void storeObj(T obj) {
        this.getHibernateTemplate().saveOrUpdate(obj);
    }

    //批量保存对象
    protected void storeObjs(List<T> objs) {
        this.getHibernateTemplate().saveOrUpdateAll(objs);
    }

    /*删除*/

    //删除单一对象
    protected void removeObj(Class<T> c, Long id) {
        T t = this.getHibernateTemplate().get(c, id);
        this.getHibernateTemplate().delete(t);
    }

    //批量删除对象
    protected void removeObjs(Class<T> c, List<Long> ids) {
        for (Long id : ids) {
            this.removeObj(c, id);
        }
    }

    /*修改*/

    //更新单一对象
    protected void updateObj(T obj) {
        this.getHibernateTemplate().saveOrUpdate(obj);
    }

    //批量更新对象
    protected void updateObjs(List<T> objs) {
        this.getHibernateTemplate().saveOrUpdateAll(objs);
    }

    /*查询*/

     /*带参数*/

    //根据id返回对象
    protected T retrieveObj(Class<T> c, Long id) {
        return this.getHibernateTemplate().get(c, id);
    }

    //根据带参数的Hql返回集合对象
    protected List<T> retrieveObjs(String queryString, String ... value) {
        return this.getHibernateTemplate().find(queryString, value);
    }

    //根据带参数的HQL语返回单一对象
    protected T retrieveObj(String queryString, String ... value) {
        List<T> list = this.getHibernateTemplate().find(queryString, value);
        if (list == null || list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    //根据带参数的Hql返回集合对象 分页
    protected List<T> retrieveObjs(String queryString, final String[] value, final int start, final int number) {
        return this.getHibernateTemplate().executeFind(session -> {
           Query query = session.createQuery(queryString);
            for (int i = 0; i < value.length; i++) {
                query = query.setString(i, value[i]);
            }
            query.setFirstResult(start);
            query.setMaxResults(number);
            return (List<T>)query.list();
        });
    }

    //根据一个参数的HQL语句返回集合对象 分页
    protected List<T> retrieveObjs(String queryString, final String value, final int start, final int number) {
        String[] str = {value};
        return this.retrieveObjs(queryString, str, start, number);
    }

    //根据带参数的HQL语句，返回记录数量
    protected long retrieveObjsCount(final String queryString, final String... value) {
        return (Long) this.getHibernateTemplate().execute(session -> {
            Query query = session.createQuery("select count(*) " + queryString);
            for (int i = 0; i < value.length; i++) {
                query.setString(i, value[i]);
            }
            return query.iterate().next();
        });
    }

     /*不带参数*/

    //根据不带参数的hql返回集合对象
    protected List<T> retrieveObjs(String queryString) {
        return this.getHibernateTemplate().find(queryString);
    }

    //根据不带参数的hql返回单一对象
    protected T retrieveObj(String queryString) {
        return (T) this.getHibernateTemplate().find(queryString);
    }

    //根据不带参数的hql返回记录数
    protected long retrieveObjsCount(final String queryString) {
        return this.retrieveObjsCount(queryString, new String[]{});

    }


}
