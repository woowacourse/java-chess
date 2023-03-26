package chessgame.dao;

import chessgame.domain.chessgame.Camp;

import java.sql.SQLException;

public class GameRoomDao {

    private static final String INSERT_GAME_ROOM_QUERY = "INSERT INTO game_rooms(turn) VALUES(?)";

    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public void addRoom(final Camp camp) {
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(INSERT_GAME_ROOM_QUERY)) {
            preparedStatement.setString(1, camp.name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
