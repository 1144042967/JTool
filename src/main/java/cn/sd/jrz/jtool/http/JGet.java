package cn.sd.jrz.jtool.http;

import cn.sd.jrz.jtool.function.JConsumer;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

/**
 * @author 江荣展
 * Date: 2019-04-13
 * Time: 17:11
 */
public class JGet extends AbstractRequestBase<JGet> {

    JGet(String url) {
        super(url);
    }

    @Override
    public void callback(JConsumer<CloseableHttpResponse> callback) {
        HttpGet get = new HttpGet(url);

    }
}
