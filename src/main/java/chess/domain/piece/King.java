package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class King extends Piece {
    private static final List<Move> possibleMoves = makePossibleMove();

    public King(final Camp camp, final Square position) {
        super(camp, position);
    }

    private static List<Move> makePossibleMove() {
        return List.of(
                Move.UP,
                Move.DOWN,
                Move.LEFT,
                Move.RIGHT,
                Move.UP_LEFT,
                Move.UP_RIGHT,
                Move.DOWN_LEFT,
                Move.DOWN_RIGHT
        );
    }

    @Override
    public boolean isMovable(final Piece targetPiece,
                             final boolean isPathBlocked) {
        return possibleMoves.contains(calculateMove(targetPiece)) && isNotSlidingMove(targetPiece);
    }

    private boolean isNotSlidingMove(final Piece target) {
        final Move move = calculateMove(target);
        return position().getFile() == target.position().getFile() - move.getFile()
                && position().getRank() == target.position().getRank() - move.getRank();
    }

    @Override
    public Piece move(final Square target) {
        return new King(camp(), target);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KING;
    }

    @Override
    public boolean isSameType(final PieceType pieceType) {
        return PieceType.KING.equals(pieceType);
    }
}
