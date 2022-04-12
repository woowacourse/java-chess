package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Locale;

public class GridDto {
    private final String position;
    private final String pieceType;
    private final String color;
    private final int moveCount;

    public GridDto(Position position, Piece piece) {
        this.position = position.getPositionName();
        this.pieceType = piece.getPieceType().name().toLowerCase();
        this.color = piece.getColor().name().toLowerCase();
        this.moveCount = piece.getMoveCount();
    }

    public String getPosition() {
        return position;
    }

    public String getPieceType() {
        return pieceType;
    }

    public String getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
