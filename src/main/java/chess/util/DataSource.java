package chess.util;

import java.sql.Connection;

public interface DataSource {

    Connection getConnection();
}
