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

    public void insert(BoardDto boardDto, GameStatusDto gameStatusDto, String roomId) {
        String query = "INSERT INTO play_log (board, game_status, room_id) VALUES (?, ?, ?)";

        try (Connection connection = chessDataSource.connection();
            PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setString(1, GSON.toJson(boardDto));
            pstmt.setString(2, GSON.toJson(gameStatusDto));
            pstmt.setString(3, roomId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("플레이 기록을 db에 저장하는 중 문제가 발생했습니다.", e);
        }
    }

    public BoardDto latestBoard(String roomId) {
        String query = "SELECT board FROM play_log WHERE room_id = (?) ORDER BY last_played_time DESC, id DESC LIMIT 1";

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
        } catch (SQLException e) {
            throw new IllegalStateException("최근 보드를 db에 불러오는 중에 문제가 발생했습니다.", e);
        }
    }

    public GameStatusDto latestGameStatus(String roomId) {
        String query = "SELECT game_status FROM play_log WHERE room_id = (?) ORDER BY last_played_time DESC, id DESC LIMIT 1";

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
        } catch (SQLException e) {
            throw new IllegalStateException("최근 게임 상태를 db에 불러오는 중에 문제가 발생했습니다.", e);
        }
    }

    private void generateFirstRow(String roomId) {
        Board board = new Board();
        BoardDto boardDto = new BoardDto(board);
        GameStatusDto gameStatusDto = new GameStatusDto(new ChessGame(board));
        insert(boardDto, gameStatusDto, roomId);
    }
}
