package techcourse.fp.chess.domain;

import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Empty;
import techcourse.fp.chess.domain.piece.ordinary.Queen;
import techcourse.fp.chess.domain.piece.pawn.BlackPawn;
import techcourse.fp.chess.domain.piece.pawn.WhitePawn;

public class PieceFixtures {

    public static final WhitePawn WHITE_PAWN = WhitePawn.create();
    public static final BlackPawn BLACK_PAWN = BlackPawn.create();

    public static final Queen WHITE_QUEEN = Queen.create(Color.WHITE);
    public static final Queen BLACK_QUEEN = Queen.create(Color.BLACK);

    public static final Empty EMPTY = Empty.create();
}
