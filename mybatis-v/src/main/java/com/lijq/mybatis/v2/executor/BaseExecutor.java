package com.lijq.mybatis.v2.executor;

import com.lijq.mybatis.v2.binding.MapperRegistry;
import com.lijq.mybatis.v2.jdbc.DBUtils;
import com.lijq.mybatis.v2.session.Configuration;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Lijq
 * @date 2018/4/8 15:02
 * @description
 */
public class BaseExecutor implements Executor {

    @Getter
    private Configuration configuration;

    public BaseExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T selectOne(MapperRegistry.MapperData mapperData, Object parameter) {

        Connection connection = DBUtils.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            // 简化处理
            pstm = connection.prepareStatement(mapperData.getSql());

            int index = 1;
            if (parameter instanceof Object[]) {
                Object[] objs = (Object[]) parameter;
                for (Object obj : objs) {
                    pstm.setObject(index++, obj);
                }
            } else {
                pstm.setObject(1, parameter);
            }

            pstm.execute();


            rs = pstm.getResultSet();

            return (T) new ResultHandler().handler(rs, mapperData.getType());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, pstm, connection);
        }

        return null;
    }
}
