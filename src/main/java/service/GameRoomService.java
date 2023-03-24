package service;

import java.util.List;

import repository.room.RoomDao;
import utils.StringBytesCalculator;

public class GameRoomService {

    private final RoomDao roomDao;

    public GameRoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void createGameRoom(String gameName) {
        int bytesLength = StringBytesCalculator.calculateBytesLength(gameName);
        System.out.println(bytesLength);
        if(bytesLength > 10) {
            throw new IllegalArgumentException("방 이름은 한글은 최대 5글자, 영어는 최대 10글자 까지 가능합니다.");
        }
        roomDao.createRoom(gameName);
    }

    public List<String> readGameRooms() {
        return roomDao.findAllRooms();
    }

    public long findRoomIdByRoomName(String roomName) {
        return roomDao.findGameIdByGameName(roomName);
    }
}
