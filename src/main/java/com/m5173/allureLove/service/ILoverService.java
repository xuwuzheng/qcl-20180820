package com.m5173.allureLove.service;

import com.github.pagehelper.PageInfo;
import com.m5173.allureLove.common.base.IBaseService;
import com.m5173.allureLove.model.eo.LoverEO;
import com.m5173.allureLove.model.request.LoverListRequest;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;

/**
 * 恋爱信息业务层
 *
 * @author 339664
 * @date 2018/5/14
 */
public interface ILoverService extends IBaseService<LoverEO> {

    /**
     * 创建恋爱信息
     * @param loverListRequest 接收参数
     * @return 返回结果
     */
    LoverEO addLover(LoverListRequest loverListRequest);

    /**
     * 查询恋爱信息列表
     * @return 返回结果
     */
    PageInfo<LoverEO> queryLover(Example example, int pageNum, int pageSize, String orderBy, HttpServletRequest request);

    /**
     * 查询恋爱信息详情
     * @param goodsId 查询条件
     * @return 返回结果
     */
    LoverEO selectLoverDetail(HttpServletRequest request, Long goodsId);

    /**
     * 删除恋爱信息
     * @param id 主键
     * @return 返回结果
     */
    Boolean deleteLover(Long id);

}
