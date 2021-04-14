package chess.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChessLogDao {
    private static final String MOVE = "move ";
    private static final String DELIMITER = " ";

    public Connection getConnection() {
        Connection con = null;

        loading();
        return connection(con);
    }

    private void loading() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Connection connection(Connection connection) {
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void addLog(String roomId, String target, String destination) {
        Connection connection = getConnection();
        String query = "INSERT INTO chessgame (room_id, target, destination) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, roomId);
            pstmt.setString(2, target);
            pstmt.setString(3, destination);
            pstmt.executeUpdate();
            closeConnection(connection);
        }
        catch (SQLException e) {
            System.err.println("로그 추가 오류:" + e.getMessage());
            e.printStackTrace();
            closeConnection(connection);
        }
    }

    public List<String> applyCommand(String userId) {
        Connection connection = getConnection();
        String query = "SELECT target, destination FROM chess.chessgame WHERE room_id = ? ORDER BY command_date ASC;";
        try (PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            List<String> commands = new ArrayList<>();

            while (rs.next()) {
                commands.add(MOVE + rs.getString(1) + DELIMITER + rs.getString(2));
            }
            closeConnection(connection);

            return commands;
        }
        catch (SQLException e) {
            System.err.println("커맨드 적용 오류:" + e.getMessage());
            e.printStackTrace();
            closeConnection(connection);
            return null;
        }
    }

    public void deleteLog(String roomId) {
        Connection connection = getConnection();
        String query = "DELETE FROM chess.chessgame WHERE room_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, roomId);
            pstmt.executeUpdate();
            closeConnection(connection);
        }
        catch (SQLException e) {
            System.err.println("로그 제거 오류:" + e.getMessage());
            e.printStackTrace();
            closeConnection(connection);
        }
    }
}
