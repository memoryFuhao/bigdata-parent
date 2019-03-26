package Api.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 操作mysql工具类
 * Created by liuxiang on 2018/7/23.
 */
public class JdbcOperateUtils {
    /**
     * 可以执行新增，修改，删除
     *
     * @param sql      sql语句
     * @param bindArgs 绑定参数
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public static int executeUpdate(String sql, Object[] bindArgs) throws SQLException {
        System.out.println(bindArgs[0]);
        /**影响的行数**/
        int affectRowCount = -1;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            /**从数据库连接池中获取数据库连接**/
            connection = JdbcConnectUtils.getInstance().getConnection();
            /**执行SQL预编译**/
            preparedStatement = connection.prepareStatement(sql.toString());
            /**设置不自动提交，以便于在出现异常的时候数据库回滚**/
            connection.setAutoCommit(false);
            if (bindArgs != null) {
                /**绑定参数设置sql占位符中的值**/
                for (int i = 0; i < bindArgs.length; i++) {
                    preparedStatement.setObject(i + 1, bindArgs[i]);
                }
            }
            /**执行sql**/
            affectRowCount = preparedStatement.executeUpdate();
            connection.commit();
            String operate;
            if (sql.toUpperCase().indexOf("DELETE FROM") != -1) {
                operate = "删除";
            } else if (sql.toUpperCase().indexOf("INSERT INTO") != -1) {
                operate = "新增";
            } else {
                operate = "修改";
            }
            System.out.println("成功" + operate + "了" + affectRowCount + "行");
            System.out.println();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return affectRowCount;
    }

    /**
     * 执行查询
     *
     * @param sql      要执行的sql语句
     * @param bindArgs 绑定的参数
     * @return List<String> 结果集对象
     * @throws SQLException SQL执行异常
     */
    public static List<String> singleQuery(String sql, Object[] bindArgs) throws SQLException {
        List<String> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            /**获取数据库连接池中的连接**/
            connection = JdbcConnectUtils.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (bindArgs != null) {
                /**设置sql占位符中的值**/
                for (int i = 0; i < bindArgs.length; i++) {
                    preparedStatement.setObject(i + 1, bindArgs[i]);
                }
            }
            /**执行sql语句，获取结果集**/
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String name = resultSet.getString(1);
                list.add(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    /**
     * 执行查询
     *
     * @param sql      要执行的sql语句
     * @param bindArgs 绑定的参数
     * @return List<Map<String, Object>>结果集对象
     * @throws SQLException SQL执行异常
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object[] bindArgs) throws SQLException {
        List<Map<String, Object>> datas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            /**获取数据库连接池中的连接**/
            connection = JdbcConnectUtils.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (bindArgs != null) {
                /**设置sql占位符中的值**/
                for (int i = 0; i < bindArgs.length; i++) {
                    preparedStatement.setObject(i + 1, bindArgs[i]);
                }
            }
            /**执行sql语句，获取结果集**/
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return datas;
    }

    /**
     * 执行数据库插入操作
     *
     * @param valueMap  插入数据表中key为列名和value为列对应的值的Map对象
     * @param tableName 要插入的数据库的表名
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public static int insert(String tableName, Map<String, Object> valueMap) throws SQLException {

        /**获取数据库插入的Map的键值对的值**/
        Set<String> keySet = valueMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        /**要插入的字段sql，其实就是用key拼起来的**/
        StringBuilder columnSql = new StringBuilder();
        /**要插入的字段值，其实就是？**/
        StringBuilder unknownMarkSql = new StringBuilder();
        Object[] bindArgs = new Object[valueMap.size()];
        int i = 0;
        while (iterator.hasNext()) {
            String key = iterator.next();
            columnSql.append(i == 0 ? "" : ",");
            columnSql.append(key);

            unknownMarkSql.append(i == 0 ? "" : ",");
            unknownMarkSql.append("?");
            bindArgs[i] = valueMap.get(key);
            i++;
        }
        /**开始拼插入的sql语句**/
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append(tableName);
        sql.append(" (");
        sql.append(columnSql);
        sql.append(" )  VALUES (");
        sql.append(unknownMarkSql);
        sql.append(" )");
        return executeUpdate(sql.toString(), bindArgs);
    }

    /**
     * 执行更新操作
     *
     * @param tableName 表名
     * @param valueMap  要更改的值
     * @param whereMap  条件
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public static int update(String tableName, Map<String, Object> valueMap, Map<String, Object> whereMap) throws SQLException {
        /**获取数据库插入的Map的键值对的值**/
        Set<String> keySet = valueMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        /**开始拼插入的sql语句**/
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ");
        sql.append(tableName);
        sql.append(" SET ");

        /**要更改的的字段sql，其实就是用key拼起来的**/
        StringBuilder columnSql = new StringBuilder();
        int i = 0;
        List<Object> objects = new ArrayList<>();
        while (iterator.hasNext()) {
            String key = iterator.next();
            columnSql.append(i == 0 ? "" : ",");
            columnSql.append(key + " = ? ");
            objects.add(valueMap.get(key));
            i++;
        }
        sql.append(columnSql);

        /**更新的条件:要更改的的字段sql，其实就是用key拼起来的**/
        StringBuilder whereSql = new StringBuilder();
        int j = 0;
        if (whereMap != null && whereMap.size() > 0) {
            whereSql.append(" WHERE ");
            iterator = whereMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                whereSql.append(j == 0 ? "" : " AND ");
                whereSql.append(key + " = ? ");
                objects.add(whereMap.get(key));
                j++;
            }
            sql.append(whereSql);
        }
        return executeUpdate(sql.toString(), objects.toArray());
    }

    /**
     * 执行删除操作
     *
     * @param tableName 要删除的表名
     * @param whereMap  删除的条件
     * @return 影响的行数
     * @throws SQLException SQL执行异常
     */
    public static int delete(String tableName, Map<String, Object> whereMap) throws SQLException {
        /**准备删除的sql语句**/
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ");
        sql.append(tableName);

        /**更新的条件:要更改的的字段sql，其实就是用key拼起来的**/
        StringBuilder whereSql = new StringBuilder();
        Object[] bindArgs = null;
        if (whereMap != null && whereMap.size() > 0) {
            bindArgs = new Object[whereMap.size()];
            whereSql.append(" WHERE ");
            /**获取数据库插入的Map的键值对的值**/
            Set<String> keySet = whereMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                String key = iterator.next();
                whereSql.append(i == 0 ? "" : " AND ");
                whereSql.append(key + " = ? ");
                bindArgs[i] = whereMap.get(key);
                i++;
            }
            sql.append(whereSql);
        }
        return executeUpdate(sql.toString(), bindArgs);
    }
}
