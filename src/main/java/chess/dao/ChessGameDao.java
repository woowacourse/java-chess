package chess.dao;

import chess.domain.Color;
import chess.dto.ColorDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChessGameDao {

    public int save(String name, String turn) {
        final String sql = "INSERT INTO chess_game (name, turn) VALUE (?, ?)";

        try (final Connection connection = DatabaseConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, turn);
            return statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public Optional<ColorDto> findByName(String name) {
        final String sql = "SELECT * FROM chess_game WHERE name = ?";

        try (final Connection connection = DatabaseConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            try (final ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                Color chessTurn = Color.from(resultSet.getString("turn"));
                return Optional.of(ColorDto.from(chessTurn));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public void deleteByName(String name) {
        final String sql = "DELETE FROM chess_game WHERE name = ?";
        try (final Connection connection = DatabaseConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTurn(String turn, String name) {
        final String sql = "UPDATE chess_game SET turn = ? WHERE name = ?";
        try (final Connection connection = DatabaseConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, turn);
            statement.setString(2, name);
            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
