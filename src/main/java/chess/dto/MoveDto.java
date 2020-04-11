package chess.dto;

import chess.domain.coordinate.Coordinate;

import java.util.Objects;

public class MoveDto {
    public static final int DEFAULT_MOVE_ID = -1;
    private int moveId;
    private int roomId;
    private Coordinate source;
    private Coordinate target;

    public MoveDto(int moveId, int roomId, Coordinate source, Coordinate target) {
        this.moveId = moveId;
        this.roomId = roomId;
        this.source = source;
        this.target = target;
    }

    public MoveDto(int roomId, Coordinate source, Coordinate target) {
        this(DEFAULT_MOVE_ID, roomId, source, target);
    }

    public int getMoveId() {
        return moveId;
    }

    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }

    public Coordinate getSource() {
        return source;
    }

    public int getRoomId() {
        return roomId;
    }

    public Coordinate getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveDto moveDto = (MoveDto) o;
        return moveId == moveDto.moveId &&
                roomId == moveDto.roomId &&
                Objects.equals(source, moveDto.source) &&
                Objects.equals(target, moveDto.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moveId, roomId, source, target);
    }
}
