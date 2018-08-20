package com.m5173.allureLove.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.m5173.allureLove.common.base.BaseService;
import com.m5173.allureLove.common.util.BeanMapper;
import com.m5173.allureLove.dao.CategoryEOMapper;
import com.m5173.allureLove.dao.GoodsEOMapper;
import com.m5173.allureLove.model.eo.CategoryEO;
import com.m5173.allureLove.model.eo.GoodsEO;
import com.m5173.allureLove.model.request.GoodsListRequest;
import com.m5173.allureLove.service.IGoodsService;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;

/**
 * 商品管理业务层
 *
 * @author 339789
 * @date 2018/4/27
 */
@Component
public class GoodsServiceImpl extends BaseService<GoodsEO> implements IGoodsService {

    @Autowired
    private GoodsEOMapper goodsEOMapper;
    @Autowired
    private CategoryEOMapper categoryEOMapper;

    /**
     * 创建文档
     * @param goodsListRequest 接收参数
     * @return 返回结果
     */
    @Override
    public GoodsEO addDoc(GoodsListRequest goodsListRequest){
        CategoryEO categoryEO = categoryEOMapper.selectByPrimaryKey(goodsListRequest.getType());
        goodsListRequest.setCategoryName(categoryEO.getName());
        GoodsEO goodsEO = BeanMapper.map(goodsListRequest, GoodsEO.class);
        //判断是否有id，有id就执行修改操作
        if(goodsListRequest.getId() != null){
            goodsEOMapper.updateByPrimaryKey(goodsEO);
        }else {
            goodsEOMapper.insert(goodsEO);
        }
        return goodsEO;
    }

    /**
     * 查询商品列表
     * @return 返回结果
     */
    @Override
    public PageInfo<GoodsEO> queryGoods(Example example, int pageNum, int pageSize, String orderBy, HttpServletRequest request){
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<GoodsEO> goodsEOList = goodsEOMapper.selectByExample(example);
        return (PageInfo<GoodsEO>) new PageInfo(goodsEOList);
    }

    /**
     * 查询商品详情
     * @return 返回结果
     */
    @Override
    public GoodsEO selectGoodsDetail(HttpServletRequest request, Long id){
        GoodsEO goodsEO = goodsEOMapper.selectByPrimaryKey(id);
        return goodsEO;
    }

    /**
     * 删除文档
     * @param id 主键
     * @return 返回结果
     */
    @Override
    public Boolean deleteDoc(Long id){
        goodsEOMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public String getDoc(String cookie){
        try (DefaultHttpClient client = new DefaultHttpClient()) {
            String url = "http://zsk.5173.com/doc/query.do?page=1&rows=2000";
            HttpPost httpPost = new HttpPost(url);
            httpPost.setURI(URI.create(url));
            httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("Cookie", cookie);
            HttpResponse response = client.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return "获取文档数据发生未知异常(IO):" + e;
        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
            return "获取文档数据发生未知异常(UC):" + e;
        } catch (ParseException e) {
            e.printStackTrace();
            return "获取文档数据发生未知异常(P):" + e;
        }
    }

}
