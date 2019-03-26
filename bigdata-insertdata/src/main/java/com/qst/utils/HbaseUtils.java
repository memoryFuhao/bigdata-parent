package com.qst.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * created by hkl
 * HBase Utils
 */

public class HbaseUtils {

    private static Logger logger = LoggerFactory.getLogger(HbaseUtils.class);

    static Configuration conf = hbaseConfiguration();
    static Connection conn = null;

    static {
        try {
            conn = ConnectionFactory.createConnection(conf);
        } catch (IOException e1) {
            logger.error("连接创建失败!");
        }
    }

    public static Configuration hbaseConfiguration() {
        Properties hdfsConfiguration = new Properties();
        Configuration conf = null;
        try {
            hdfsConfiguration = HdfsPropertiesUtils.load("/user/conf/path.properties");
            String masterString = hdfsConfiguration.getProperty("hbase.master");
            String clientString = hdfsConfiguration.getProperty("hbase.zookeeper.property.clientPort");
            String quorumString = hdfsConfiguration.getProperty("hbase.zookeeper.quorum");
            conf = HBaseConfiguration.create();
            conf.set("hbase.master", masterString);
            conf.set("hbase.zookeeper.property.clientPort", clientString);
            conf.set("hbase.zookeeper.quorum", quorumString);
        } catch (Exception e) {
            logger.error("HBase conf is incorrect!");
        }
        return conf;
    }

    /**
     * 创建表
     *
     * @param tableName 表名
     * @param familys   列族s
     */
    public static void createTable(String tableName, String[] familys) {
        TableName tName = TableName.valueOf(tableName);
        Admin admin = null;
        try {
            admin = conn.getAdmin();
            if (admin.tableExists(tName)) {
                logger.info(new StringBuilder("table ").append(tableName).append("already exists").toString());
            } else {
                HTableDescriptor hTableDescriptor = new HTableDescriptor(tName);
                for (String family : familys) {
                    hTableDescriptor.addFamily(new HColumnDescriptor(family));
                }
                admin.createTable(hTableDescriptor);
                logger.info(new StringBuilder("create table ").append(tableName).append(" success").toString());
            }
        } catch (IOException e) {
            logger.error("create table failed!", e);
        } finally {
            try {
                if (admin != null) {
                    admin.close();
                }
            } catch (IOException e) {
                logger.error("close admin failed!", e);
            }
        }
    }

    /**
     * 创建单个列族的表
     *
     * @param tableName 表名
     * @param family    列族
     */
    public static void createTable(String tableName, String family) {
        TableName tName = TableName.valueOf(tableName);
        Admin admin = null;
        try {
            admin = conn.getAdmin();
            if (admin.tableExists(tName)) {
                logger.info(new StringBuilder("table ").append(tableName).append("already exists").toString());
            } else {
                HTableDescriptor hTableDescriptor = new HTableDescriptor(tName);
                hTableDescriptor.addFamily(new HColumnDescriptor(family));
                admin.createTable(hTableDescriptor);
                logger.info(new StringBuilder("create table ").append(tableName).append(" success").toString());
            }
        } catch (IOException e) {
            logger.error("create table failed!", e);
        } finally {
            try {
                if (admin != null) {
                    admin.close();
                }
            } catch (IOException e) {
                logger.error("close admin failed!", e);
            }
        }
    }

    /**
     * 插入单条数据
     *
     * @param tablename
     * @param put
     */
    public static void put(String tablename, Put put) {
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            table.put(put);
            logger.info("insert data success");
        } catch (IOException e) {
            logger.error("insert data failed!", e);
        }
    }

    /**
     * 批量插入数据
     *
     * @param tablename
     * @param putList
     */
    public static void put(String tablename, List<Put> putList) {
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            table.put(putList);
            logger.info("insert datas success");
        } catch (IOException e) {
            logger.error("insert datas failed!", e);
        }
    }

    /**
     * 插入单列数据
     *
     * @param tablename 表名
     * @param rowkey    行键
     * @param family    列族
     * @param column    列
     * @param data      列的值
     */
    public static void put(String tablename, String rowkey, String family, String column, String data) {
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            Put put = new Put(Bytes.toBytes(rowkey));
            put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(data));
            table.put(put);
            logger.info("insert data success");
        } catch (Exception e) {
            logger.error(new StringBuilder("insert unsuccessfully,tableName:").append(tablename).append(",Rowkey:")
                    .append(rowkey).append(",FamilyName:").append(family).append(",the reason:").append(e.getMessage()).toString(), e);
        }
    }

    /**
     * 插入单个列族数据
     *
     * @param tablename 表名
     * @param rowkey    行键
     * @param family    列族
     * @param column    一整列
     * @param value     一整列的数据值，类型为String
     */
    public static void put(String tablename, String rowkey, String family, List<String> column, List<String> value) {
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            Put put = new Put(Bytes.toBytes(rowkey));
            for (int i = 0; i < column.size(); i++) {
                put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column.get(i)),
                        Bytes.toBytes(value.get(i)));
            }
            table.put(put);
            logger.info("insert data success");
        } catch (Exception e) {
            logger.error(new StringBuilder("insert unsuccessfully,tableName:").append(tablename).append(",Rowkey:")
                    .append(rowkey).append(",FamilyName:").append(family).append(",the reason:").append(e.getMessage()).toString(), e);
        }
    }

    /**
     * 插入单个列族数据
     *
     * @param tablename 表名
     * @param rowkey    行键
     * @param family    列族
     * @param columns   一整列
     * @param values    一整列的数据值，类型为String
     */
    public static void put(String tablename, String rowkey, String family, String[] columns, String[] values) {
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            Put put = new Put(Bytes.toBytes(rowkey));
            for (int i = 0; i < columns.length; i++) {
                put.addColumn(Bytes.toBytes(family), Bytes.toBytes(columns[i]),
                        Bytes.toBytes(values[i]));
            }
            table.put(put);
            logger.info("insert data success");
        } catch (Exception e) {
            logger.error(new StringBuilder("insert unsuccessfully,tableName:").append(tablename).append(",Rowkey:")
                    .append(rowkey).append(",FamilyName:").append(family).append(",the reason:").append(e.getMessage()).toString(), e);
        }
    }

    /**
     * 通过行键获取数据
     *
     * @param tablename 表名
     * @param rowkey    行键
     * @return result  查询结果
     */
    public static Result get(String tablename, String rowkey) {
        Result result = null;
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            Get get = new Get(Bytes.toBytes(rowkey));
            result = table.get(get);
            logger.info(new StringBuilder("rowkey: ").append(new String(result.getRow())).toString());
            List<Cell> cellList = result.listCells();
            for (Cell cell : cellList) {
                logger.info(new StringBuilder(new String(CellUtil.cloneFamily(cell))).append(":")
                        .append(new String(CellUtil.cloneQualifier(cell))).append(" ")
                        .append(new String(CellUtil.cloneValue(cell))).toString());
            }
            logger.info("get data success!");
        } catch (IOException e) {
            logger.error(new StringBuilder("scan unsuccessfully,tableName:").append(tablename)
                    .append(",the reason:").append(e.getMessage()).toString(), e);
        }
        return result;
    }

    /**
     * 通过多个rowkey查询多行数据
     *
     * @param tablename  表名
     * @param rowkeyList rowkey的集合
     * @return 查询结果
     */
    public static Result[] get(String tablename, List<String> rowkeyList) {
        Result results[] = null;
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            List<Get> list = new ArrayList<>();
            for (String rowkey : rowkeyList) {
                Get get = new Get(Bytes.toBytes(rowkey));
                list.add(get);
            }
            results = table.get(list);
            logger.info(new StringBuilder("tablename:").append(tablename).toString());
            for (Result result : results) {
                logger.info(new StringBuilder("rowkey: ").append(new String(result.getRow())).toString());
                List<Cell> cellList = result.listCells();
                for (Cell cell : cellList) {
                    logger.info(new StringBuilder(new String(CellUtil.cloneFamily(cell)))
                            .append(":").append(new String(CellUtil.cloneQualifier(cell)))
                            .append(" ").append(new String(CellUtil.cloneValue(cell))).toString());
                }
            }
            logger.info("get data success!");
        } catch (IOException e) {
            logger.error(new StringBuilder("scan unsuccessfully,tableName:").append(tablename)
                    .append(",the reason:").append(e.getMessage()).toString(), e);
        }
        return results;
    }

    /**
     * 通过多个rowkey查询多行数据
     *
     * @param tablename  表名
     * @param rowkeyList rowkey的集合
     * @return 查询结果
     */
    public static String getJsonResults(String tablename, List<String> rowkeyList) {
        return convertToJsonStr(tablename, rowkeyList);
    }

    /**
     * 通过多个Get对象查询多行数据
     *
     * @param tablename
     * @param list
     * @return 查询结果
     */
    public static Result[] getResults(String tablename, List<Get> list) {
        Result results[] = null;
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            results = table.get(list);
            logger.info(new StringBuilder("tablename:").append(tablename).toString());
            for (Result result : results) {
                logger.info(new StringBuilder("rowkey: ").append(new String(result.getRow())).toString());
                List<Cell> cellList = result.listCells();
                for (Cell cell : cellList) {
                    logger.info(new StringBuilder(new String(CellUtil.cloneQualifier(cell))).append(":").append(new String(cell.getQualifierArray()))
                            .append(" ").append(new String(CellUtil.cloneValue(cell))).toString());
                }
            }
            logger.info("get data success!");
        } catch (IOException e) {
            logger.error(new StringBuilder("scan unsuccessfully,tableName:").append(tablename)
                    .append(",the reason:").append(e.getMessage()).toString(), e);
        }
        return results;


    }

    /**
     * 通过行键&列族&列获取指定列值
     *
     * @param tablename
     * @param rowkey
     * @param family
     * @param column
     * @return result 查询结果
     */
    public static Result get(String tablename, String rowkey, String family, String column) {
        Result result = null;
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            Get get = new Get(Bytes.toBytes(rowkey));
            get.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
            result = table.get(get);
            byte[] val = result.getValue(Bytes.toBytes(family), Bytes.toBytes(column));
            logger.info(new StringBuilder(family).append(":").append(column)
                    .append(" ").append(new String(val)).toString());
        } catch (Exception e) {
            logger.error(new StringBuilder("insert unsuccessfully,tableName:").append(tablename)
                    .append(",FamilyName:").append(family)
                    .append(",the reason:").append(e.getMessage()).toString(), e);
        }
        return result;
    }

    /**
     * 查询全表数据
     *
     * @param tablename 表名
     */
    public static ResultScanner scan(String tablename) {
        ResultScanner res = null;
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            Scan scan = new Scan();
            res = table.getScanner(scan);
            for (Result result : res) {
                logger.info(new StringBuilder("rowkey: ").append(new String(result.getRow())).toString());
                List<Cell> cellList = result.listCells();
                for (Cell cell : cellList) {
                    logger.info(new StringBuilder(new String(CellUtil.cloneFamily(cell))).append(":").append(new String(CellUtil.cloneQualifier(cell)))
                            .append(" ").append(new String(CellUtil.cloneValue(cell))).toString());
                }
            }
            logger.info("scan data success!");
        } catch (Exception e) {
            logger.error(new StringBuilder("scan unsuccessfully,tableName:").append(tablename)
                    .append(",the reason:").append(e.getMessage()).toString(), e);
        }
        return res;
    }

    /**
     * 查询指定行键范围的数据
     *
     * @param tablename   表名
     * @param startrowkey 开始行键值
     * @param endrowkey   结束行键值
     * @return 查询结果
     */
    public static ResultScanner timeScan(String tablename, String startrowkey, String endrowkey) {
        ResultScanner res = null;
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            byte[] begin = Bytes.toBytes(startrowkey);
            byte[] end = Bytes.toBytes(endrowkey);
            Scan scan = new Scan();
            scan.setStartRow(begin);
            scan.setStopRow(end);
            res = table.getScanner(scan);
            for (Result result : res) {
                logger.info(new StringBuilder("rowkey: ").append(new String(result.getRow())).toString());
                List<Cell> cellList = result.listCells();
                for (Cell cell : cellList) {
                    logger.info(new StringBuilder(new String(CellUtil.cloneFamily(cell))).append(":").append(new String(CellUtil.cloneQualifier(cell)))
                            .append(" ").append(new String(CellUtil.cloneValue(cell))).toString());
                }
            }
            logger.info("scan data success!");
        } catch (Exception e) {
            logger.error(new StringBuilder("insert unsuccessfully,tableName:").append(tablename).
                    append(",startrowkey:").append(startrowkey)
                    .append("endrowkey").append(endrowkey)
                    .append(",the reason:").append(e.getMessage()).toString());
        }
        return res;
    }

    /**
     * 浏览指定列族指定条件的数据
     *
     * @param tablename         表名
     * @param familyName        列族名
     * @param jsonSearchCommand joson格式的数据
     * @return 查询结果
     */
    public static ResultScanner jsonScan(String tablename, String familyName, String jsonSearchCommand) {
        ResultScanner res = null;
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            Map<String, String> searchCommandMap = new HashMap<>();
            try {
                JSONObject jsonObject = JSONObject.parseObject(jsonSearchCommand);
                Iterator iterator = jsonObject.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next().toString();
                    searchCommandMap.put(key, jsonObject.getString(key));
                }
            } catch (Exception e) {
                logger.error("query exception", e);
            }
            if (searchCommandMap.isEmpty()) {
                logger.error("query condition is null");
            }
            FilterList filterList = new FilterList();
            for (String key : searchCommandMap.keySet()) {
                SingleColumnValueFilter filter = new SingleColumnValueFilter(
                        Bytes.toBytes(familyName),
                        Bytes.toBytes(key),
                        CompareFilter.CompareOp.EQUAL,
                        Bytes.toBytes(searchCommandMap.get(key)));
                filter.setFilterIfMissing(true);
                filterList.addFilter(filter);
            }
            Scan scan = new Scan();
            scan.setFilter(filterList);
            res = table.getScanner(scan);
        } catch (Exception e) {
            logger.error(new StringBuilder("Read hbase table failed:").append(tablename).append(",query :").append(jsonSearchCommand)
                    .append(e.getMessage()).toString());
            res = null;
        }
        return res;
    }

    /**
     * 删除表
     *
     * @param tablename 表名
     * @return true或者false，是否删除表成功
     */
    public static boolean deleteTable(String tablename) {
        boolean result = false;
        Admin admin = null;
        try {
            admin = conn.getAdmin();
            if (admin.tableExists(TableName.valueOf(tablename))) {
                admin.disableTable(TableName.valueOf(tablename));
                admin.deleteTable(TableName.valueOf(tablename));
                result = true;
                logger.info(new StringBuilder("HBase table is deleted successfully:").append(tablename).toString());
            } else {
                ;
                logger.info(new StringBuilder("table").append(tablename).append("is not exists").toString());
            }
        } catch (Exception e) {
            logger.error(new StringBuilder("HBase table is deleted unsuccessfully,tableName:").append(tablename)
                    .append(",the reason:").append(e.getMessage()).toString());
        } finally {
            try {
                if (admin != null) {
                    admin.close();
                }
            } catch (IOException e) {
                logger.error("close admin failed!", e);
            }
        }
        return result;
    }

    /**
     * 删除指定rowkey的数据；
     *
     * @param tablename
     * @param rowkey
     */
    public static void deleteByRowkey(String tablename, String rowkey) {
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            Delete delete = new Delete(Bytes.toBytes(rowkey));
            table.delete(delete);
            logger.info("delete data success!");
        } catch (Exception e) {
            logger.error("delete data failed!", e);
        }
    }

    /**
     * 删除多个rowkey的数据；
     *
     * @param tablename
     * @param rowkeyList
     */
    public static void deleteByRowkey(String tablename, List<String> rowkeyList) {
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            List<Delete> deleteList = new ArrayList<>();
            for (String rowkey : rowkeyList) {
                Delete delete = new Delete(Bytes.toBytes(rowkey));
                deleteList.add(delete);
            }
            table.delete(deleteList);
            logger.info("delete datas success!");
        } catch (Exception e) {
            logger.error("delete datas failed!", e);
        }
    }

    /**
     * 清空表数据
     *
     * @param tablename
     * @return
     */
    public static boolean truncateTable(String tablename) {
        boolean result = false;
        Admin admin = null;
        try {
            admin = conn.getAdmin();
            if (admin.tableExists(TableName.valueOf(tablename))) {
                admin.disableTable(TableName.valueOf(tablename));
                admin.truncateTable(TableName.valueOf(tablename), true);
                result = true;
                logger.info(new StringBuilder("HBase table is truncated successfully:").append(tablename).toString());
            } else {
                ;
                logger.info(new StringBuilder("table").append(tablename).append("is not exists").toString());
            }
        } catch (Exception e) {
            logger.error(new StringBuilder("HBase table is truncated unsuccessfully,tableName:").append(tablename)
                    .append(",the reason:").append(e.getMessage()).toString());
        } finally {
            try {
                if (admin != null) {
                    admin.close();
                }
            } catch (IOException e) {
                logger.error("close admin failed!", e);
            }
        }
        return result;
    }

    /**
     * convert hbase data  to jsonString
     *
     * @param tablename
     * @param rowkey
     * @return jsonstring
     */
    public static String convertToJsonStr(String tablename, String rowkey) {
        String s = "";
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            Get get = new Get(Bytes.toBytes(rowkey));
            Result result = table.get(get);
            JSONObject colValues = new JSONObject();
            colValues.put("uuid",Bytes.toString(result.getRow()));
            for (Cell cell : result.listCells()) {
                colValues.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
            }
            s = colValues.toString();
            logger.info("convert data to json string sucsess!");
        } catch (Exception e) {
            logger.error(new StringBuilder("convertToJsonStr unsuccessfully,tableName:").append(tablename)
                    .append(",the reason:").append(e.getMessage()).toString(), e);
        }
        return s;
    }

    /**
     * convert hbase datas  to jsonStrings
     *
     * @param tablename
     * @param rowkeylist
     * @return jsonstrings
     */
    public static String convertToJsonStr(String tablename, List<String> rowkeylist) {
        String s = "";
        try {
            Table table = conn.getTable(TableName.valueOf(tablename));
            List<Get> list = new ArrayList<>();
            for (String rowkey : rowkeylist) {
                Get get = new Get(Bytes.toBytes(rowkey));
                list.add(get);
            }
            Result[] results = table.get(list);
            JSONArray jsonArray = new JSONArray();
            for (Result result : results) {
                JSONObject colValues = new JSONObject();
                colValues.put("uuid", Bytes.toString(result.getRow()));
                for (Cell cell : result.listCells()) {
                    colValues.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
                }
                jsonArray.add(colValues);
            }
            s = jsonArray.toString();
            logger.info("convert datas to json string sucsess!");
        } catch (Exception e) {
            logger.error(new StringBuilder("convertToJsonStr unsuccessfully,tableName:").append(tablename)
                    .append(",the reason:").append(e.getMessage()).toString(), e);
        }
        return s;
    }


}