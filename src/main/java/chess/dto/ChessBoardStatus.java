package chess.dto;

import chess.domain.Camp;

public class ChessBoardStatus {

    private final Camp currentTurn;
    private final boolean isOver;

    public ChessBoardStatus(final Camp currentTurn, final boolean isOver) {
        this.currentTurn = currentTurn;
        this.isOver = isOver;
    }

    public Camp getCurrentTurn() {
        return currentTurn;
    }

    public boolean isOver() {
        return isOver;
    }
}
