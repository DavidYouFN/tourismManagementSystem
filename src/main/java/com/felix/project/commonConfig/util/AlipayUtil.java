package com.felix.project.commonConfig.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.felix.project.user.util.GetUUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.alipay.api.AlipayConstants.CHARSET_UTF8;

/**
 * @ClassName alipayUtil
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/25 13:29
 **/
public class AlipayUtil {

    public static void aliPay(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String property)throws AlipayApiException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016092400584934",
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCLCEdoUJis2BmuZlbmRaeebtP2e2hcPh4S/CKxsiJFCGueiJDRvB4xalKYyHp7rwzaxJ3TLBYQ1jg+muPY/1W0ldx3Le/cqCXVlEjpdhA8ZT1ug2yxN8WMKpWw9hilN2vqZbWs8XHGyVPqFN9TxCXFh9yXeDes3rYCV+qd5DmyYYrainSViVa5BYVV7+H3eIeb7Yn5ccqWeXQFQ+wSTztzSYwoHB4x7C+3kp95ZetBKmlLtydt3I4pXyDszwisLgnDWJZu6q/A6GMU0korJ3HIvWysJXVg/fhMZyNmMEartWrU+nVBpUV64oWdghWah1fuWaorMN2F3J0R80npwDntAgMBAAECggEASL+IPbkOakKZDXxXNB52Wpw7JNnl/X4zVlPAA6Ll86E4Tre+rz/zvH+ZwIAGNvmdjEsdGgYTdbPhskBxwHW6bseLmIDc5WTGhz8T97TjQfbnchAqq4A3SeRudy4OL0UUno2hxmB/3Dg0RGOvh7E24mj4r/kjiLzwF8HQ7dUhq4hAPm1nywiDF1wQ7RMizl/rsHvfd95rltHaXs/1apIffRJ4TU/EpyHM1aqURJnAhYrNiggu7rbnqOPFCOx5KRYRDqJqqAl10sU3xgIdzVTIYBvVY2A4cLjaa69I1O4GbqxOAof/om12Ws6n2m00YOj2fNCb3zja66U8gBR5xbo4AQKBgQDPi+HrygdefsXK0GR7rasr0JyruEIJws8rU1Vu3bwnp01l0JdZCr6epdK7N76T0vtyu5WPaoyb4SwRe/R05Uz3RQALjlsjvgBiHM6XFNU2AKvNn5pnixC3Vj9fmDNt9IDA//9XrnfjLgarZlTihUJQxiqOzqci62n8XLsOL3ZPbQKBgQCrfZ4gRwqJ83v03KYvmeY/VV4RMyM+1PA9Zw1dJTqz44A+1GXKFp3YUnaRZdSSpoG3IbER0zEzlwmJ9MTxM24uI8finvRvGp+KonpW6Om05FDiz1h4doK3JeumgluZ0qCyM6fgT74V4ig/UURwwrGEpnlG5n3EuPb4svFjhCaEgQKBgBE33KVQHG5lfAlIYp20GbhH1UeVwLzIkaFZh4PsF1j+0zf4JyY28vt8bFAl3sSqwzuQfmbogwPHmX1V2ED+aPU2nztWMw9zCtsY4ra7/rg9NAOaExg1/EwJdrZ6jQG5QBlC468lhUKupdUAZ1hnj8ndttgkIs+N0jwigU4698i9AoGAPHLKEzyXZWsTLYu9dJf+BiMURmHNMa+jgzFZD5U47QHrSbpaqvJv7zmaG8RWOt9AaAoTPWJBPJsnvJ4v8Ymcc90e7y3yoVs0E40ULKBrCN1V+az21XeIugA+4XGf0kfhZw84RKQ3L8WJRFBkHI80Pbrkku5aNMayH5YL6nVBkoECgYAYYA3LUGbDyf8ZF4BFolX9ZtlC15snBOo9A1M6Qbrez0HoBoRvxqiBblOxO6FP1AUWPudifBZx4MBKGiGpTMMQGFWJB9k8HOYxv+7SZO79QrSdB/jZyO6eDqVUqHKnREjx1wQrnL+AwwfkKq7TZCqxyxO9JayVtBZWQoUSuLPHEA==",
                "json",
                CHARSET_UTF8,
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiwhHaFCYrNgZrmZW5kWnnm7T9ntoXD4eEvwisbIiRQhrnoiQ0bweMWpSmMh6e68M2sSd0ywWENY4Pprj2P9VtJXcdy3v3Kgl1ZRI6XYQPGU9boNssTfFjCqVsPYYpTdr6mW1rPFxxslT6hTfU8QlxYfcl3g3rN62AlfqneQ5smGK2op0lYlWuQWFVe/h93iHm+2J+XHKlnl0BUPsEk87c0mMKBweMewvt5KfeWXrQSppS7cnbdyOKV8g7M8IrC4Jw1iWbuqvwOhjFNJKKydxyL1srCV1YP34TGcjZjBGq7Vq1Pp1QaVFeuKFnYIVmodX7lmqKzDdhdydEfNJ6cA57QIDAQAB",
                "RSA2");
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:4200/payReturn");
        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":"+GetUUID.getOrderIdByTime()+"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+property+"," +
                "    \"subject\":"+GetUUID.getOrderIdByTime()+"," +
                "    \"body\":"+GetUUID.getOrderIdByTime()+"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET_UTF8);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
}
