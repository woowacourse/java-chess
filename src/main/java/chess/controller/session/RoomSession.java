package chess.controller.session;

public class RoomSession {
    private static final ThreadLocal<Room> session = new ThreadLocal<>();

    private RoomSession() {
    }

    public static void add(final Room room) {
        session.set(room);
    }

    public static int getId() {
        final Room room = session.get();
        if (room == null) {
            return 0;
        }
        return room.getId();
    }

    public static String getName() {
        final Room room = session.get();
        if (room == null) {
            return "none";
        }
        return room.getName();
    }

    public static void remove() {
        session.remove();
    }
}
