package chessgame.repository;

import chessgame.domain.chessgame.Camp;
import chessgame.dto.GameRoomDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameRoomDao {

    private static final String INSERT_GAME_ROOM_QUERY = "INSERT INTO game_rooms(turn) VALUES(?)";
    private static final String FIND_ALL_GAME_ROOM_QUERY = "SELECT * FROM game_rooms";
    private static final String FIND_GAME_ROOM_QUERY = "SELECT * FROM game_rooms WHERE id = ?";
    private static final String FIND_LEAST_GAME_ROOM_QUERY =
            "select * from game_rooms order by id desc limit 1";
    private static final String UPDATE_GAME_ROOM_QUERY =
            "UPDATE game_rooms SET turn = ? WHERE id = ?";
    private static final String DELETE_GAME_ROOM_QUERY = "DELETE FROM game_rooms WHERE id = ?";

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

    public List<GameRoomDto> findAllGameRoom() {
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(FIND_ALL_GAME_ROOM_QUERY)) {
            final var result = preparedStatement.executeQuery();

            List<GameRoomDto> rooms = new ArrayList<>();

            while (result.next()) {
                GameRoomDto gameRoomDto = new GameRoomDto(
                        result.getLong("id"),
                        result.getString("turn")
                );
                rooms.add(gameRoomDto);
            }
            return rooms;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public GameRoomDto findGameRoomById(final long roomId) {
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

    public GameRoomDto findLeastGameRoom() {
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(FIND_LEAST_GAME_ROOM_QUERY)) {
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
        throw new IllegalArgumentException("[ERROR] 가장 최신 게임을 찾지 못했습니다.");
    }

    public void updateGameRoomById(final long roomId, final Camp camp) {
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(UPDATE_GAME_ROOM_QUERY)) {
            preparedStatement.setString(1, camp.name());
            preparedStatement.setLong(2, roomId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteGameRoomById(final long roomId) {
        try (final var connection = databaseConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(DELETE_GAME_ROOM_QUERY)) {
            preparedStatement.setLong(1, roomId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
