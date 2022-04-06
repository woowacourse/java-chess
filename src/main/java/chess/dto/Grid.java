package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Grid {
    private final String position;
    private final String piece;

    public Grid(Position position, Piece piece) {
        this.position = position.getPositionName();
        this.piece = piece.getPieceName();
    }

    public String getPosition() {
        return position;
    }

    public String getPiece() {
        return piece;
    }
}
