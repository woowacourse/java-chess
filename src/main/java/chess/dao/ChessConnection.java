package chess.dao;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class ChessConnection {

    protected final static org.slf4j.Logger logger = LoggerFactory.getLogger(ChessConnection.class);

    private static ChessConnection instance;

    private ConnectionProperty connectionProperty = new ConnectionProperty();

    synchronized public static ChessConnection getInstance() {
        try {
            if (instance == null) {
                instance = new ChessConnection();
                logger.info("DBManager initialize: {}", instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    private ChessConnection() {
        initFirstConnection();
    }

    private void initFirstConnection(){
        try {
            setupFirstDriver();
        } catch (Exception ex) {
            logger.error("Exception : {}", ex.getMessage());
        }
        logger.info("FirstConnection Created");
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:apache:commons:dbcp:first_connection");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return con;
    }

    public void setupFirstDriver() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        GenericObjectPool connectionPool = new GenericObjectPool(null);
        connectionPool.setMaxActive(45);
        connectionPool.setMinIdle(4);
        connectionPool.setMaxWait(15000);
        connectionPool.setTimeBetweenEvictionRunsMillis(3600000);
        connectionPool.setMinEvictableIdleTimeMillis(1800000);
        connectionPool.setMaxIdle(45);
        connectionPool.setTestOnBorrow(true);

        // 실제 DB와의 커넥션을 연결해주는 팩토리 생성
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                "jdbc:mysql://" + connectionProperty.getServer() + "/" + connectionProperty.getDatabase() + connectionProperty.getOption(), // JDBC URL
                connectionProperty.getUserName(), connectionProperty.getPassword());

        // Connection Pool이 PoolableConnection 객체를 생성할 때 사용할
        // PoolableConnectionFactory 생성
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
                connectionFactory,
                connectionPool,
                null, // statement pool
                "SELECT 1", // 커넥션 테스트 쿼리: 커넥션이 유효한지 테스트할 때 사용되는 쿼리.
                false, // read only 여부
                false); // auto commit 여부

        // Pooling을 위한 JDBC 드라이버 생성 및 등록
        PoolingDriver driver = new PoolingDriver();

        // JDBC 드라이버에 커넥션 풀 등록
        driver.registerPool("first_connection", connectionPool);
    }

    public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            freeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            freeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection con, PreparedStatement pstmt) {
        try {
            if (pstmt != null) pstmt.close();
            freeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection con, Statement stmt) {
        try {
            if (stmt != null) stmt.close();
            freeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Statement stmt) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(PreparedStatement pstmt) {
        try {
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


