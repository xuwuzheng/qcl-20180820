package com.m5173.allureLove.service.impl;

import com.m5173.allureLove.common.base.BaseService;
import com.m5173.allureLove.dao.FeedbackEOMapper;
import com.m5173.allureLove.model.eo.FeedbackEO;
import com.m5173.allureLove.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 收货地址管理业务层
 *
 * @author 339789
 * @date 2018/4/27
 */
@Component
public class FeedbackServiceImpl extends BaseService<FeedbackEO> implements IFeedbackService {

    @Autowired
    private FeedbackEOMapper feedbackEOMapper;

    /**
     * 添加收货地址
     * @param feedbackEO 数据
     * @return 返回结果
     */
    @Override
    public Boolean addFeedback(FeedbackEO feedbackEO){
        feedbackEOMapper.insert(feedbackEO);
        return true;
    }

}
