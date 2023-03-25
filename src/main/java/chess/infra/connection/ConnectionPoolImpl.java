package chess.infra.connection;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConnectionPoolImpl implements ConnectionPool {

    private static final int DEFAULT_CONNECTION_COUNT = 5;
    private static final ConnectionPoolImpl instance = new ConnectionPoolImpl();
    private final AtomicInteger index;
    private final List<Connection> connections;

    private ConnectionPoolImpl() {
        this(DEFAULT_CONNECTION_COUNT);
    }

    private ConnectionPoolImpl(int connectionCount) {
        index = new AtomicInteger(0);
        connections = Stream.generate(ConnectionGenerator::getConnection)
                .limit(connectionCount)
                .collect(Collectors.toList());
    }

    public static ConnectionPoolImpl getInstance() {
        return instance;
    }

    @Override
    public Connection getConnection() {
        int currentIndex = index.getAndIncrement();
        return connections.get(currentIndex % connections.size());
    }
}
