package com.m5173.allureLove;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTests {

	@Autowired
	private ICartService cartService;
	@Test
	public void selectCart() {
		Example example = new Example(CartEO.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("user_id","20");
		List<CartEO> cartList=cartService.queryCart(example);
		System.out.println(cartList);
	}

	@Test
	public void downImg() throws Exception{
		String json="{\"store\":{\"storeId\":30,\"storeName\":\" 建德新安东路店 \",\"storeAddr\":\" 建德市新安东路168号 \",\"areaId\":330100,\"sign\":\"1\",\"type\":1,\"lat\":29.486202,\"lng\":119.29614,\"isCollection\":0,\"data\":[]},\"activityBottom\":[{\"activityId\":1,\"activityName\":\"车衣\",\"activityLogo\":\"http://img.siyoulife.com/img/20160624/cheyi.png\",\"activityImg\":null,\"activityType\":2,\"activityTitle\":\"有备无患 车衣领导者\",\"activityImgBottom\":null,\"activityAbortTime\":null,\"replenishmentAbortTime\":null,\"replenishmentMemo\":null,\"attentionMemo\":null,\"unit\":null,\"price\":null,\"detailList\":null,\"executeDetailList\":null,\"list\":[]},{\"activityId\":2,\"activityName\":\"3D座垫\",\"activityLogo\":\"http://img.siyoulife.com/img/20160624/zupdian.png\",\"activityImg\":null,\"activityType\":2,\"activityTitle\":\"无与伦比的超高性价比\",\"activityImgBottom\":null,\"activityAbortTime\":null,\"replenishmentAbortTime\":null,\"replenishmentMemo\":null,\"attentionMemo\":null,\"unit\":null,\"price\":null,\"detailList\":null,\"executeDetailList\":null,\"list\":[]},{\"activityId\":3,\"activityName\":\"脚垫\",\"activityLogo\":\"http://img.siyoulife.com/img/20160624/jiaodian.png\",\"activityImg\":null,\"activityType\":2,\"activityTitle\":\"要定制 就现在\",\"activityImgBottom\":null,\"activityAbortTime\":null,\"replenishmentAbortTime\":null,\"replenishmentMemo\":null,\"attentionMemo\":null,\"unit\":null,\"price\":null,\"detailList\":null,\"executeDetailList\":null,\"list\":[]},{\"activityId\":14,\"activityName\":\"太阳膜\",\"activityLogo\":\"http://img.siyoulife.com/img/20160624/taiyangmo.png\",\"activityImg\":null,\"activityType\":2,\"activityTitle\":\"冰与火的感受\",\"activityImgBottom\":null,\"activityAbortTime\":null,\"replenishmentAbortTime\":null,\"replenishmentMemo\":null,\"attentionMemo\":null,\"unit\":null,\"price\":null,\"detailList\":null,\"executeDetailList\":null,\"list\":[]}],\"activityMiddel\":[{\"activityId\":23,\"activityName\":\"上线有礼\",\"activityLogo\":\"\",\"activityImg\":\"\",\"activityType\":2,\"activityTitle\":\"限时限量疯狂抢购中\",\"activityImgBottom\":\"\",\"activityAbortTime\":null,\"replenishmentAbortTime\":null,\"replenishmentMemo\":null,\"attentionMemo\":null,\"unit\":null,\"price\":null,\"detailList\":null,\"executeDetailList\":null,\"list\":[]},{\"activityId\":26,\"activityName\":\"3D座垫\",\"activityLogo\":\"\",\"activityImg\":\"\",\"activityType\":2,\"activityTitle\":\"超高性价比\",\"activityImgBottom\":\"\",\"activityAbortTime\":null,\"replenishmentAbortTime\":null,\"replenishmentMemo\":null,\"attentionMemo\":null,\"unit\":null,\"price\":null,\"detailList\":null,\"executeDetailList\":null,\"list\":[]},{\"activityId\":27,\"activityName\":\"测试-活动1\",\"activityLogo\":\"\",\"activityImg\":\"\",\"activityType\":1,\"activityTitle\":\"活动1测试\",\"activityImgBottom\":\"\",\"activityAbortTime\":null,\"replenishmentAbortTime\":null,\"replenishmentMemo\":null,\"attentionMemo\":null,\"unit\":null,\"price\":null,\"detailList\":null,\"executeDetailList\":null,\"list\":[]}],\"activityTop\":[{\"goodsCategoryId\":37,\"goodsCategoryName\":\"脚垫\",\"goodsCategoryType\":0,\"parentId\":1,\"isTop\":10,\"logoImg\":\"http://img.siyoulife.com/img/20160831/1472613223849074999.png\",\"mutexId\":null,\"memo\":null,\"contentUrl\":null,\"hasNext\":null,\"indexPosition\":null,\"logo\":\"http://img.siyoulife.com/img/20161128/1480299465549019729.png\"},{\"goodsCategoryId\":36,\"goodsCategoryName\":\"座垫\",\"goodsCategoryType\":0,\"parentId\":1,\"isTop\":9,\"logoImg\":\"http://img.siyoulife.com/img/item/goods_category/ioc-zuodian.png\",\"mutexId\":null,\"memo\":null,\"contentUrl\":null,\"hasNext\":null,\"indexPosition\":null,\"logo\":\"http://img.siyoulife.com/img/20161128/1480299417807013246.png\"},{\"goodsCategoryId\":146,\"goodsCategoryName\":\"美容\",\"goodsCategoryType\":2,\"parentId\":1,\"isTop\":8,\"logoImg\":\"http://img.siyoulife.com/img/20161010/1476065931282050444.png\",\"mutexId\":null,\"memo\":null,\"contentUrl\":null,\"hasNext\":null,\"indexPosition\":null,\"logo\":\"http://img.siyoulife.com/img/20161010/1476065931282050444.png\"},{\"goodsCategoryId\":127,\"goodsCategoryName\":\"太阳膜\",\"goodsCategoryType\":0,\"parentId\":1,\"isTop\":8,\"logoImg\":\"http://img.siyoulife.com/img/20161011/1476152159346033312.png\",\"mutexId\":null,\"memo\":null,\"contentUrl\":null,\"hasNext\":null,\"indexPosition\":null,\"logo\":\"http://img.siyoulife.com/img/20161128/1480299657406004446.png\"},{\"goodsCategoryId\":145,\"goodsCategoryName\":\"店长推荐\",\"goodsCategoryType\":0,\"parentId\":1,\"isTop\":7,\"logoImg\":\"http://img.siyoulife.com/img/item/goods_category/home%20page_Manager-recommended%20icon@3x.png\",\"mutexId\":null,\"memo\":null,\"contentUrl\":null,\"hasNext\":null,\"indexPosition\":null,\"logo\":\"http://img.siyoulife.com/img/item/goods_category/home%20page_Manager-recommended%20icon@3x.png\"},{\"goodsCategoryId\":147,\"goodsCategoryName\":\"油漆\",\"goodsCategoryType\":3,\"parentId\":1,\"isTop\":7,\"logoImg\":\"http://img.siyoulife.com/img/20161010/1476065984642018499.png\",\"mutexId\":null,\"memo\":null,\"contentUrl\":null,\"hasNext\":null,\"indexPosition\":null,\"logo\":\"http://img.siyoulife.com/img/20161010/1476065984642018499.png\"},{\"goodsCategoryId\":91,\"goodsCategoryName\":\"大小保养\",\"goodsCategoryType\":1,\"parentId\":1,\"isTop\":6,\"logoImg\":\"http://img.siyoulife.com/img/20161013/1476357481187011525.png\",\"mutexId\":null,\"memo\":null,\"contentUrl\":null,\"hasNext\":null,\"indexPosition\":null,\"logo\":\"http://img.siyoulife.com/img/20161013/1476357481187011525.png\"},{\"goodsCategoryId\":124,\"goodsCategoryName\":\"车衣\",\"goodsCategoryType\":0,\"parentId\":9,\"isTop\":5,\"logoImg\":\"http://img.siyoulife.com/img/20160831/1472614547677082978.png\",\"mutexId\":null,\"memo\":null,\"contentUrl\":null,\"hasNext\":null,\"indexPosition\":null,\"logo\":\"http://img.siyoulife.com/img/item/goods_category/home%20page_car-cover%20icon@3x.png\"},{\"goodsCategoryId\":141,\"goodsCategoryName\":\"雨刷\",\"goodsCategoryType\":0,\"parentId\":10,\"isTop\":4,\"logoImg\":\"http://img.siyoulife.com/img/20160831/1472613116356055808.png\",\"mutexId\":null,\"memo\":null,\"contentUrl\":null,\"hasNext\":null,\"indexPosition\":null,\"logo\":\"http://img.siyoulife.com/img/20160831/1472613116356055808.png\"},{\"goodsCategoryId\":125,\"goodsCategoryName\":\"太阳挡\",\"goodsCategoryType\":0,\"parentId\":9,\"isTop\":4,\"logoImg\":\"http://img.siyoulife.com/img/20160831/1472614845488035966.png\",\"mutexId\":null,\"memo\":null,\"contentUrl\":null,\"hasNext\":null,\"indexPosition\":null,\"logo\":\"http://img.siyoulife.com/img/item/goods_category/home%20page_Sun-block%20icon@3x.png\"}]}";
		JSONObject result=JSONObject.parseObject(json);
		JSONArray activityBottoms=result.getJSONArray("activityBottom");
		//activityBottom
		for(int i=0,aszie=activityBottoms.size();i<aszie;i++){
			JSONObject activityBottom=activityBottoms.getJSONObject(i);
			String activityLogo=activityBottom.getString("activityLogo");
			String filename="A"+activityBottom.getInteger("activityId")+".png";
			String savePath="D:\\appImgs\\activityLogo\\";
			download(activityLogo,filename,savePath);
		}
		//activityTop
		JSONArray activityTops=result.getJSONArray("activityTop");
		for(int i=0,aszie=activityTops.size();i<aszie;i++){
			JSONObject activityTop=activityTops.getJSONObject(i);
			String logo=activityTop.getString("logo");
			String logofilename="L"+activityTop.getInteger("goodsCategoryId")+".png";
			String savePath="D:\\appImgs\\activityTop\\";
			download(logo,logofilename,savePath);
			String logoImg=activityTop.getString("logoImg");
			String llogoImgfilename="M"+activityTop.getInteger("goodsCategoryId")+".png";
			download(logoImg,llogoImgfilename,savePath);
		}

	}

	private void download(String urlString, String filename,String savePath) throws Exception{
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		//设置请求超时为5s
		con.setConnectTimeout(10*1000);
		// 输入流
		InputStream is = con.getInputStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf=new File(savePath);
		if(!sf.exists()){
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}

}
