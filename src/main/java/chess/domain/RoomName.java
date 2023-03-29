package chess.domain;

public class RoomName {
    private static final int MAX_LENGTH = 10;

    private final String roomName;

    public RoomName(String roomName) {
        validateRoomName(roomName);

        this.roomName = roomName;
    }

    private void validateRoomName(String roomName) {
        if (roomName == null) {
            throw new NullPointerException("방 이름은 null이 될 수 없습니다.");
        }

        if (roomName.isEmpty() || roomName.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("방 이름은 1글자 이상, 10글자 이하여야 합니다.");
        }
    }

    public String getRoomName() {
        return roomName;
    }
}
