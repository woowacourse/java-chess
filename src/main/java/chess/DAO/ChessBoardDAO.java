package chess.DAO;

import chess.domain.DTO.MoveRequestDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardDAO {

    private Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "chess_game"; // MySQL DATABASE 이름
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
            con = DriverManager
                .getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
                    password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }


    public void addLog(String source, String target) throws SQLException {
        String query = "INSERT INTO log VALUES (?, ?)";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, source);
        pstmt.setString(2, target);
        pstmt.executeUpdate();

        closeConnection(connection);
    }

    public void clearLog() throws SQLException {
        String query = "DELETE FROM log";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.executeUpdate();

        closeConnection(connection);
    }

    public List<MoveRequestDTO> getLog() throws SQLException {
        String query = "SELECT * FROM log";
        Connection connection = getConnection();
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        List<MoveRequestDTO> resultLog = new ArrayList<>();
        while (rs.next()) {
            String source = rs.getString("source");
            String target = rs.getString("target");
            resultLog.add(new MoveRequestDTO(source, target));
        }
        closeConnection(connection);
        return resultLog;
    }
}