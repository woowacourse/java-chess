package chess.persistence.dao;

import chess.persistence.dao.connector.DataSourceFactory;
import chess.persistence.dto.ChessGameDTO;

import java.sql.*;

public class ChessGameDAO {
    public static ChessGameDAO getInstance() {
        return ChessGameDAOHandler.INSTANCE;
    }

    public long addGameStatus(ChessGameDTO gameStatus) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection()) {
            String query = "INSERT INTO game(game_status, last_user) VALUES (?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            pstmt.setBoolean(1, gameStatus.isGameStatus());
            pstmt.setString(2, gameStatus.getLastUser());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public ChessGameDTO findByMaxGameId() {
        try (Connection connection = DataSourceFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM chess.game ORDER BY game_id DESC LIMIT 1;";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS);

            ChessGameDTO findGameStatusInfo = new ChessGameDTO();
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                findGameStatusInfo.setGameId(0);
                return findGameStatusInfo;
            }

            findGameStatusInfo.setGameId(rs.getInt("game_id"));
            return findGameStatusInfo;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private static class ChessGameDAOHandler {
        static final ChessGameDAO INSTANCE = new ChessGameDAO();
    }
}
