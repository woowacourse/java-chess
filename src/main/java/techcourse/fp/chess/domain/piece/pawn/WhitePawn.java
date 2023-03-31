package techcourse.fp.chess.domain.piece.pawn;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.Directions;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.PieceType;

public class WhitePawn extends Pawn {

    private static final int INITIAL_WHITE_RANK = 2;

    private WhitePawn(final Color color, final PieceType pieceType,
                      final Directions attackDirections,
                      final Direction moveDirection) {
        super(color, pieceType, attackDirections, moveDirection);
    }

    public static WhitePawn create() {
        return new WhitePawn(Color.WHITE, PieceType.PAWN,
                new Directions(Direction.ofWhitePawnAttack()),
                Direction.ofWhitePawnMove());
    }

    @Override
    protected boolean isStartPosition(final Position source) {
        return source.getRankOrder() == INITIAL_WHITE_RANK;
    }
}
