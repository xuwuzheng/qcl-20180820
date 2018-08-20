package com.m5173.allureLove.service;

import com.github.pagehelper.PageInfo;
import com.m5173.allureLove.common.base.IBaseService;
import com.m5173.allureLove.model.eo.UpvoteEO;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;

/**
 * 点赞业务层
 *
 * @author 339664
 * @date 2018/5/14
 */
public interface IUpvoteService extends IBaseService<UpvoteEO> {


    /**
     * 查询点赞列表
     * @return 返回结果
     */
    PageInfo<UpvoteEO> queryUpvote(Example example, int pageNum, int pageSize, String orderBy, HttpServletRequest request);

}
