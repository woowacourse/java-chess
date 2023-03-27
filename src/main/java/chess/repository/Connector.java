package chess.repository;

import java.sql.Connection;

public interface Connector {

    Connection getConnection();
}
