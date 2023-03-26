package chess.dto;

import chess.domain.Color;

public class GameDto {
    public static GameDto EMPTY = new GameDto(0, true, Color.EMPTY.name());

    public GameDto(final int id, final boolean status, final String color) {
        this.id = id;
        this.status = status;
        this.color = color;
    }

    private final int id;
    private final boolean status;
    private final String color;

    public int getId() {
        return id;
    }

    public boolean isEnd() {
        return status;
    }

    public String getColor() {
        return color;
    }
}
