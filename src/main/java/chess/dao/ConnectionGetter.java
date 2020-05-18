package chess.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionGetter {

    Connection get() throws ClassNotFoundException, SQLException;
}
