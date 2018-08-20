package com.m5173.allureLove.controller;

import com.github.pagehelper.PageInfo;
import com.m5173.allureLove.common.base.BaseController;
import com.m5173.allureLove.common.base.BaseResponse;
import com.m5173.allureLove.common.base.IServiceResponse;
import com.m5173.allureLove.common.enums.ResponseCodes;
import com.m5173.allureLove.common.exception.BusinessException;
import com.m5173.allureLove.model.eo.CategoryEO;
import com.m5173.allureLove.model.request.CategoryListRequest;
import com.m5173.allureLove.model.request.GoodsListRequest;
import com.m5173.allureLove.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;

/**
 * 类目查询
 * @author 340252
 * @date 2018/5/14
 */
@RestController
@RequestMapping("/category")
@Api(value = "类目查询接口", description = "类目查询接口")
public class CategoryController extends BaseController {

    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询类目
     * @return 返回结果
     */
    @PostMapping("/queryCategory")
    @ApiOperation(value="类目查询", notes="类目查询")
    public IServiceResponse queryCategory(@RequestBody CategoryListRequest categoryListRequest, HttpServletRequest request){
        Example example = new Example(CategoryEO.class);
        Example.Criteria criteria = example.createCriteria();
        if (categoryListRequest.getQueryKey() != null) {
            criteria.orLike("name", "%" + categoryListRequest.getQueryKey() + "%");
        }
        if (categoryListRequest.getPage() == null){
            categoryListRequest.setPage(1);
        }
        if (categoryListRequest.getPageSize() == null){
            categoryListRequest.setPageSize(10);
        }

        PageInfo<CategoryEO> pageInfo = categoryService.queryCategory(example, categoryListRequest.getPage(), categoryListRequest.getPageSize(), "id asc",request);
        BaseResponse<PageInfo<CategoryEO>> baseResponse = new BaseResponse<>();
        baseResponse.setData(pageInfo);
        LOGGER.info("类目查询成功");
        return baseResponse;
    }

    /**
     * 添加类目
     * @param categoryListRequest 接收参数
     * @return 返回结果
     */
    @PostMapping("/addCategory")
    @ApiOperation(value="添加类目", notes="添加类目")
    public IServiceResponse addCategory(@RequestBody CategoryListRequest categoryListRequest){
        CategoryEO categoryEO = categoryService.addCategory(categoryListRequest);
        BaseResponse<CategoryEO> baseResponse = new BaseResponse<>();
        baseResponse.setData(categoryEO);
        return baseResponse;
    }

    /**
     * 删除类目
     * @param goodsListRequest 请求参数
     * @return 返回结果
     */
    @PostMapping("/deleteCategory")
    @ApiOperation(value="删除类目", notes="删除类目")
    public IServiceResponse deleteCategory(@RequestBody GoodsListRequest goodsListRequest){
        if (goodsListRequest.getId() == null){
            throw new BusinessException(ResponseCodes.EmptyInfo,"id");
        }
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        baseResponse.setData(categoryService.deleteCategory(Long.valueOf(goodsListRequest.getId())));
        LOGGER.info("删除类目成功");
        return baseResponse;
    }
}
