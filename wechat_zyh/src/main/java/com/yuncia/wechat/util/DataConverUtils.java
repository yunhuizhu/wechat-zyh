/**
 *
 */
package com.yuncia.wechat.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.binary.BinaryStreamDriver;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author MonKong
 * @Description 主要用于Object/Json/XML/Map之间的简单数据转换，如需特殊配置，调用getObjectWriter()
 * @date 2014年12月8日
 */
@SuppressWarnings("unchecked")
public class DataConverUtils {
    private static final Logger logger = LogManager
            .getLogger(DataConverUtils.class);
    private static ObjectMapper objectMapper;
    private static XmlMapper xmlMapper;

    static {// 初始化mapper
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 不序列化null|但貌似对Json-->Map/List不起作用|对conver有效
        // objectMapper.setSerializationInclusion(Include.NON_NULL);
        // objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        xmlMapper = new XmlMapper();
        // 把日期写作时间毫秒
        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                true);
        // 枚举使用toString方法(需要重写toString-小写)
        // xmlMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING,true);
        // 不序列化null值(注意bean属性不能用原始类型)
        // xmlMapper.setSerializationInclusion(Include.NON_NULL);
        // 命名规则
        // xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
    }

    public static <T> T convertValue(Object obj, Class<T> clazz) {
        return objectMapper.convertValue(obj, clazz);
    }

    public static <T> T XMLToObject(String xml, Class<T> clazz)
            throws Exception {
        return xmlMapper.readValue(xml, clazz);
    }

    public static String ObjectToXML(Object obj) throws Exception {
        return xmlMapper.writeValueAsString(obj);
    }


    public static Map<String, Object> ObjectToMap(Object obj) {
        return objectMapper.convertValue(obj, Map.class);
    }

    public static String ObjectToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     * 主要用于Json-->Object/Map/List 用一句话描述该方法的作用
     *
     * @param json
     * @param clazz
     * @return
     * @date 2014年12月9日
     */
    public static <T> T JsonToObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json.getBytes(), clazz);
        } catch (IOException e) {
            logger.error("", e);
        }
        return null;
    }

    public static ObjectWriter getObjectWriter() {
        return objectMapper.writer();
    }

    public static ObjectWriter getXMLWriter() {
        return xmlMapper.writer();
    }

    public static ObjectReader getXMLReader() {
        return xmlMapper.reader();
    }
    public static String xstremObjectToXML(Object obj,Object subClass,String subAlias){
        XStream xstream = new XStream(new BinaryStreamDriver());
        xstream.alias("xml", obj.getClass());
        if(subClass!=null){
            xstream.alias(subAlias, subClass.getClass());
        }
        xstream.processAnnotations(obj.getClass());
        StringWriter sw = new StringWriter();
        xstream.marshal(obj, new CompactWriter(sw));
//        String xml = xstream.toXML(obj);
        String xml= sw.toString();
        //去掉换行
        return xml;
    }
}
