package chess.dao;

import java.sql.Connection;

public interface ConnectionStrategy {

    Connection findConnection();
}
