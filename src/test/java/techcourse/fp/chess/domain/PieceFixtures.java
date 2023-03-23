package techcourse.fp.chess.domain;

import techcourse.fp.chess.domain.piece.Empty;
import techcourse.fp.chess.domain.piece.pawn.BlackPawn;
import techcourse.fp.chess.domain.piece.pawn.WhitePawn;

public class PieceFixtures {

    public static final BlackPawn BLACK_PAWN = BlackPawn.create();
    public static final WhitePawn WHITE_PAWN = WhitePawn.create();
    public static final Empty EMPTY = Empty.create();

}
