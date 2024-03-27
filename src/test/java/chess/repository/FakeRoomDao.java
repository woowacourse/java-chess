package chess.repository;

import static chess.fixture.RoomFixture.createBigChessRoom;
import static chess.fixture.RoomFixture.createChessRoom;
import static chess.fixture.RoomFixture.createSmallChessRoom;

import chess.domain.room.Room;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeRoomDao implements RoomRepository {
    private final Map<Integer, Room> rooms = new HashMap<>();

    public FakeRoomDao() {
        initialize();
    }

    @Override
    public long save(final Room room) {
        if (room.getRoomId() == null) {
            rooms.put(rooms.size() + 1, new Room((long) rooms.size() + 1, room.getUserId(), room.getName()));
            return rooms.size();
        }
        rooms.put(rooms.size() + 1, room);
        return rooms.size();
    }

    @Override
    public List<Room> findAllByUserId(final long userId) {
        return rooms.values().stream()
                .filter(room -> room.getUserId() == userId)
                .toList();
    }

    @Override
    public Optional<Room> findByUserIdAndName(final long userId, final String name) {
        return rooms.values().stream()
                .filter(room -> room.getUserId() == userId && room.getName().equals(name))
                .findAny();
    }

    public void deleteAll() {
        rooms.clear();
        initialize();
    }

    private void initialize() {
        rooms.put(1, createChessRoom());
        rooms.put(2, createBigChessRoom());
        rooms.put(3, createSmallChessRoom());
    }
}
