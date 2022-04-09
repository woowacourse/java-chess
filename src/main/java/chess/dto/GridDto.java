package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class GridDto {
    private final String position;
    private final String piece;
    private final int moveCount;

    public GridDto(Position position, Piece piece) {
        this.position = position.getPositionName();
        this.piece = piece.getPieceName();
        this.moveCount = piece.getMoveCount();
    }

    public String getPosition() {
        return position;
    }

    public String getPiece() {
        return piece;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
