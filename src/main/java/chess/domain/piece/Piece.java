package chess.domain.piece;

import chess.domain.File;
import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.Rank;

public abstract class Piece {

    private final PieceType type;
    private final PieceColor color;

    public Piece(PieceType type, PieceColor color) {
        this.type = type;
        this.color = color;
    }

    public abstract boolean canMove(Position source, Position target);

    public PieceType getType() {
        return type;
    }

    public PieceColor getColor() {
        return color;
    }

    protected boolean isSameRank(Position source, Position target) {
        return source.rank() == target.rank();
    }

    protected boolean isSameFile(Position source, Position target) {
        return source.file() == target.file();
    }

    protected int getDeltaRank(Rank source, Rank target) {
        return Math.abs(source.get() - target.get());
    }

    protected int getDeltaFile(File source, File target) {
        return Math.abs(source.get() - target.get());
    }
}
