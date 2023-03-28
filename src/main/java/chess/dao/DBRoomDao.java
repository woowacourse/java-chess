package chess.dao;

import chess.domain.Room;
import chess.domain.piece.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class DBRoomDao {

    public int insert(final Room room) {
        final var query = "INSERT INTO chess_game(turn) VALUES (?);";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, room.getTurn().name());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Room select(final int id) {
        final var query = "SELECT * FROM chess_game WHERE id = ?";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final Team turn = Team.valueOf(resultSet.getString("turn"));
                //final Board board = dbBoardDao.select(id);
                return new Room(id, null, turn);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Room> selectAll() {
        List<Room> rooms = new ArrayList<>();
        final var query = "SELECT * FROM chess_game";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Team turn = Team.valueOf(resultSet.getString("turn"));
                rooms.add(new Room(id, null, turn));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }

    public void update(final Room room) {
        final var query = "UPDATE chess_game SET turn = ? WHERE id = ?;";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, room.getTurn().name());
            preparedStatement.setInt(2, room.getId());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(final Room room) {
        final var query = "DELETE FROM chess_game WHERE id = ?;";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, room.getId());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
