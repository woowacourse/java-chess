package chess.dto;

import java.util.Objects;

public class RoomDto {
    public static final int DEFAULT_ROOM_ID = -1;
    public static final int DEFAULT_USER_ID = -1;
    public static final String DEFAULT_NAME = "default";

    private int roomId;
    private String name;
    private int blackUserId;
    private int whiteUserId;
    private boolean isEnd;

    public RoomDto(int roomId, int blackUserId, int whiteUserId, boolean isEnd, String name) {
        this.roomId = roomId;
        this.blackUserId = blackUserId;
        this.whiteUserId = whiteUserId;
        this.isEnd = isEnd;
        this.name = name;
    }

    public RoomDto() {
        this(DEFAULT_ROOM_ID, DEFAULT_USER_ID, DEFAULT_USER_ID, false, DEFAULT_NAME);
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlackUserId() {
        return blackUserId;
    }

    public void setBlackUserId(int blackUserId) {
        this.blackUserId = blackUserId;
    }

    public int getWhiteUserId() {
        return whiteUserId;
    }

    public void setWhiteUserId(int whiteUserId) {
        this.whiteUserId = whiteUserId;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDto roomDto = (RoomDto) o;
        return roomId == roomDto.roomId &&
                blackUserId == roomDto.blackUserId &&
                whiteUserId == roomDto.whiteUserId &&
                isEnd == roomDto.isEnd &&
                Objects.equals(name, roomDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, name, blackUserId, whiteUserId, isEnd);
    }
}
