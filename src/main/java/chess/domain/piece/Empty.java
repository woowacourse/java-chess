package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.game.Camp;

public class Empty extends Piece {
    private static final Empty empty = new Empty();

    private Empty() {
        super(Camp.EMPTY, new Square(File.EMPTY, Rank.EMPTY));
    }

    public Empty(final Square square) {
        super(Camp.EMPTY, new Square(square.file(), square.rank()));
    }

    @Override
    public Piece move(final Square target) {
        throw new IllegalArgumentException("빈 Square는 움직일 수 없습니다.");
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
