package chess.dao;

import chess.domain.board.Board;
import chess.domain.chessgame.ChessGame;
import chess.dto.web.BoardDto;
import chess.dto.web.GameStatusDto;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayLogDao {

    private static final Gson GSON = new Gson();

    private final ChessDataSource chessDataSource;

    public PlayLogDao(ChessDataSource chessDataSource) {
        this.chessDataSource = chessDataSource;
    }

    public void insert(BoardDto boardDto, GameStatusDto gameStatusDto, String roomId)
        throws SQLException {
        String query = "INSERT INTO play_log (board, game_status, room_id) VALUES (?, ?, ?)";

        try (Connection connection = chessDataSource.connection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, GSON.toJson(boardDto));
            pstmt.setString(2, GSON.toJson(gameStatusDto));
            pstmt.setString(3, roomId);
            pstmt.executeUpdate();
        }
    }

    public BoardDto latestBoard(String roomId) throws SQLException {
        String query = "SELECT board FROM play_log WHERE room_id = (?) ORDER BY last_played_time DESC LIMIT 1";

        try (Connection connection = chessDataSource.connection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, roomId);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (!rs.next()) {
                    generateFirstRow(roomId);
                    return latestBoard(roomId);
                }
                String serializedBoard = rs.getString("board");
                return GSON.fromJson(serializedBoard, BoardDto.class);
            }
        }
    }

    public GameStatusDto latestGameStatus(String roomId) throws SQLException {
        String query = "SELECT game_status FROM play_log WHERE room_id = (?) ORDER BY last_played_time DESC LIMIT 1";

        try (Connection connection = chessDataSource.connection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, roomId);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (!rs.next()) {
                    generateFirstRow(roomId);
                    return latestGameStatus(roomId);
                }
                String serializedStatus = rs.getString("game_status");
                return GSON.fromJson(serializedStatus, GameStatusDto.class);
            }
        }
    }

    private void generateFirstRow(String roomId) throws SQLException {
        Board board = new Board();
        BoardDto boardDto = new BoardDto(board);
        GameStatusDto gameStatusDto =
            new GameStatusDto(new ChessGame(board));
        insert(boardDto, gameStatusDto, roomId);
    }
}
