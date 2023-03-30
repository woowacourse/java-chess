package chess.dao;

public class RoomName {
    private static final int MAX_ROOM_NAME_LENGTH = 12;
    public static final int BLANK_LENGTH = 0;

    private final String roomName;

    public RoomName(String roomName) {
        validateRoomName(roomName);
        this.roomName = roomName;
    }

    private void validateRoomName(String roomName) {
        if (roomName.length() > MAX_ROOM_NAME_LENGTH) {
            throw new IllegalArgumentException("방 이름은 12자를 넘을 수 없습니다.");
        }
        if (roomName.trim().length() == BLANK_LENGTH){
            throw new IllegalArgumentException("방 이름을 입력해 주세요.");
        }
    }

    public String getRoomName() {
        return roomName;
    }
}
