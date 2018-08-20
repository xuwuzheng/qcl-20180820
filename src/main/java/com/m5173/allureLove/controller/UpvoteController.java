package com.m5173.allureLove.controller;

import com.github.pagehelper.PageInfo;
import com.m5173.allureLove.common.base.BaseController;
import com.m5173.allureLove.common.base.BaseResponse;
import com.m5173.allureLove.common.base.IServiceResponse;
import com.m5173.allureLove.model.eo.GoodsEO;
import com.m5173.allureLove.model.eo.UpvoteEO;
import com.m5173.allureLove.model.request.GoodsListRequest;
import com.m5173.allureLove.service.IUpvoteService;
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
 * 商品接口管理
 *
 * @author 339664
 * @date 2018/5/14
 */
@RestController
@RequestMapping("upvote")
@Api(value = "点赞接口", description = "点赞接口")
public class UpvoteController extends BaseController {
    @Autowired
    private IUpvoteService upvoteService;

    /**
     * 点赞接口
     * @param goodsListRequest 接收参数
     * @return 返回结果
     */
    @PostMapping("/queryUpvote")
    @ApiOperation(value="点赞接口", notes="点赞接口")
    public IServiceResponse queryUpvote(@RequestBody GoodsListRequest goodsListRequest, HttpServletRequest request){
        Example example = new Example(GoodsEO.class);
        Example.Criteria criteria = example.createCriteria();
        if (goodsListRequest.getType() != null){
            criteria.andEqualTo("type",goodsListRequest.getType());
        }
        if (goodsListRequest.getPage() == null){
            goodsListRequest.setPage(1);
        }
        if (goodsListRequest.getPageSize() == null){
            goodsListRequest.setPageSize(100);
        }

        PageInfo<UpvoteEO> pageInfo = upvoteService.queryUpvote(example, goodsListRequest.getPage(), goodsListRequest.getPageSize(), "id asc",request);
        BaseResponse<PageInfo<UpvoteEO>> baseResponse = new BaseResponse<>();
        baseResponse.setData(pageInfo);
        LOGGER.info("点赞接口查询成功");
        return baseResponse;
    }

}
