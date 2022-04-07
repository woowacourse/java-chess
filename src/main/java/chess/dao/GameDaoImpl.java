package chess.dao;

import chess.domain.GameState;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

;

public class GameDaoImpl implements GameDao {

    private final ConnectionSetup connectionSetup;

    public GameDaoImpl(ConnectionSetup connectionSetup) {
        this.connectionSetup = connectionSetup;
    }

    @Override
    public void save(long id) {
        String query = "INSERT INTO game VALUES (?, ?)";
        try (Connection connection = connectionSetup.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            pstmt.setString(2, GameState.READY.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<GameState> load(long id) {
        try (Connection connection = connectionSetup.getConnection();
             PreparedStatement pstmt = createLoadPreparedStatement(connection, id);
             ResultSet rs = pstmt.executeQuery()) {
            return getGame(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private PreparedStatement createLoadPreparedStatement(Connection connection, long id) throws SQLException {
        String query = "SELECT * FROM game WHERE game_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setLong(1, id);
        return pstmt;
    }

    private Optional<GameState> getGame(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return Optional.of(GameState.valueOf(rs.getString("state")));
        }
        return Optional.empty();
    }

    @Override
    public void updateState(long id, GameState gameState) {
        String query = "UPDATE game SET state = ? WHERE game_id = ?";
        try (Connection connection = connectionSetup.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, gameState.name());
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String query = "DELETE FROM game WHERE game_id = ?";
        try (Connection connection = connectionSetup.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM game";
        try (Connection connection = connectionSetup.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
