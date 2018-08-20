package com.m5173.allureLove.service;

import com.github.pagehelper.PageInfo;
import com.m5173.allureLove.common.base.IBaseService;
import com.m5173.allureLove.model.eo.CategoryEO;
import com.m5173.allureLove.model.request.CategoryListRequest;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品类目管理
 * @author 340252
 * @date 2018/5/14
 */
public interface ICategoryService extends IBaseService<CategoryEO> {
    /**
     * 商品类目查询
     */
    PageInfo<CategoryEO> queryCategory(Example example, int pageNum, int pageSize, String orderBy, HttpServletRequest request);

    CategoryEO addCategory(CategoryListRequest categoryListRequest);

    Boolean deleteCategory(Long id);

}
