package Api.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 连接mysql工具类
 * Created by liuxiang on 2018/7/23.
 */
public class JdbcConnectUtils {
    private static volatile JdbcConnectUtils dbConnection;
    private ComboPooledDataSource cpds;

    /**
     * 在构造函数初始化的时候获取数据库连接
     */
    private JdbcConnectUtils() {
        try {
            Properties prop = PropertiesReaderUtils.getProperties("conf/path.properties");
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String username = prop.getProperty("userName");
            String password = prop.getProperty("password");
            /**数据库连接池对象**/
            cpds = new ComboPooledDataSource();
            /**设置数据库连接驱动**/
            cpds.setDriverClass(driver);
            /**设置数据库连接地址**/
            cpds.setJdbcUrl(url);
            /**设置数据库连接用户名**/
            cpds.setUser(username);
            /**设置数据库连接密码**/
            cpds.setPassword(password);
            /**获取连接失败将会引起所有等待连接池来获取连接的线程抛出异常**/
            cpds.setBreakAfterAcquireFailure(false);
            /**设置最大连接数**/
            cpds.setMaxPoolSize(100);
            cpds.setMaxStatements(0);
            /**最大空闲时间,1800秒内未使用则连接被丢弃。**/
            cpds.setMaxIdleTime(1800);
            /**当连接池用完时客户端调用getConnection()后等待获取新连接的时间**/
            cpds.setCheckoutTimeout(5000);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象，单例
     *
     * @return
     */
    public static JdbcConnectUtils getInstance() {
        if (dbConnection == null) {
            synchronized (JdbcConnectUtils.class) {
                if (dbConnection == null) {
                    dbConnection = new JdbcConnectUtils();
                }
            }
        }
        return dbConnection;
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public final synchronized Connection getConnection() throws SQLException {
        try {
            return cpds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * finalize()方法是在垃圾收集器删除对象之前对这个对象调用的。
     *
     * @throws Throwable
     */
    protected void finalize() throws Throwable {
        DataSources.destroy(cpds);
        super.finalize();
    }

    public static void free(Statement st, Connection conn) {
        try {
            if (st != null)
                st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }
}
