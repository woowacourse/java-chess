package chess.utils.validator;

public class RoomValidator {

    public static void checkUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }

    public static void checkRoomName(String roomName) {
        if (roomName.isEmpty()) {
            throw new IllegalArgumentException("Max room name length is 20");
        }
        if (roomName.length() > 20) {
            throw new IllegalArgumentException("Max room name length is 20");
        }
    }
}
