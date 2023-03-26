package chessgame.dao;

import chessgame.domain.chessgame.Camp;
import chessgame.dto.GameRoomDto;

import java.sql.SQLException;

public class GameRoomDao {

    private static final String INSERT_GAME_ROOM_QUERY = "INSERT INTO game_rooms(turn) VALUES(?)";
    private static final String FIND_GAME_ROOM_QUERY = "SELECT * FROM game_rooms WHERE id = ?";

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

    public GameRoomDto findGameRoomById(long roomId) {
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(FIND_GAME_ROOM_QUERY)) {
            preparedStatement.setLong(1, roomId);
            final var result = preparedStatement.executeQuery();
            if (result.next()) {
                return new GameRoomDto(
                        result.getLong("id"),
                        result.getString("turn")
                );
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        throw new IllegalArgumentException("[ERROR] 해당 아이디의 게임을 찾지 못했습니다.");
    }
}
