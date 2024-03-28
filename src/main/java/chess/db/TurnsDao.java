package chess.db;

import chess.dto.TurnDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnsDao {
    private static final String TABLE = "turns";

    private final ChessGameDBConnector connector;

    public TurnsDao(ChessGameDBConnector connector) {
        this.connector = connector;
    }

    public void create(TurnDto turnDto) {
        try (final Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + TABLE + " (color) VALUES (?)");
            statement.setString(1, turnDto.color());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating turn", e);
        }
    }

    public TurnDto find() {
        try (final Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            return new TurnDto(resultSet.getString("color"));
        } catch (SQLException e) {
            throw new RuntimeException("Error finding turn", e);
        }
    }

    public void deleteAll() {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting all turns", e);
        }
    }
}
