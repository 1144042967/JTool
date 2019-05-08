package cn.sd.jrz.jtool.http;

import cn.sd.jrz.jtool.function.JConsumer;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 江荣展
 * Date: 2019-04-13
 * Time: 17:32
 */
@SuppressWarnings("unchecked")
public abstract class AbstractRequestBase<T extends AbstractRequestBase> {
    String url;
    List<NameValuePair> query = new ArrayList<>();
    Map<String, Object> header = new HashMap<>();
    Map<String, Object> body = new HashMap<>();
    ContentType contentType = ContentType.APPLICATION_JSON;

    AbstractRequestBase(String url) {
        this.url = url;
    }

    public T query(String key, Object value) {
        if (key != null && value != null) {
            query.add(new BasicNameValuePair(key, value.toString()));
        }
        return (T) this;
    }

    public T header(String key, Object value) {
        if (key != null && value != null) {
            header.put(key, value);
        }
        return (T) this;
    }

    public T body(String key, Object value) {
        if (key != null && value != null) {
            body.put(key, value);
        }
        return (T) this;
    }

    public T contentType(ContentType contentType) {
        this.contentType = contentType;
        return (T) this;
    }

    public abstract void callback(JConsumer<CloseableHttpResponse> callback);
}
