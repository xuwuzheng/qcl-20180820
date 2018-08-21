package com.m5173.allureLove.controller;

import com.m5173.allureLove.common.base.BaseController;
import com.m5173.allureLove.common.base.BaseResponse;
import com.m5173.allureLove.common.base.IServiceResponse;
import com.m5173.allureLove.common.enums.ResponseCodes;
import com.m5173.allureLove.common.exception.BusinessException;
import com.m5173.allureLove.common.util.BeanMapper;
import com.m5173.allureLove.model.eo.UserAddressEO;
import com.m5173.allureLove.model.request.UserAddressRequest;
import com.m5173.allureLove.service.IUserAddressService;
import com.m5173.allureLove.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * 收货地址接口管理
 *
 * @author 339664
 * @date 2018/5/14
 */
@RestController
@Api(value = "收货地址接口", description = "收货地址接口")
public class UserAddressController extends BaseController {
    @Autowired
    private IUserAddressService userAddressService;
    @Autowired
    private IUserService userService;

    /**
     * 添加收货地址
     * @param userAddressRequest 请求参数
     * @return 返回结果
     */
    @PostMapping("/user/addAddress")
    @ApiOperation(value="添加收货地址", notes="添加收货地址")
    public IServiceResponse addAddress(@RequestBody UserAddressRequest userAddressRequest){
        userService.userInfo(userAddressRequest.getUserToken());

        if (StringUtils.isBlank(userAddressRequest.getConsignee())){
            throw new BusinessException(ResponseCodes.EmptyInfo,"收货名称");
        }
        if (StringUtils.isBlank(userAddressRequest.getDetail())){
            throw new BusinessException(ResponseCodes.EmptyInfo,"详情地址");
        }
        if (StringUtils.isBlank(userAddressRequest.getPhone())){
            throw new BusinessException(ResponseCodes.EmptyInfo,"联系电话");
        }
        UserAddressEO userAddressEO = BeanMapper.map(userAddressRequest, UserAddressEO.class);
        Boolean result = userAddressService.addAddress(userAddressEO);
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        baseResponse.setData(result);
        LOGGER.info("添加收货地址成功");
        return baseResponse;
    }

    /**
     * 查询收货地址
     * @param userAddressRequest 请求参数
     * @return 返回结果
     */
    @PostMapping("/user/editAddress")
    @ApiOperation(value="查询收货地址", notes="查询收货地址")
    public IServiceResponse editAddress(@RequestBody UserAddressRequest userAddressRequest){
        userService.userInfo(userAddressRequest.getUserToken());
        if (userAddressRequest.getAddressId() == null){
            throw new BusinessException(ResponseCodes.EmptyInfo, "id");
        }
        BaseResponse<Map<String, Object>> baseResponse = new BaseResponse<>();
        baseResponse.setData(userAddressService.selectAddress(Long.valueOf(userAddressRequest.getAddressId())));
        LOGGER.info("收货地址详情查询成功,id:"+userAddressRequest.getAddressId());
        return baseResponse;
    }

    /**
     * 删除收货地址
     * @param userAddressRequest 请求参数
     * @return 返回结果
     */
    @PostMapping("/user/deleteAddress")
    @ApiOperation(value="删除收货地址", notes="删除收货地址")
    public IServiceResponse deleteAddress(@RequestBody UserAddressRequest userAddressRequest){
        if (userAddressRequest.getAddressId() == null){
            throw new BusinessException(ResponseCodes.EmptyInfo,"id");
        }
        userService.userInfo(userAddressRequest.getUserToken());
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        baseResponse.setData(userAddressService.deleteAddress(Long.valueOf(userAddressRequest.getAddressId())));
        LOGGER.info("删除收货地址成功");
        return baseResponse;
    }

    /**
     * 查询收货地址列表
     * @param userAddressRequest 请求参数
     * @return 返回结果
     */
    @PostMapping("/user/address")
    @ApiOperation(value="查询收货地址列表", notes="查询收货地址列表")
    public IServiceResponse queryAddress(@RequestBody UserAddressRequest userAddressRequest){
        userService.userInfo(userAddressRequest.getUserToken());
        Example example = new Example(UserAddressEO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userAddressRequest.getUserId());
        BaseResponse<List<UserAddressEO>> baseResponse = new BaseResponse<>();
        baseResponse.setData(userAddressService.selectByExample(example));
        LOGGER.info("收货地址分页查询成功");
        return baseResponse;
    }

}
