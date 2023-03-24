package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Rook extends Piece {
    private static final List<Move> possibleMoves = makePossibleMove();

    public Rook(final Camp camp, final Square position) {
        super(camp, position);
    }

    private static List<Move> makePossibleMove() {
        return List.of(
                Move.UP,
                Move.DOWN,
                Move.LEFT,
                Move.RIGHT
        );
    }

    @Override
    public boolean isMovable(final Square target, final Move move, final boolean isPathBlocked) {
        return possibleMoves.contains(move) && !isPathBlocked;
    }

    @Override
    public PieceType pieceType() {
        return PieceType.ROOK;
    }

    @Override
    public boolean isSameType(final PieceType pieceType) {
        return PieceType.ROOK.equals(pieceType);
    }
}
