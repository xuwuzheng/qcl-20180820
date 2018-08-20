package com.m5173.allureLove.service;

import com.m5173.allureLove.common.base.IBaseService;
import com.m5173.allureLove.model.eo.FeedbackEO;

/**
 * 反馈业务层
 *
 * @author 339664
 * @date 2018/5/14
 */
public interface IFeedbackService extends IBaseService<FeedbackEO> {


    /**
     * 添加反馈
     * @param feedbackEO 数据
     * @return 返回结果
     */
    Boolean addFeedback(FeedbackEO feedbackEO);
}
