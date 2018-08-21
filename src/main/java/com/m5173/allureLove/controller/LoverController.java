package com.m5173.allureLove.controller;

import com.github.pagehelper.PageInfo;
import com.m5173.allureLove.common.base.BaseController;
import com.m5173.allureLove.common.base.BaseResponse;
import com.m5173.allureLove.common.base.IServiceResponse;
import com.m5173.allureLove.common.enums.ResponseCodes;
import com.m5173.allureLove.common.exception.BusinessException;
import com.m5173.allureLove.model.eo.LoverEO;
import com.m5173.allureLove.model.request.LoverListRequest;
import com.m5173.allureLove.service.ILoverService;
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
 * 恋爱信息接口管理
 *
 * @author 339664
 * @date 2018/5/14
 */
@RestController
@RequestMapping("lover")
@Api(value = "恋爱信息接口", description = "恋爱信息接口")
public class LoverController extends BaseController {
    @Autowired
    private ILoverService loverService;


    /**
     * 创建修改恋爱信息
     * @param loverListRequest 接收参数
     * @return 返回结果
     */
    @PostMapping("/addLover")
    @ApiOperation(value="创建修改恋爱信息", notes="创建修改恋爱信息")
    public IServiceResponse addLover(@RequestBody LoverListRequest loverListRequest){

        LoverEO loverEO = loverService.addLover(loverListRequest);
        BaseResponse<LoverEO> baseResponse = new BaseResponse<>();
        LOGGER.info("创建修改恋爱信息成功:"+loverEO);
        return baseResponse;
    }

    /**
     * 查询恋爱信息详情
     * @param loverListRequest 接收参数
     * @return 返回结果
     */
    @PostMapping("/loverDetail")
    @ApiOperation(value="查询恋爱信息详情", notes="查询恋爱信息详情")
    public IServiceResponse selectLoverDetail(@RequestBody LoverListRequest loverListRequest){
        if (loverListRequest.getId() != null){
            throw new BusinessException(ResponseCodes.EmptyInfo,"查询id");
        }
        LoverEO loverEO = loverService.selectLoverDetail(Long.valueOf(loverListRequest.getId()));
        BaseResponse<LoverEO> baseResponse = new BaseResponse<>();
        baseResponse.setData(loverEO);
        LOGGER.info("恋爱信息详情查询成功,getId:{}"+loverListRequest.getId());
        return baseResponse;
    }
    /**
     * 搜索恋爱信息接口
     * @param loverListRequest 接收参数
     * @return 返回结果
     */
    @PostMapping("/goods")
    @ApiOperation(value="搜索恋爱信息接口", notes="搜索恋爱信息接口")
    public IServiceResponse searchGoods(@RequestBody LoverListRequest loverListRequest, HttpServletRequest request){
        Example example = new Example(LoverEO.class);
        Example.Criteria criteria = example.createCriteria();
        if (loverListRequest.getQueryKey() != null) {
            criteria.orLike("nike", "%" + loverListRequest.getQueryKey() + "%");
            criteria.orLike("detail", "%" + loverListRequest.getQueryKey() + "%");
            criteria.orLike("tag", "%" + loverListRequest.getQueryKey() + "%");
            criteria.orLike("property", "%" + loverListRequest.getQueryKey() + "%");
        }
        if(loverListRequest.getSex() != null){
            criteria.andEqualTo("sex",""+loverListRequest.getSex()+"");
        }
        if (loverListRequest.getPage() == null){
            loverListRequest.setPage(1);
        }
        if (loverListRequest.getPageSize() == null){
            loverListRequest.setPageSize(10);
        }

        PageInfo<LoverEO> pageInfo = loverService.queryLover(example, loverListRequest.getPage(), loverListRequest.getPageSize(), loverListRequest.getSortKey(), request);
        BaseResponse<PageInfo<LoverEO>> baseResponse = new BaseResponse<>();
        baseResponse.setData(pageInfo);
        LOGGER.info("搜索恋爱信息接口查询成功");
        return baseResponse;
    }

    /**
     * 删除恋爱信息
     * @param loverListRequest 请求参数
     * @return 返回结果
     */
    @PostMapping("/delLover")
    @ApiOperation(value="删除恋爱信息", notes="删除恋爱信息")
    public IServiceResponse delLover(@RequestBody LoverListRequest loverListRequest){
        if (loverListRequest.getId() == null){
            throw new BusinessException(ResponseCodes.EmptyInfo,"id");
        }
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        baseResponse.setData(loverService.deleteLover(loverListRequest));
        LOGGER.info("删除恋爱信息成功,id:{}",loverListRequest.getId());
        return baseResponse;
    }

}
