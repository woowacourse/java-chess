package dto;

import chess.domain.Aliance;

import java.util.Objects;

public class GameDto {
    private final int id;
    private final boolean isEnd;
    private final Aliance turn;

    public GameDto(int id, boolean isEnd, int teamId) {
        this.id = id;
        this.isEnd = isEnd;
        this.turn = Aliance.valueOf(teamId);
    }

    public int getId() {
        return id;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public Aliance getTurn() {
        return turn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDto gameDto = (GameDto) o;
        return id == gameDto.id &&
                isEnd == gameDto.isEnd &&
                turn == gameDto.turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isEnd, turn);
    }
}
