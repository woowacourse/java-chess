package repository.room;

import java.util.List;

public interface RoomDao {
    long createRoom(String gameName);

    List<String> findAllRooms();

    void deleteAllGame();

    long findGameIdByGameName(String gameName);
}
