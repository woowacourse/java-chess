package chess.dao.dto;

import chess.domain.piece.attribute.Color;

public class ChessGame {
    private final Long id;
    private final Color nextTurn;
    private final boolean running;
    private final String pieces;

    public ChessGame(Long id, Color nextTurn, boolean running, String pieces) {
        this.id = id;
        this.nextTurn = nextTurn;
        this.running = running;
        this.pieces = pieces;
    }

    public Long getId() {
        return id;
    }

    public Color getNextTurn() {
        return nextTurn;
    }

    public boolean isRunning() {
        return running;
    }

    public String getPieces() {
        return pieces;
    }
}
