package com.m5173.allureLove.service;

import com.github.pagehelper.PageInfo;
import com.m5173.allureLove.common.base.IBaseService;
import com.m5173.allureLove.model.eo.GoodsEO;
import com.m5173.allureLove.model.request.GoodsListRequest;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品业务层
 *
 * @author 339664
 * @date 2018/5/14
 */
public interface IGoodsService extends IBaseService<GoodsEO> {

    /**
     * 创建文档
     * @param goodsListRequest 接收参数
     * @return 返回结果
     */
    GoodsEO addDoc(GoodsListRequest goodsListRequest);

    /**
     * 查询商品列表
     * @return 返回结果
     */
    PageInfo<GoodsEO> queryGoods(Example example, int pageNum, int pageSize, String orderBy, HttpServletRequest request);

    /**
     * 查询商品详情
     * @param goodsId 查询条件
     * @return 返回结果
     */
    GoodsEO selectGoodsDetail(HttpServletRequest request, Long goodsId);

    /**
     * 删除文档
     * @param id 主键
     * @return 返回结果
     */
    Boolean deleteDoc(Long id);

    String getDoc(String cookie);
}
