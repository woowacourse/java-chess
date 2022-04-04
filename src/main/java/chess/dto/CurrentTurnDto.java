package chess.dto;

import chess.domain.chesspiece.Color;

public class CurrentTurnDto {

    private final String name;
    private final Color currentTurn;

    private CurrentTurnDto(final String name, final Color currentTurn) {
        this.name = name;
        this.currentTurn = currentTurn;
    }

    public static CurrentTurnDto from(final String name, final String currentTurn) {
        return new CurrentTurnDto(name, Color.from(currentTurn));
    }

    public String getName() {
        return name;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }
}
