package chess.dao;

import java.sql.Connection;

public interface ConnectionGenerator {
    String SERVER = "localhost:13306";
    String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    String USERNAME = "root";
    String PASSWORD = "root";

    Connection getConnection();
}
