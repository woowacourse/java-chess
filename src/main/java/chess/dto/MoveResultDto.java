package chess.dto;

import chess.domain.piece.MoveResult;

public class MoveResultDto {
    private final String piece;
    private final String from;
    private final String to;
    private final MoveResult moveResult;

    public MoveResultDto(String piece, String from, String to, MoveResult moveResult) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.moveResult = moveResult;
    }

    public String getPiece() {
        return piece;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public MoveResult getMoveResult() {
        return moveResult;
    }
}

