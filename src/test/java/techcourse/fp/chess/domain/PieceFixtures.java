package techcourse.fp.chess.domain;

import techcourse.fp.chess.domain.piece.BlackPawn;
import techcourse.fp.chess.domain.piece.UnMovablePiece;
import techcourse.fp.chess.domain.piece.WhitePawn;

public class PieceFixtures {

    public static final BlackPawn BLACK_PAWN = BlackPawn.create();
    public static final WhitePawn WHITE_PAWN = WhitePawn.create();
    public static final UnMovablePiece EMPTY = UnMovablePiece.create();

}
