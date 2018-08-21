package com.m5173.allureLove.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.m5173.allureLove.common.base.BaseService;
import com.m5173.allureLove.common.util.BeanMapper;
import com.m5173.allureLove.dao.LoverEOMapper;
import com.m5173.allureLove.model.eo.LoverEO;
import com.m5173.allureLove.model.eo.UserEO;
import com.m5173.allureLove.model.request.LoverListRequest;
import com.m5173.allureLove.service.ILoverService;
import com.m5173.allureLove.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 恋爱信息管理业务层
 *
 * @author 339789
 * @date 2018/4/27
 */
@Component
public class LoverServiceImpl extends BaseService<LoverEO> implements ILoverService {

    @Autowired
    private LoverEOMapper loverEOMapper;
    @Autowired
    private IUserService userService;

    /**
     * 创建修改恋爱信息
     * @param loverListRequest 接收参数
     * @return 返回结果
     */
    @Override
    public LoverEO addLover(LoverListRequest loverListRequest){
        UserEO userEO = userService.userInfo(loverListRequest.getUserToken());
        LoverEO loverEO = BeanMapper.map(loverListRequest, LoverEO.class);
        //判断是否有id，有id就执行修改操作
        if(loverListRequest.getId() != null){
            loverEO.setLastUpdateTime(new Date());
            loverEOMapper.updateByPrimaryKey(loverEO);
        }else {
            loverEO.setLikeNumber(0);
            loverEO.setPopular(0);
            loverEO.setUserId(userEO.getUserId());
            loverEO.setLastUpdateTime(new Date());
            loverEO.setCreateTime(new Date());
            loverEOMapper.insert(loverEO);
        }
        return loverEO;
    }

    /**
     * 查询恋爱信息列表
     * @return 返回结果
     */
    @Override
    public PageInfo<LoverEO> queryLover(Example example, int pageNum, int pageSize, String orderBy, HttpServletRequest request){
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<LoverEO> goodsEOList = loverEOMapper.selectByExample(example);
        for (LoverEO loverEO : goodsEOList){
            loverEO.setUserId(null);
        }
        return (PageInfo<LoverEO>) new PageInfo(goodsEOList);
    }

    /**
     * 查询恋爱信息详情
     * @return 返回结果
     */
    @Override
    public LoverEO selectLoverDetail(Long id){
        LoverEO loverEO = loverEOMapper.selectByPrimaryKey(id);
        loverEO.setUserId(null);
        return loverEO;
    }

    /**
     * 删除恋爱信息
     * @param loverListRequest 主键
     * @return 返回结果
     */
    @Override
    public Boolean deleteLover(LoverListRequest loverListRequest){
        userService.userInfo(loverListRequest.getUserToken());
        loverEOMapper.deleteByPrimaryKey(Long.valueOf(loverListRequest.getId()));
        return true;
    }

}
