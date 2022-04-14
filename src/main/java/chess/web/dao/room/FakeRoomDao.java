package chess.web.dao.room;

import chess.web.Room;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeRoomDao implements RoomDao {

    private final HashMap<Integer, Room> rooms = new HashMap<>();
    private int ids = 0;


    @Override
    public void save(final String name) {
        ids++;
        rooms.put(ids, new Room(ids, name, 1, "WHITE"));
    }

    @Override
    public int findIdByName(final String name) {
        return (int) rooms.values()
            .stream()
            .filter(room -> room.getName().equals(name))
            .findFirst()
            .get()
            .getId();
    }

    @Override
    public void removeById(final int id) {
        rooms.remove(id);
    }

    @Override
    public Room findById(final int id) {
        return rooms.get(id);
    }

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(rooms.values());
    }

    @Override
    public void updateNameById(final int id, final String name) {
        final Room room = rooms.get(id);
        final Room newRoom = new Room(room.getId(), name, room.getCanJoin(), room.getCurrentCamp());
        rooms.put(id, newRoom);
    }

    @Override
    public void updateRoom(final int roomId, final int canJoin, final String currentCamp) {
        final Room room = rooms.get(roomId);
        final Room newRoom = new Room(room.getId(), room.getName(), canJoin, currentCamp);
        rooms.put(roomId, newRoom);
    }
}
