package com.m5173.allureLove.controller;

import com.github.pagehelper.PageInfo;
import com.m5173.allureLove.common.base.BaseController;
import com.m5173.allureLove.common.base.BaseResponse;
import com.m5173.allureLove.common.base.IServiceResponse;
import com.m5173.allureLove.common.enums.ResponseCodes;
import com.m5173.allureLove.common.exception.BusinessException;
import com.m5173.allureLove.common.util.ExportExcel;
import com.m5173.allureLove.model.eo.GoodsEO;
import com.m5173.allureLove.model.eo.OldDocInfo;
import com.m5173.allureLove.model.request.CookieRequest;
import com.m5173.allureLove.model.request.GoodsListRequest;
import com.m5173.allureLove.service.IGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 商品接口管理
 *
 * @author 339664
 * @date 2018/5/14
 */
@RestController
@RequestMapping("doc")
@Api(value = "商品接口", description = "商品接口")
public class GoodsController extends BaseController {
    @Autowired
    private IGoodsService goodsService;

    private static final String FILES_EXPORT_PATH = "/exl";

    /**
     * 创建文档
     * @param goodsListRequest 接收参数
     * @return 返回结果
     */
    @PostMapping("/addDoc")
    @ApiOperation(value="创建文档", notes="创建文档")
    public IServiceResponse addDoc(@RequestBody GoodsListRequest goodsListRequest){
        GoodsEO goodsEO = goodsService.addDoc(goodsListRequest);
        BaseResponse<GoodsEO> baseResponse = new BaseResponse<>();
        baseResponse.setData(goodsEO);
        return baseResponse;
    }

    /**
     * 查询商品详情
     * @param goodsListRequest 接收参数
     * @param request 请求头
     * @return 返回结果
     */
    @PostMapping("/docDetail")
    @ApiOperation(value="查询商品详情", notes="查询商品详情")
    public IServiceResponse selectGoodsDetail(@RequestBody GoodsListRequest goodsListRequest, HttpServletRequest request){
        if (StringUtils.isBlank(goodsListRequest.getId())){
            throw new BusinessException(ResponseCodes.EmptyInfo,"查询id");
        }
        GoodsEO goodsEO = goodsService.selectGoodsDetail(request, Long.valueOf(goodsListRequest.getId()));
        BaseResponse<GoodsEO> baseResponse = new BaseResponse<>();
        baseResponse.setData(goodsEO);
        LOGGER.info("商品详情查询成功,getId:"+goodsListRequest.getId());
        return baseResponse;
    }
    /**
     * 搜索商品接口
     * @param goodsListRequest 接收参数
     * @return 返回结果
     */
    @PostMapping("/goods")
    @ApiOperation(value="搜索商品接口", notes="搜索商品接口")
    public IServiceResponse searchGoods(@RequestBody GoodsListRequest goodsListRequest, HttpServletRequest request){
        Example example = new Example(GoodsEO.class);
        Example.Criteria criteria = example.createCriteria();
        if (goodsListRequest.getQueryKey() != null) {
            criteria.orLike("title", "%" + goodsListRequest.getQueryKey() + "%");
            criteria.orLike("content", "%" + goodsListRequest.getQueryKey() + "%");
            criteria.orLike("pcontent", "%" + goodsListRequest.getQueryKey() + "%");
        }
        if (goodsListRequest.getType() != null){
            criteria.andEqualTo("type",goodsListRequest.getType());
        }
        if (goodsListRequest.getPage() == null){
            goodsListRequest.setPage(1);
        }
        if (goodsListRequest.getPageSize() == null){
            goodsListRequest.setPageSize(10);
        }

        PageInfo<GoodsEO> pageInfo = goodsService.queryGoods(example, goodsListRequest.getPage(), goodsListRequest.getPageSize(), "id asc",request);
        BaseResponse<PageInfo<GoodsEO>> baseResponse = new BaseResponse<>();
        baseResponse.setData(pageInfo);
        LOGGER.info("搜索商品接口查询成功");
        return baseResponse;
    }

    /**
     * 删除文档
     * @param goodsListRequest 请求参数
     * @return 返回结果
     */
    @PostMapping("/deleteDoc")
    @ApiOperation(value="删除文档", notes="删除文档")
    public IServiceResponse deleteDoc(@RequestBody GoodsListRequest goodsListRequest){
        if (goodsListRequest.getId() == null){
            throw new BusinessException(ResponseCodes.EmptyInfo,"id");
        }
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        baseResponse.setData(goodsService.deleteDoc(Long.valueOf(goodsListRequest.getId())));
        LOGGER.info("删除文档成功");
        return baseResponse;
    }

    /**
     * 获取文档数据
     * @param cookieRequest 请求参数
     * @return 返回结果
     */
    @PostMapping("/getDoc")
    @ApiOperation(value="获取文档数据", notes="获取文档数据")
    public IServiceResponse getDoc(@RequestBody CookieRequest cookieRequest){
        if (cookieRequest.getCookie() == null){
            throw new BusinessException(ResponseCodes.EmptyInfo,"cookie");
        }
        String str = goodsService.getDoc(cookieRequest.getCookie());
        JSONObject jsonObject = JSONObject.fromObject(str);
        JSONArray array = JSONArray.fromObject(jsonObject.getString("rows"));
        Object[] obj = new Object[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject1 = array.getJSONObject(i);
            obj[i] = JSONObject.toBean(jsonObject1, OldDocInfo.class);
        }
        BaseResponse<Object[]> baseResponse = new BaseResponse<>();
        baseResponse.setData(obj);
        baseResponse.setResult(exportDocList(array));
        LOGGER.info("获取文档数据成功");
        return baseResponse;
    }

    public String exportDocList(JSONArray array) {

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet();

        ExportExcel exportExcel = new ExportExcel(wb, sheet);

        // 创建单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();

        // 指定单元格居中对齐
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 指定单元格垂直居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 指定当单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);

        // 创建报表头部
        String headString = "知识库统计";
        int columnSize = 8;
        exportExcel.createNormalHead(0, headString, columnSize - 1);

        // 创建报表列
        String[] columHeader = new String[]{"标题", "内容类型", "分类", "作者", "访问数","热度" ,"好评","差评","评价","评论数"};

        exportExcel.createColumHeader(1, columHeader);

        HSSFCellStyle cellstyle = wb.createCellStyle();
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);

        // 循环创建中间的单元格的各项的值
        if (array != null) {
            int i = 2;
            for (int j = 0; j < array.size(); j++) {
                JSONObject jsonObject1 = array.getJSONObject(j);
                HSSFRow row = sheet.createRow((short) i++);
                exportExcel.createCell(wb, row, 0, CellStyle.ALIGN_LEFT, jsonObject1.getString("TITLE"));
                exportExcel.createCell(wb, row, 1, CellStyle.ALIGN_LEFT, jsonObject1.getString("DOCDESCRIBE"));
                exportExcel.createCell(wb, row, 2, CellStyle.ALIGN_LEFT, jsonObject1.getString("TYPENAME"));
                exportExcel.createCell(wb, row, 3, CellStyle.ALIGN_LEFT, jsonObject1.getString("AUTHOR"));
                exportExcel.createCell(wb, row, 4, CellStyle.ALIGN_LEFT, jsonObject1.getString("VISITNUM"));
                exportExcel.createCell(wb, row, 5, CellStyle.ALIGN_LEFT, jsonObject1.getString("HOTNUM"));
                exportExcel.createCell(wb, row, 6, CellStyle.ALIGN_LEFT, jsonObject1.getString("PRAISEYES"));
                exportExcel.createCell(wb, row, 7, CellStyle.ALIGN_LEFT, jsonObject1.getString("PRAISENO"));
                exportExcel.createCell(wb, row, 8, CellStyle.ALIGN_LEFT, jsonObject1.getString("EVALUATE"));
                exportExcel.createCell(wb, row, 9, CellStyle.ALIGN_LEFT, jsonObject1.getString("ANSWERINGNUM"));
            }
        }
        //导出的Excel列宽自适应(超过1700条报错)
        for (int i = 0; i <columHeader.length ; i++) {
            sheet.autoSizeColumn(i,true);
        }
//        File file = new File(FILES_EXPORT_PATH);
//        file.mkdirs();
//        String filePath = FILES_EXPORT_PATH + "/" + UUID.randomUUID().toString() + ".xls";
//        exportExcel.outputExcel(filePath);
//        return filePath;
        String filePath = null;
        try {
            FileSystemView fsv = FileSystemView.getFileSystemView();
            File com=fsv.getHomeDirectory();  //桌面
            filePath = FILES_EXPORT_PATH + "/知识库统计-" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + ".xls";
            FileOutputStream fout = new FileOutputStream(filePath);
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return filePath;
    }

}
