package com.aloneness.itoken.common.service;

import com.aloneness.itoken.common.domain.BaseDomain;
import com.github.pagehelper.PageInfo;

public interface BaseService <T extends BaseDomain> {

    int insert(T t, String createBy);

    int update(T t, String updateBy);

    int delete(T t);

    int count(T t);

    T selectOne(T t);

    PageInfo<T> page(int pageNumber, int pageSize, T t);
}
