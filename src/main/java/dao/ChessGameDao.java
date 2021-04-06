package dao;

import domain.chessgame.ChessGame;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class ChessGameDao {

    private static final String SERVER_PORT_NUMBER = "localhost:13306";
    private static final String DATABASE_NAME = "chess_db";
    private static final String DATABASE_OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String DATABASE_USER_NAME = "root";
    private static final String DATABASE_USER_PASSWORD = "root";


    public Connection getConnection() {
        Connection con = null;
        String server = SERVER_PORT_NUMBER;
        String database = DATABASE_NAME;
        String option = DATABASE_OPTION;
        String userName = DATABASE_USER_NAME;
        String password = DATABASE_USER_PASSWORD;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
        }

        try {
            con = DriverManager
                .getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
                    password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
        }

        return con;
    }

    public ChessGame selectByGameId(int gameId) throws SQLException, IOException {
        String query = "SELECT * FROM chessGame WHERE game_id = ?";

        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, String.valueOf(gameId));
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return new ChessGame();
            }

            return chessGame(rs.getString("serialized_base64_chess_game"));
        }
    }

    public ChessGame updateChessGameByGameId(int gameId, ChessGame chessGame)
        throws SQLException, IOException {
        String query = "INSERT INTO chessGame (game_id, serialized_base64_chess_game) "
            + "VALUES (?, ?) ON DUPLICATE KEY UPDATE serialized_base64_chess_game = ?";

        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, String.valueOf(gameId));
            pstmt.setString(2, serializedChessGame(chessGame));
            pstmt.setString(3, serializedChessGame(chessGame));
            pstmt.executeUpdate();
            return selectByGameId(gameId);
        }
    }

    public String serializedChessGame(ChessGame chessGame) throws IOException {
        byte[] serializedMember;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(chessGame);
                serializedMember = baos.toByteArray();
            }
        }
        return Base64.getEncoder().encodeToString(serializedMember);
    }

    public ChessGame chessGame(String serializedMember) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(
            Base64.getDecoder().decode(serializedMember))) {
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                Object objectMember = ois.readObject();
                return (ChessGame) objectMember;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        throw new IOException();
    }

}
