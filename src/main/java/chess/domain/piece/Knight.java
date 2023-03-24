package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Knight extends Piece {
    private static final List<Move> possibleMoves = makePossibleMove();

    public Knight(final Camp camp, final Square position) {
        super(camp, position);
    }

    private static List<Move> makePossibleMove() {
        return List.of(
                Move.UP_UP_LEFT,
                Move.UP_UP_RIGHT,
                Move.DOWN_DOWN_LEFT,
                Move.DOWN_DOWN_RIGHT,
                Move.LEFT_LEFT_UP,
                Move.LEFT_LEFT_DOWN,
                Move.RIGHT_RIGHT_UP,
                Move.RIGHT_RIGHT_DOWN
        );
    }

    @Override
    public boolean isMovable(final Square target, final Move move, final boolean isPathBlocked) {
        return possibleMoves.contains(move);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KNIGHT;
    }

    @Override
    public boolean isSameType(final PieceType pieceType) {
        return PieceType.KNIGHT.equals(pieceType);
    }
}
