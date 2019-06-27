package chess.persistence.dao;

import chess.persistence.dao.connector.DataSourceFactory;
import chess.persistence.dto.ChessGameDTO;

import java.sql.*;

public class ChessGameDAO {
    public static ChessGameDAO getInstance() {
        return ChessGameDAOHandler.INSTANCE;
    }

    private ChessGameDAO() {
    }

    public long addGameStatus(ChessGameDTO chessGameDTO) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection()) {
            String query = "INSERT INTO game(game_status, last_user) VALUES (?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            pstmt.setBoolean(1, chessGameDTO.getGameStatus());
            pstmt.setString(2, chessGameDTO.getLastUser());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> game 상태 데이터 넣는 부분");
        }
    }

    public long updateGameStatus(ChessGameDTO chessGameDTO) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection()) {
            String query = "UPDATE game SET game_status=?, last_user=? WHERE game_id=?";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            pstmt.setBoolean(1, chessGameDTO.getGameStatus());
            pstmt.setString(2, chessGameDTO.getLastUser());
            pstmt.setInt(3, chessGameDTO.getGameId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> game상태 업데이트 부분");
        }
    }

    public ChessGameDTO findByMaxGameId() {
        try (Connection connection = DataSourceFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM chess.game ORDER BY game_id DESC LIMIT 1;";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS);

            ChessGameDTO chessGameDTO = new ChessGameDTO();
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                chessGameDTO.setGameId(0);
                return chessGameDTO;
            }
            chessGameDTO.setGameId(rs.getInt("game_id"));
            chessGameDTO.setGameStatus(rs.getBoolean("game_status"));
            chessGameDTO.setLastUser(rs.getString("last_user"));
            return chessGameDTO;
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> maxGameId 찾는 부분");
        }
    }

    private static class ChessGameDAOHandler {
        static final ChessGameDAO INSTANCE = new ChessGameDAO();
    }
}
