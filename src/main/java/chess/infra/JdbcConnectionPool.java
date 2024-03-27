package chess.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class JdbcConnectionPool {

    private static final String FAILED_INITIALIZE = "커넥션 풀 초기화에 실패했습니다.";
    private static final String FAILED_TO_GET_CONNECTION = "커넥션 획득에 실패했습니다.";
    private static final String FAILED_TO_TERMINATE = "종료에 실패했습니다.";
    private static final String FAILED_RELEASE = "커넥션 해제에 실패했습니다.";
    private static final int INITIAL_POOL_SIZE = 2;
    private static final JdbcConnectionPool INSTANCE = new JdbcConnectionPool();
    private final DatabaseConfiguration configuration;
    private BlockingQueue<Connection> pool;

    private JdbcConnectionPool() {
        configuration = DatabaseConfiguration.getInstance();
        initializeConnectionPool();
    }

    public static JdbcConnectionPool getInstance() {
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
        return DriverManager.getConnection(
                configuration.getUrl(),
                configuration.getUsername(),
                configuration.getPassword()
        );
    }

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

    public void releaseConnection(final Connection connection) {
        try {
            pool.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(FAILED_RELEASE);
        }
    }

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
