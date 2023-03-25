package chess.db;

import static java.util.stream.Collectors.toList;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class FixedConnectionPool implements ConnectionPool {

    private static final FixedConnectionPool INSTANCE = new FixedConnectionPool();
    private static final int MAX_CONNECTION_SIZE = 5;

    private final AtomicInteger index;
    private final List<Connection> connections;

    private FixedConnectionPool() {
        this(MAX_CONNECTION_SIZE);
    }

    private FixedConnectionPool(int connectionCount) {
        index = new AtomicInteger();
        connections = Stream.generate(ConnectionGenerator::getConnection)
                .limit(connectionCount)
                .collect(toList());
    }

    public static FixedConnectionPool getInstance() {
        return INSTANCE;
    }

    @Override
    public Connection getConnection() {
        int currentIndex = index.getAndIncrement();
        return connections.get(currentIndex % connections.size());
    }
}
