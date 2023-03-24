package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;

public class Empty extends Piece {
    private static final Empty empty = new Empty();

    private Empty() {
        super(Camp.EMPTY, new Square(File.EMPTY, Rank.EMPTY));
    }

    public static Empty of() {
        return empty;
    }

    @Override
    public PieceType pieceType() {
        return PieceType.EMPTY;
    }

    @Override
    public boolean isSameType(final PieceType pieceType) {
        return PieceType.EMPTY.equals(pieceType);
    }
}
