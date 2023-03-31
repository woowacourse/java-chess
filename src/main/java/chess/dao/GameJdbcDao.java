package chess.dao;

import chess.domain.Color;
import chess.dto.GameDto;

import java.sql.*;

public class GameJdbcDao implements GameDao {
    private final Connection connection;

    public GameJdbcDao(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public GameDto create() {
        final var query = "INSERT INTO game(status,color) VALUES(?, ?)";
        try (final PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setBoolean(1, false);
            ps.setString(2, Color.WHITE.name());
            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                return GameDto.of(
                        resultSet.getInt(1)
                );
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return GameDto.EMPTY;
    }

    @Override
    public GameDto findByLastGame() {
        final var query = "SELECT * FROM game WHERE id = (SELECT MAX(id) as id FROM game);";
        try (final PreparedStatement ps = connection.prepareStatement(query)) {
            final var resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return GameDto.from(
                        resultSet.getInt("id"),
                        resultSet.getBoolean("status"),
                        resultSet.getString("color")
                );
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return GameDto.EMPTY;
    }

    @Override
    public void update(final boolean status, final int gameId) {
        final var query = "UPDATE game SET status=? WHERE id = ? ";
        try (final PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, status);
            ps.setInt(2, gameId);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(final Color color, final int gameId) {
        final var query = "UPDATE game SET color=? WHERE id = ? ";
        try (final PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, color.reverse().name());
            ps.setInt(2, gameId);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
