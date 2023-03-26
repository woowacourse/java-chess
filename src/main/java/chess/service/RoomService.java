package chess.service;

import chess.domain.piece.Color;
import chess.domain.room.Room;
import chess.repository.RoomDao;
import java.util.List;
import java.util.stream.Collectors;

public class RoomService {

    private final RoomDao roomDao;

    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public List<String> findOngoingRoomNames(long userId) {
        List<Room> rooms = roomDao.findAllByUserId(userId);
        return rooms.stream()
                .filter(room -> room.getWinner() == Color.NONE)
                .map(Room::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public long create(long userId, String name) {
        if (roomDao.findByUserIdAndName(userId, name) != null) {
            throw new IllegalArgumentException("이미 사용중인 이름입니다.");
        }
        Room room = new Room(userId, name);
        return roomDao.save(room);
    }

    public long selectRoom(long userId, String name) {
        Room room = roomDao.findByUserIdAndName(userId, name);
        if (room == null) {
            throw new IllegalArgumentException("존재하지 않는 게임방입니다.");
        }
        if (room.getWinner() != Color.NONE) {
            throw new IllegalArgumentException("진행중인 방이 아닙니다.");
        }
        return room.getId();
    }
}
