package chess.repository;

import java.sql.*;

public class RepositoryUtil {

    private final Connection connection;

    public RepositoryUtil() {
        this.connection = getConnection();
    }

    public static Connection getConnection() {
        Connection con = null;
        String server = "127.0.0.1:13306"; // MySQL 서버 주소
        String database = "Chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    // 드라이버 연결해제
    public static void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public ResultSet executeQuery(String query, String... inserted) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (int i = 1; i <= inserted.length; i++) {
            statement.setString(i, inserted[i - 1]);
        }

        return statement.executeQuery();
    }

    public ResultSet executeUpdate(String query, String... inserted) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (int i = 1; i <= inserted.length; i++) {
            statement.setString(i, inserted[i - 1]);
        }
        statement.executeUpdate();

        return statement.getGeneratedKeys();
    }
}
