package com.houde;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class PostTest {
    public static void main(String[] args) {
        String url = "https://pg.openepay.com/gateway/index.do";
        String params = "inputCharset=1&receiveUrl=https://115.28.185.37/2/rest/callback/ops&version=v1.0&language=1&signType=1&merchantId=105840170322005&orderNo=20170323172547744&orderAmount=11&orderCurrency=156&orderDatetime=20170323172547&orderExpireDatetime=300&productName=iOS Demo Live&ext1=9f3f317c-94e6-40fe-8309-a99714aebe9b&ext2=ea84dd57-2902-426f-88aa-45bd93126bf4&termId=IOS&payType=22&issuerId=alipay&signMsg=788B844198A6A556F9F8BCC99A960749";
        System.out.println("开始请求");
        System.out.println("url = "+url);
        System.out.println("params = "+params);
        String res = WebUtil.sendPost(url, params);
        System.out.println("返回值："+res);
        System.out.println("请求结束");
    }

}
