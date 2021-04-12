package chess.controller.web.dto;

import chess.domain.position.Position;

import java.util.Objects;

public class MoveRequestDto {
    private final long id;
    private final String from;
    private final String to;

    public MoveRequestDto(long id, String from, String to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public Position getFromPosition() {
        return Position.of(from);
    }

    public Position getToPosition() {
        return Position.of(to);
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoveRequestDto)) return false;
        MoveRequestDto that = (MoveRequestDto) o;
        return getId() == that.getId() && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), from, to);
    }
}
