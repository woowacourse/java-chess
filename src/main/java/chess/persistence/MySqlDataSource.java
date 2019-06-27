package chess.persistence;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlDataSource implements AbstractDataSource {
    private static final String HOST = "localhost"; // MySQL 서버 주소
    private static final String DB_NAME = "chess_db"; // MySQL DATABASE 이름
    private static final String USER = "user"; //  MySQL 서버 아이디
    private static final String PASSWORD = "gmlgus12"; // MySQL 서버 비밀번호

    @Override
    public Connection getConnection() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUser(USER);
        ds.setPassword(PASSWORD);
        ds.setUrl("jdbc:mysql://" + HOST + "/" + DB_NAME + "?serverTimezone=UTC&useSSL=false");

        return ds.getConnection();
    }
}
