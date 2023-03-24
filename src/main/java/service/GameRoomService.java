package service;

import java.util.List;

import repository.room.RoomDao;

public class GameRoomService {

    private final RoomDao roomDao;

    public GameRoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void createGameRoom(String gameName) {
        roomDao.createRoom(gameName);
    }

    public List<String> readGameRooms() {
        return roomDao.findAllRooms();
    }

    public long findRoomIdByRoomName(String roomName) {
        return roomDao.findGameIdByGameName(roomName);
    }
}
