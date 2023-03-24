package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Bishop extends Piece {
    private static final List<Move> possibleMoves = makePossibleMove();

    public Bishop(final Camp camp, final Square position) {
        super(camp, position);
    }

    private static List<Move> makePossibleMove() {
        return List.of(
                Move.UP_LEFT,
                Move.UP_RIGHT,
                Move.DOWN_LEFT,
                Move.DOWN_RIGHT
        );
    }

    @Override
    public boolean isMovable(final Piece targetPiece,
                             final boolean isPathBlocked) {
        return possibleMoves.contains(calculateMove(targetPiece)) && !isPathBlocked;
    }

    @Override
    public Piece move(final Square target) {
        return new Bishop(camp(), target);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.BISHOP;
    }

    @Override
    public boolean isSameType(final PieceType pieceType) {
        return PieceType.BISHOP.equals(pieceType);
    }
}
