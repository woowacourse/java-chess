package web;

import chess.piece.Color;
import chess.piece.Piece;
import chess.position.Position;

class PieceDTO {
    private final Position position;
    private final Color color;
    private final PieceType type;

    public PieceDTO(Position position, Piece piece) {
        this.position = position;
        this.color = piece.getColor();
        this.type = PieceType.valueOf(piece);
    }

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public String getPosition() {
        return position.toString();
    }
}
