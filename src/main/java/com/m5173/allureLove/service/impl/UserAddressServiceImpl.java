package com.m5173.allureLove.service.impl;

import com.github.pagehelper.PageHelper;
import com.m5173.allureLove.common.base.BaseService;
import com.m5173.allureLove.dao.UserAddressEOMapper;
import com.m5173.allureLove.model.eo.UserAddressEO;
import com.m5173.allureLove.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收货地址管理业务层
 *
 * @author 339789
 * @date 2018/4/27
 */
@Component
public class UserAddressServiceImpl extends BaseService<UserAddressEO> implements IUserAddressService {

    @Autowired
    private UserAddressEOMapper userAddressEOMapper;

    /**
     * 添加收货地址
     * @param userAddressEO 数据
     * @return 返回结果
     */
    @Override
    public Boolean addAddress(UserAddressEO userAddressEO){
        this.updateUserAddress(userAddressEO);
        if(userAddressEO.getAddressId() == null){
            userAddressEOMapper.insert(userAddressEO);
        }else{
            userAddressEOMapper.updateByPrimaryKey(userAddressEO);
        }

        return true;
    }

    /**
     * 删除收货地址
     * @param id 主键
     * @return 返回结果
     */
    @Override
    public Boolean deleteAddress(Long id){
        userAddressEOMapper.deleteByPrimaryKey(id);
        return true;
    }

    /**
     * 查询收货地址列表
     * @param example 查询条件
     * @return 返回结果
     */
    @Override
    public List<UserAddressEO> queryAddress(Example example, String orderBy){
        PageHelper.orderBy(orderBy);
        return userAddressEOMapper.selectByExample(example);
    }

    /**
     * 查询收货地址详情
     * @return 返回结果
     */
    @Override
    public Map<String, Object> selectAddress(Long addressId){
        UserAddressEO userAddressEO = userAddressEOMapper.selectByPrimaryKey(addressId);
        Map<String, Object> address = new HashMap<>();
        address.put("address", userAddressEO);
        address.put("districtList", new ArrayList<>());
        address.put("cityList", new ArrayList<>());
        address.put("provinceList", new ArrayList<>());
        return address;
    }

    private void updateUserAddress(UserAddressEO userAddressEO){
        //如果此收货地址为默认，则其他地址设为false
        if (userAddressEO.getIsDefault()){
            Example example = new Example(UserAddressEO.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId",userAddressEO.getUserId());
            UserAddressEO userAddressEO1 = new UserAddressEO();
            userAddressEO1.setIsDefault(false);
            userAddressEOMapper.updateByExampleSelective(userAddressEO1,example);
        }
    }
}
