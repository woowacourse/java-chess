package techcourse.fp.chess.domain.piece.pawn;

import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.movingStrategy.PawnStrategy;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.PieceType;

public class BlackPawn extends Pawn {

    private static final int INITIAL_BLACK_RANK = 7;

    private BlackPawn(final Color color, final PieceType pieceType,
                      final PawnStrategy pawnStrategy) {
        super(color, pieceType, pawnStrategy);
    }

    public static BlackPawn create() {
        return new BlackPawn(Color.BLACK, PieceType.PAWN, PawnStrategy.ofBlack());
    }

    @Override
    protected boolean isStartPosition(final Position source) {
        return source.getRankOrder() == INITIAL_BLACK_RANK;
    }
}
