package chess.persistence.dao.core;

import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;
import chess.persistence.dao.connector.DataSourceFactory;
import chess.persistence.dto.ChessGameDTO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JDBCTemplate {
    private JDBCTemplate() {
    }

    public static JDBCTemplate getInstance() {
        return JdbcTemplateHandler.INSTANCE;
    }

    public long update(String query, int gameId, int roundNo, int x, int y, String type, String team) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS)) {
            pstmt.setInt(1, gameId);
            pstmt.setInt(2, roundNo);
            pstmt.setInt(3, x);
            pstmt.setInt(4, y);
            pstmt.setString(5, type);
            pstmt.setString(6, team);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> Board 데이터 넣는 부분\n" + e.getMessage());
        }
    }

    public long update(String query, boolean gameStatus, String lastUser) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS)) {
            pstmt.setBoolean(1, gameStatus);
            pstmt.setString(2, lastUser);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> Game 데이터 넣는 부분");
        }
    }

    public long update(String query, boolean gameStatus, String lastUser, int gameId) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS)) {
            pstmt.setBoolean(1, gameStatus);
            pstmt.setString(2, lastUser);
            pstmt.setInt(3, gameId);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> Game상태 업데이트 부분");
        }
    }

    public Map<Square, Piece> queryForBoard(String query, int gameId, int roundNo) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS)) {
            pstmt.setInt(1, gameId);
            pstmt.setInt(2, roundNo);

            ResultSet rs = pstmt.executeQuery();
            Map<Square, Piece> board = new HashMap<>();
            while (rs.next()) {
                board.put(Square.of(rs.getInt("square_x"), rs.getInt("square_y"))
                        , Type.valueOf(rs.getString("piece_type")).create(Team.valueOf(rs.getString("team"))));
            }
            rs.close();

            return board;
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> 보드상태 찾는 부분\n" + e.getMessage());
        }
    }

    public int queryForBoard(String query, int gameId) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS)) {
            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();

            int result = rs.next() ? rs.getInt("round_no") : -1;
            rs.close();

            return result;
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> maxRound 찾는 부분");
        }
    }

    public ChessGameDTO queryForGame(String query) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS)) {
            ResultSet rs = pstmt.executeQuery();
            ChessGameDTO chessGameDTO = new ChessGameDTO();

            if (!rs.next()) {
                chessGameDTO.setGameId(0);
                return chessGameDTO;
            }
            chessGameDTO.setGameId(rs.getInt("game_id"));
            chessGameDTO.setGameStatus(rs.getBoolean("game_status"));
            chessGameDTO.setLastUser(rs.getString("last_user"));

            rs.close();
            return chessGameDTO;
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> maxGameId 찾는 부분");
        }
    }

    private static class JdbcTemplateHandler {
        private static final JDBCTemplate INSTANCE = new JDBCTemplate();
    }
}
