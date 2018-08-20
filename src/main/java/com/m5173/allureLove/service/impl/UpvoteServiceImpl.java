package com.m5173.allureLove.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.m5173.allureLove.common.base.BaseService;
import com.m5173.allureLove.dao.UpvoteEOMapper;
import com.m5173.allureLove.model.eo.UpvoteEO;
import com.m5173.allureLove.service.IUpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 点赞管理业务层
 *
 * @author 339789
 * @date 2018/4/27
 */
@Component
public class UpvoteServiceImpl extends BaseService<UpvoteEO> implements IUpvoteService {

    @Autowired
    private UpvoteEOMapper upvoteEOMapper;

    /**
     * 查询点赞列表
     * @return 返回结果
     */
    @Override
    public PageInfo<UpvoteEO> queryUpvote(Example example, int pageNum, int pageSize, String orderBy, HttpServletRequest request){
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<UpvoteEO> upvoteEOList = upvoteEOMapper.selectByExample(example);
        return (PageInfo<UpvoteEO>) new PageInfo(upvoteEOList);
    }

}
