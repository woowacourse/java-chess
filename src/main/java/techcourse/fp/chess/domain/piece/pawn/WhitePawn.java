package techcourse.fp.chess.domain.piece.pawn;

import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.movingStrategy.PawnStrategy;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.PieceType;

public class WhitePawn extends Pawn {

    private static final int INITIAL_WHITE_RANK = 2;

    public WhitePawn(final Color color, final PieceType pieceType,
                     final PawnStrategy pawnStrategy) {
        super(color, pieceType, pawnStrategy);
    }

    public static WhitePawn create() {
        return new WhitePawn(Color.WHITE, PieceType.PAWN, PawnStrategy.ofWhite());
    }

    @Override
    protected boolean isStartPosition(final Position source) {
        return source.getRankOrder() == INITIAL_WHITE_RANK;
    }
}
