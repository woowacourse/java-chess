package chess.dao;

import chess.domain.piece.Color;
import chess.dto.ChessBoardDto;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameDAO {
    private static final int UNIQUE_GAME_ID = 1;

    private final Gson gson = new Gson();

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306";  // hostname:port number
        String database = "chess_database"; // database 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";           // db userName
        String password = "root";           // db password

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
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void saveGame(ChessBoardDto chessBoardDto, Color currentTurnColor) throws SQLException {
        deleteAllGame();    // 하나의 row만을 이용하기 위해 삭제합니다.
        String query = "INSERT INTO game VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, UNIQUE_GAME_ID);    // 하나의 row만을 이용합니다.
        pstmt.setString(2, gson.toJson(chessBoardDto));
        pstmt.setString(3, currentTurnColor.name());
        pstmt.executeUpdate();
    }

    public void deleteAllGame() throws SQLException {
        String query = "DELETE FROM game";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}
