package chess.controller.web.dto;

import chess.domain.position.Position;

import java.util.Objects;

public class MoveRequestDto {
    private final String from;
    private final String to;
    private final String id;

    public MoveRequestDto(String from, String to, String id) {
        this.from = from;
        this.to = to;
        this.id = id;
    }

    public Position getFromPosition() {
        return Position.of(from);
    }

    public Position getToPosition() {
        return Position.of(to);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoveRequestDto)) return false;
        MoveRequestDto that = (MoveRequestDto) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, getId());
    }
}
