package com.yuncia.wechat.pojo.wechat.json.msg;

import com.yuncia.wechat.pojo.wechat.MessageType;

/**
 * @author MonKong
 * @Description 用一句话描述该类的作用
 * @date 2014年12月11日
 */
public class TextMsgReqJSON extends MsgReqJSON {
    private Text text;

    /**
     * 同时实例化Text并且设置其Content值
     */
    public TextMsgReqJSON(String textContent, String touser) {
        super(MessageType.text, touser);
        this.text = new Text();
        this.text.setContent(textContent);
    }

    public class Text {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Text [content=" + content + "]";
        }
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TextMsgReqJSON [text=" + text + "]" + super.toString();
    }
}
