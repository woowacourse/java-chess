package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;

public abstract class Piece {

    private final PieceType name;
    private final PieceColor color;

    public Piece(PieceType name, PieceColor color) {
        this.name = name;
        this.color = color;
    }

    public abstract boolean canMove(Position source, Position target);

    protected static boolean isSameRank(Position source, Position target) {
        return source.rank() == target.rank();
    }

    protected static boolean isSameFile(Position source, Position target) {
        return source.file() == target.file();
    }

    public PieceType getName() {
        return name;
    }

    public PieceColor getColor() {
        return color;
    }
}
