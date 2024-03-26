package chess.db;

import chess.domain.piece.character.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {
    private final ConnectionGenerator connectionGenerator;

    TurnDao() {
        this(new ProductionConnectionGenerator());
    }

    TurnDao(ConnectionGenerator connectionGenerator) {
        this.connectionGenerator = connectionGenerator;
    }

    public Team load() {
        try (final Connection connection = connectionGenerator.getConnection();) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM turn");
            final ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            return Team.valueOf(resultSet.getString("team"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void change(Team team) {
        try (final Connection connection = connectionGenerator.getConnection();) {
            final PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM turn");
            deleteStatement.execute();
            final PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO turn VALUES (?)");

            insertStatement.setString(1, team.name());
            insertStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Team team) {
        try (final Connection connection = connectionGenerator.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO turn VALUES (?)");

            statement.setString(1, team.name());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("DELETE FROM turn");

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
