package chess.controller.session;

public class RoomSession {
    private static RoomSession INSTANCE = new RoomSession();

    private ThreadLocal<Room> session = new ThreadLocal<>();

    private RoomSession() {
    }

    public static RoomSession getInstance() {
        return INSTANCE;
    }

    public void add(final Room room) {
        session.set(room);
    }

    public int getId() {
        final Room room = session.get();
        if (room == null) {
            return 0;
        }
        return room.getId();
    }

    public String getName() {
        final Room room = session.get();
        if (room == null) {
            return "none";
        }
        return room.getName();
    }

    public void remove() {
        session.remove();
    }
}
