package chess.domain.piece;

import chess.domain.board.Position;

import static chess.util.NullValidator.validateNull;

public abstract class Piece {
    private final PieceColor pieceColor;
    private final String name;
    protected Position position;

    public Piece(String name, PieceColor pieceColor, Position position) {
        validateNull(name, pieceColor, position);
        this.name = name;
        this.pieceColor = pieceColor;
        this.position = position;
    }

    public boolean isNoneColor() {
        return pieceColor.isNoneColor();
    }

    public String getName() {
        return pieceColor.getPieceName(name);
    }
}
