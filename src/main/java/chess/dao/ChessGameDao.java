package chess.dao;

import chess.domain.Color;
import chess.dto.ColorDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public ColorDto findByName(String name) {
        final String sql = "SELECT * FROM chess_game WHERE name = ?";

        try (final Connection connection = DatabaseConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            try (final ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                Color chessTurn = Color.from(resultSet.getString("turn"));
                return ColorDto.from(chessTurn);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
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
