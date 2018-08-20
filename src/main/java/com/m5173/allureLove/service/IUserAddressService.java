package com.m5173.allureLove.service;

import com.m5173.allureLove.common.base.IBaseService;
import com.m5173.allureLove.model.eo.UserAddressEO;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * 收货地址业务层
 *
 * @author 339664
 * @date 2018/5/14
 */
public interface IUserAddressService extends IBaseService<UserAddressEO> {


    /**
     * 添加收货地址
     * @param userAddressEO 数据
     * @return 返回结果
     */
    Boolean addAddress(UserAddressEO userAddressEO);

    /**
     * 删除收货地址
     * @param id 主键
     * @return 返回结果
     */
    Boolean deleteAddress(Long id);

    /**
     * 查询收货地址列表
     * @return 返回结果
     */
    List<UserAddressEO> queryAddress(Example example, String orderBy);

    /**
     * 查询收货地址详情
     * @param addressId 查询条件
     * @return
     */
    Map<String, Object> selectAddress(Long addressId);
}
