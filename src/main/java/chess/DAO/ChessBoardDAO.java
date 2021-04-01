package chess.DAO;


import chess.domain.board.ChessBoard;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessBoardDAO {

    private final Connection connection;

    public ChessBoardDAO() {
        this.connection = getConnection();
    }

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

    public void addRoomInformation(String roomName, ChessBoard chessboard, Gson gson)
        throws SQLException {
        String query = "INSERT INTO room VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, roomName);
        pstmt.setString(2, gson.toJson(chessboard));
        pstmt.executeUpdate();
    }

    public ChessBoard findChessBoardByRoom(String roomName, Gson gson) throws SQLException {
        String query = "SELECT * FROM room WHERE name = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, roomName);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        ChessBoard chessboard = gson.fromJson(rs.getString("chess_board"), ChessBoard.class);
        return chessboard;
    }

}