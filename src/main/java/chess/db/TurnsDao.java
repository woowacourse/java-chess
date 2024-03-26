package chess.db;

import chess.dto.TurnDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Supplier;

public class TurnsDao {
    private static final String TABLE = "turns";

    private final Supplier<Connection> connector;

    public TurnsDao(Supplier<Connection> connector) {
        this.connector = connector;
    }

    public void create(TurnDto turnDto) {
        try (final Connection connection = connector.get()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + TABLE + " VALUES (?)");
            statement.setString(1, turnDto.color());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating turn", e);
        }
    }

    public TurnDto find() {
        try (final Connection connection = connector.get()) {
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
        try (Connection connection = connector.get()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting all turns", e);
        }
    }
}
