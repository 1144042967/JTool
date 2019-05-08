package cn.sd.jrz.jtool.http;

/**
 * @author 江荣展
 * Date: 2019-04-13
 * Time: 17:10
 */
public class JHttp {

    public static JGet get(String url) {
        return new JGet(url);
    }
}
