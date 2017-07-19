package com.jxufe.ctdms.dao;

import java.util.List;

import com.jxufe.ctdms.utils.Pagination;
import com.jxufe.ctdms.utils.QueryCondition;

 

/**
 * interface <code>BaseDao</code> DAO接口,封装常用的数据库操作
 * 
 * @author Luxh
 */
public interface BaseDao {
    
    /**
     * 新增实体
     * @param entity  要新增的实体
     */
    public void save(Object entity);
    
    /**
     * 更新实体
     * @param entity  要更新的实体
     */
    public void update(Object entity);
    
    /**
     * 根据主键删除实体
     * @param <T>
     * @param clazz   实体类的Class
     * @param id      主键
     */
    public <T> void delete(Class<T> clazz,Object id);
    
    /**
     * 根据主键批量删除实体
     * @param <T>
     * @param clazz   实体类的Class
     * @param id      主键数组
     */
    public <T> void delete(Class<T> clazz,Object[] ids);
    
    /**
     * 根据主键查询
     * @param <T>
     * @param clazz  实体类的Class
     * @param id     主键
     * @return
     */
    public <T> T getById(Class<T> clazz,Object id);
    
    /**
     * 查询所有记录
     * @param <T>
     * @param clazz 实体类的Class
     * @return
     */
    public <T> List<T> getAll(Class<T> clazz);
    
    
    /**
     * 根据条件集合查询记录
     * @param <T>
     * @param clazz
     * @param queryConditions 查询条件集合
     * @param orderBy         排序,如 order by id desc
     * @param currentPage     当前页
     * @param pageSize        每页显示记录数
     * @return 
     */
    public <T> List<T> get(Class<T> clazz,List<QueryCondition> queryConditions,String orderBy,int currentPage,int pageSize);
    
    
    /**
     * 根据条件集合查询记录
     * @param <T>
     * @param clazz
     * @param queryConditions  查询条件集合
     * @return
     */
    public <T> List<T> get(Class<T> clazz,List<QueryCondition> queryConditions);
    
    /**
     * 根据条件集合查询记录
     * @param <T>
     * @param clazz
     * @param queryConditions  查询条件集合
     * @param orderBy          排序,如 order by id desc
     * @return
     */
    public <T> List<T> get(Class<T> clazz,List<QueryCondition> queryConditions,String orderBy);
    
    /**
     * 根据条件集合查询单条记录
     * @param clazz
     * @param queryConditions  查询条件集合
     * @return
     */
    @SuppressWarnings("rawtypes")
    public  Object getSingleResult(Class clazz,List<QueryCondition> queryConditions);
    
    /**
     * 根据条件查询记录数量
     * @param clazz
     * @param queryConditions  查询条件集合
     * @return
     */
    @SuppressWarnings("rawtypes")
    public long getRecordCount(Class clazz,List<QueryCondition> queryConditions);
    
    /**
     * 根据jpql查询
     * @param <T>
     * @param jpql
     * @param objects
     * @return
     */
    public <T> List<T> getByJpql(String jpql,Object...objects);
    
    /**
     * 执行jpql语句
     * @param jpql
     * @param objects
     * @return
     */
    public int executeJpql(String jpql,Object...objects);
    
    /**
     * 分页查询
     * @param <T>
     * @param clazz
     * @param queryConditions   查询条件集合
     * @param orderBy           排序字段 如：order by id desc
     * @param currentPage       当前页
     * @param pageSize          每页显示记录数
     * @return
     */
    public <T> Pagination<T> getPagination(Class<T> clazz,List<QueryCondition> queryConditions,String orderBy,int currentPage,int pageSize);
    
    /**
     * 查找唯一结果
     * @param jpql
     * @param objects
     * @return
     */
    public Object getUniqueResultByJpql(String jpql,Object...objects);
}