package com.m5173.allureLove.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.m5173.allureLove.common.base.BaseService;
import com.m5173.allureLove.common.util.BeanMapper;
import com.m5173.allureLove.dao.CategoryEOMapper;
import com.m5173.allureLove.model.eo.CategoryEO;
import com.m5173.allureLove.model.request.CategoryListRequest;
import com.m5173.allureLove.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商品类目查询
 * Created by Administrator on 2018/5/14.
 */
@Component
public class CategoryServiceImpl  extends BaseService<CategoryEO> implements ICategoryService {
    @Autowired
    private CategoryEOMapper categoryEOMapper;

    /**
     * 商品类目查询
     * @return 返回结果
     */
    @Override
    public PageInfo<CategoryEO> queryCategory(Example example, int pageNum, int pageSize, String orderBy, HttpServletRequest request){
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<CategoryEO> goodsEOList = categoryEOMapper.selectByExample(example);
        return (PageInfo<CategoryEO>) new PageInfo(goodsEOList);
    }

    @Override
    public CategoryEO addCategory(CategoryListRequest categoryListRequest){
        CategoryEO categoryEO = BeanMapper.map(categoryListRequest, CategoryEO.class);
        //判断是否有id，有id就执行修改操作
        if(categoryListRequest.getId() != null){
            categoryEOMapper.updateByPrimaryKey(categoryEO);
        }else {
            categoryEOMapper.insert(categoryEO);
        }
        return categoryEO;
    }

    @Override
    public Boolean deleteCategory(Long id){
        categoryEOMapper.deleteByPrimaryKey(id);
        return true;
    }

}
