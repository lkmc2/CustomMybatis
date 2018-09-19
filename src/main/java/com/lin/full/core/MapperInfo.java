package com.lin.full.core;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description Mapper信息
 */
public class MapperInfo {

    /*** 查询类型 ***/
    private QueryType queryType;

    /*** 接口名 ***/
    private String interfaceName;

    /*** 方法名 ***/
    private String methodName;

    /*** 查询语句 ***/
    private String sql;

    /*** 返回值类型 ***/
    private String resultType;

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    @Override
    public String toString() {
        return "MapperInfo{" +
                "queryType=" + queryType +
                ", interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", sql='" + sql + '\'' +
                ", resultType='" + resultType + '\'' +
                '}';
    }

}
