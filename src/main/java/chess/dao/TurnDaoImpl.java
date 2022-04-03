package chess.dao;

import chess.domain.turn.Turn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TurnDaoImpl implements TurnDao {

    private final Connection connection;

    public TurnDaoImpl(final Connection connection) {
        this.connection = connection;
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
}
