package chess.infra.connection;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConnectionPool {

    private static final int DEFAULT_CONNECTION_COUNT = 5;
    private static final ConnectionPool instance = new ConnectionPool();
    private final AtomicInteger index;
    private final List<Connection> connections;

    private ConnectionPool() {
        this(DEFAULT_CONNECTION_COUNT);
    }

    private ConnectionPool(int connectionCount) {
        index = new AtomicInteger(0);
        connections = Stream.generate(ConnectionGenerator::getConnection)
                .limit(connectionCount)
                .collect(Collectors.toList());
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() {
        int currentIndex = index.getAndIncrement();
        return connections.get(currentIndex % connections.size());
    }
}
