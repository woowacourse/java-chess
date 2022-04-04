package chess.dao;

import chess.domain.turn.Turn;
import chess.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TurnDaoImpl implements TurnDao {

    private final Connection connection;

    public TurnDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public TurnDaoImpl() {
        this(DBConnection.getConnection());
    }

    @Override
    public Optional<Turn> findCurrentTurn() {
        final String query = "select turn from chess_game";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                String turn = resultSet.getString("turn");
                return Optional.of(Turn.valueOf(turn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateTurn(Turn currentTurn, Turn turn) {
        final String query = "update chess_game set turn = ? where turn = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, turn.name());
            pstmt.setString(2, currentTurn.name());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
