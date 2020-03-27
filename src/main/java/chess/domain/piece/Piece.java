package chess.domain.piece;

import chess.domain.board.Position;

import static chess.util.NullValidator.validateNull;

public abstract class Piece {
    protected final PieceColor pieceColor;
    private final String name;
    private Position position;

    public Piece(String name, PieceColor pieceColor, Position position) {
        validateNull(name, pieceColor, position);
        this.name = name;
        this.pieceColor = pieceColor;
        this.position = position;
    }

    public String getName() {
        return pieceColor.getPieceName(name);
    }
}
