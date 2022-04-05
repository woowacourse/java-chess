package chess.dao;

import java.sql.Connection;

public interface ConnectionManager {

    <T> T run(ConnectionFunction<Connection, T> runnable);
}
