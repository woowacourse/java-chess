package chess.service;

import chess.domain.room.Room;
import chess.repository.RoomRepository;
import java.util.List;

public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(final RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<String> getRoomNames(final long userId) {
        return roomRepository.findAllByUserId(userId).stream()
                .map(Room::getName)
                .toList();
    }


    public long create(long userId, String name) {
        roomRepository.findByUserIdAndName(userId, name)
                .ifPresent(room -> {
                    throw new IllegalArgumentException("이미 사용중인 이름입니다.");
                });
        Room room = new Room(userId, name);
        return roomRepository.save(room);
    }

    public long selectRoom(long userId, String name) {
        Room room = roomRepository.findByUserIdAndName(userId, name)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임방입니다."));
        return room.getRoomId();
    }
}
