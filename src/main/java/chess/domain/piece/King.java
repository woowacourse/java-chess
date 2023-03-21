package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class King extends Piece {
    private static final List<Move> possibleMoves = makePossibleMove();

    public King(final Camp camp) {
        super(camp, Role.KING);
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
    public boolean isMovable(final Square source, final Square target, final Move move) {
        return possibleMoves.contains(move) && isNotSlidingMove(source, target, move);
    }

    private boolean isNotSlidingMove(final Square source, final Square target, final Move move) {
        return move.getFile() == target.getFile() - source.getFile()
                && move.getRank() == target.getRank() - source.getRank();
    }
}
