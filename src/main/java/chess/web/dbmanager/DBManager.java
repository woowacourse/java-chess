package chess.web.dbmanager;

import java.sql.Connection;

public interface DBManager {

    Connection getConnection();
}
