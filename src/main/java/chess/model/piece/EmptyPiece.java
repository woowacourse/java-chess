package chess.model.piece;

import chess.model.MoveType;
import chess.model.Path;
import chess.model.PieceColor;

public class EmptyPiece extends Piece {

    private static final String EMBLEM = ".";
    private static final double SCORE = 0;

    private static final Piece EMPTY_PIECE = new EmptyPiece(PieceColor.EMPTY);

    private EmptyPiece(PieceColor pieceColor) {
        super(pieceColor);
    }

    public static Piece of(PieceColor pieceColor) {
        return EMPTY_PIECE;
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        return false;
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
