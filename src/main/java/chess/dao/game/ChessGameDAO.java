package chess.dao.game;

import chess.domain.piece.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDAO {

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "Chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager
                .getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
                    password);
        } catch (SQLException e) {
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

    public Color getGameTurn(String gameID) throws SQLException {
        String query = "select * from game_chessGame_TB Where Game_ID = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, gameID);
        ResultSet rs = pstmt.executeQuery();

        rs.next();
        Color gameTurn = Color.of(rs.getString("GAME_TURN_NM"));
        if (rs.next()) {
            throw new IllegalAccessError("인자가 너무 많습니다.");
        }
        return gameTurn;
    }
}
