package com.m5173.allureLove.controller;

import com.m5173.allureLove.common.base.BaseController;
import com.m5173.allureLove.common.base.BaseResponse;
import com.m5173.allureLove.common.base.IServiceResponse;
import com.m5173.allureLove.common.enums.ResponseCodes;
import com.m5173.allureLove.common.exception.BusinessException;
import com.m5173.allureLove.model.eo.FeedbackEO;
import com.m5173.allureLove.model.request.FeedbackRequest;
import com.m5173.allureLove.service.IFeedbackService;
import com.m5173.allureLove.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 反馈接口管理
 *
 * @author 339664
 * @date 2018/5/14
 */
@RestController
@RequestMapping("extras")
@Api(value = "反馈接口", description = "反馈接口")
public class FeedbackController extends BaseController {
    @Autowired
    private IFeedbackService feedbackService;
    @Autowired
    private IUserService userService;

    /**
     * 添加反馈
     * @param feedbackRequest 请求参数
     * @return 返回结果
     */
    @PostMapping("/addFeedBack")
    @ApiOperation(value="添加反馈", notes="添加反馈")
    public IServiceResponse addAddress(@RequestBody FeedbackRequest feedbackRequest){
        userService.userInfo(feedbackRequest.getUserToken());

        if (StringUtils.isBlank(feedbackRequest.getContent())){
            throw new BusinessException(ResponseCodes.EmptyInfo,"反馈内容");
        }

        FeedbackEO feedbackEO = new FeedbackEO();
        feedbackEO.setContent(feedbackRequest.getContent());
        feedbackEO.setUserId(feedbackRequest.getUserId());
        feedbackEO.setCreateTime(new Date());
        Boolean result = feedbackService.addFeedback(feedbackEO);
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        baseResponse.setData(result);
        LOGGER.info("添加反馈成功");
        return baseResponse;
    }

}
