package chess.service;

import java.util.Objects;

public class RoomNumber {

    private static final int RAW_NEW_ROOM_NUMBER = 0;

    private final int roomNumber;

    public RoomNumber(final int roomNumber) {
        validate(roomNumber);
        this.roomNumber = roomNumber;
    }

    private void validate(final int roomNumber) {
        if (roomNumber < RAW_NEW_ROOM_NUMBER) {
            throw new IllegalArgumentException("방 번호는 0 이상의 값입니다.");
        }
    }

    public boolean isNewRoomNumber() {
        return roomNumber == RAW_NEW_ROOM_NUMBER;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RoomNumber that = (RoomNumber) o;
        return roomNumber == that.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
