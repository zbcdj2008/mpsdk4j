package io.github.elkan1788.mpsdk4j.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

/**
 * 采用驱动式处理微信消息
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class MessageHandler extends DefaultHandler2 {

    private static final Log log = Logs.get();

    // 节点属性值
    private String attrVal;

    static Map<String, String> _vals = new ConcurrentHashMap<String, String>();

    public Map<String, String> getValues() {
        return _vals;
    }

    @Override
    public void startDocument() throws SAXException {
        _vals.clear();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if ("PicList".equals(qName)) {
            return;
        }

        if ("item".equals(qName)) {
            return;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (log.isInfoEnabled()) {
            if (!Strings.equals("xml", qName)) {
                log.infof("Current node vaule: [%s-%s]", qName, attrVal);
            }
        }

        // 暂存为map集合
        if ("ToUserName".equals(qName)) {
            _vals.put("toUserName", attrVal);
            return;
        }
        if ("FromUserName".equals(qName)) {
            _vals.put("fromUserName", attrVal);
            return;
        }
        if ("CreateTime".equals(qName)) {
            _vals.put("createTime", attrVal);
            return;
        }
        if ("MsgType".equals(qName)) {
            _vals.put("msgType", attrVal);
            return;
        }
        if ("Content".equals(qName)) {
            _vals.put("content", attrVal);
            return;
        }
        if ("PicUrl".equals(qName)) {
            _vals.put("picUrl", attrVal);
            return;
        }
        if ("MediaId".equals(qName)) {
            _vals.put("mediaId", attrVal);
            return;
        }
        if ("MsgId".equals(qName)) {
            _vals.put("msgId", attrVal);
            return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        this.attrVal = new String(ch, start, length);
    }

}