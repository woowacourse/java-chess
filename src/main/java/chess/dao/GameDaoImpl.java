package chess.dao;

import chess.domain.GameState;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameDaoImpl implements GameDao {

    private final ConnectionSetup dbConnectionSetup;

    public GameDaoImpl(ConnectionSetup connectionSetup) {
        this.dbConnectionSetup = connectionSetup;
    }

    @Override
    public void save(long id) {
        String query = "INSERT INTO game VALUES (?, ?)";
        try (Connection connection = dbConnectionSetup.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            pstmt.setString(2, GameState.READY.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(long id) {
        String query = "SELECT * FROM game WHERE id = ?";
        try (Connection connection = dbConnectionSetup.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
