package com.m5173.allureLove.common.base;


import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface IBaseService<T extends BaseEntity> {
    Long insert(T entity);

    boolean update(T entity);

    boolean deleteByPrimary(Long id);

    boolean delete(T entity);

    T selectById(Long id);

    List<T> selectAll();

    T selectOne(T record);

    List<T> select(T record);

    List<T> selectByExample(Example example);

    T selectOneByExample(Example example);

    int selectCount(T entity);

    boolean exists(Long id);
}
