package database;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Symbol;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ChessGameDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306";
        String database = "chess_db";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public void addChessGame(ChessGame chessGame) throws SQLException, IOException {
        if (!Objects.isNull(findByGameId("1"))) {
            deleteChessGame("1");
        }

        Map<Position, Piece> chessBoard = chessGame.board();
        JSONObject jsonObject = new JSONObject();

        for (Position position : chessBoard.keySet()) {
            jsonObject.put(position.toString(), chessBoard.get(position).getSymbol());
        }

        try (Connection con = getConnection()) {
            String query = "INSERT INTO chessGame VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, "1");
            pstmt.setString(2, chessGame.state().toString());
            pstmt.setString(3, jsonObject.toJSONString());
            pstmt.executeUpdate();
        }
    }

    public void deleteChessGame(String gameId) throws SQLException {
        try (Connection con = getConnection()) {
            String query = "DELETE FROM chessGame WHERE game_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, gameId);
            pstmt.executeUpdate();
        }
    }

    public ChessGame findByGameId(String gameId) throws SQLException, IOException {
        LinkedHashMap<String, String> jsonMap;
        String state;

        try (Connection con = getConnection()) {
            String query = "SELECT * FROM chessGame WHERE game_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, gameId);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            jsonMap = new ObjectMapper().readValue(rs.getString("board_info"), LinkedHashMap.class);
            state = rs.getString("turn");
        }

        Map<Position, Piece> chessBoard = new LinkedHashMap<>();

        for (String positionKey : jsonMap.keySet()) {
            chessBoard.put(Position.of(positionKey), Symbol.getPiece(jsonMap.get(positionKey)));
        }

        ChessGame chessGame = new ChessGame(new Board(chessBoard));
        chessGame.setState(state);
        return chessGame;
    }
}
