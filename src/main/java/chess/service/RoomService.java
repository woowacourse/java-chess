package chess.service;

import chess.domain.room.Room;
import chess.repository.RoomRepository;
import java.util.List;

public class RoomService {

    private static final String ROOM_NOT_FOUND = "존재하지 않는 게임방입니다.";
    private static final String DUPLICATED_ROOM_NAME = "이미 사용중인 이름입니다.";
    private final RoomRepository roomRepository;

    public RoomService(final RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<String> getRoomNames(final long userId) {
        return roomRepository.findAllByUserId(userId).stream()
                .map(Room::getName)
                .toList();
    }

    public long create(final long userId, final String name) {
        roomRepository.findByUserIdAndName(userId, name)
                .ifPresent(room -> {
                    throw new IllegalArgumentException(DUPLICATED_ROOM_NAME);
                });
        Room room = new Room(userId, name);
        return roomRepository.save(room);
    }

    public long selectRoom(final long userId, final String name) {
        Room room = roomRepository.findByUserIdAndName(userId, name)
                .orElseThrow(() -> new IllegalArgumentException(ROOM_NOT_FOUND));
        return room.getRoomId();
    }
}
