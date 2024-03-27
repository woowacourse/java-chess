package chess.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class JdbcConnectionPool implements ConnectionPool {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final int INITIAL_POOL_SIZE = 2;
    private static final String FAILED_INITIALIZE = "커넥션 풀 초기화에 실패했습니다.";
    private static final String FAILED_TO_GET_CONNECTION = "커넥션 획득에 실패했습니다.";
    private static final String FAILED_TO_TERMINATE = "종료에 실패했습니다.";
    private static final String FAILED_RELEASE = "커넥션 해제에 실패했습니다.";

    private BlockingQueue<Connection> pool;

    private static final ConnectionPool INSTANCE = new JdbcConnectionPool();

    private JdbcConnectionPool() {
        initializeConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    private void initializeConnectionPool() {
        pool = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
        try {
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                pool.offer(createNewConnection());
            }
        } catch (SQLException e) {
            throw new RuntimeException(FAILED_INITIALIZE);
        }
    }

    private Connection createNewConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
    }

    @Override
    public Connection getConnection() {
        try {
            if (pool.isEmpty()) {
                return createNewConnection();
            } else {
                return pool.take();
            }
        } catch (InterruptedException | SQLException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(FAILED_TO_GET_CONNECTION);
        }
    }

    @Override
    public void releaseConnection(Connection connection) {
        try {
            pool.put(connection);
            System.out.println("Pool Size = " + pool.size());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(FAILED_RELEASE);
        }
    }

    @Override
    public void shutdown() {
        try {
            for (Connection connection : pool) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(FAILED_TO_TERMINATE);
        }
        pool.clear();
    }
}
