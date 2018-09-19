package com.lin.full.core;

import com.lin.full.core.config.Config;
import com.lin.full.core.enumtype.QueryType;
import com.lin.full.core.model.MapperInfo;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description SQL Mapper处理类（单例）
 */
public enum SqlMappersHolder {
    INSTANCE;

    // 存储Mapper信息的Map
    private Map<String, MapperInfo> mapperInfoMap;

    SqlMappersHolder() {
        mapperInfoMap = new HashMap<>();

        // 获取Mapper xml文件所在目录
        File dir = null;
        try {
            dir = new File(SqlMappersHolder.class
                                                .getClassLoader()
                                                .getResource(Config.DEFAULT.getMapperPath())
                                                .toURI()
                                                .getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        //用dom4j解析UserMapper.xml文件
        SAXReader reader = new SAXReader();
        try {
            for (String fileName : dir.list()) {
                Document doc = reader.read(new File(dir, fileName));
                Element root = doc.getRootElement();

                // 获取Mapper接口的类名
                String className = root.attributeValue("namespace");

                // 遍历所有增删改查节点
                for (Object obj : root.elements()) {
                    Element element = (Element) obj;

                    // 创建Mapper信息
                    MapperInfo info = new MapperInfo();

                    // 设置查询类型
                    info.setQueryType(QueryType.value(element.getName()));

                    // 设置接口名
                    info.setInterfaceName(className);

                    // 设置查询方法名
                    info.setMethodName(element.attributeValue("id"));

                    // 设置返回值类型
                    info.setResultType(element.attributeValue("resultType"));

                    // 设置查询语句
                    info.setSql(element.getText());

                    // 将Mapper与对应的信息放入MAP中
                    mapperInfoMap.put(idOf(className, element.attributeValue("id")), info);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据接口名和方法名获取对应的Mapper信息
     * @param className 接口名
     * @param methodName 方法名
     * @return 对应的接口信息
     */
    public MapperInfo getMapperInfo(String className, String methodName) {
        return mapperInfoMap.get(idOf(className, methodName));
    }

    /**
     * 使用接口名和方法名生成唯一id
     * @param className 接口名
     * @param methodName 方法名
     * @return 唯一id
     */
    private String idOf(String className, String methodName) {
        return className + "." + methodName;
    }

}
