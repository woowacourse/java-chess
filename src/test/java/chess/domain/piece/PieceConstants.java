package chess.domain.piece;


import static chess.domain.team.Team.BLACK;
import static chess.domain.team.Team.NONE;
import static chess.domain.team.Team.WHITE;

public class PieceConstants {

    public static final Empty EMPTY = new Empty(NONE);
    public static final Pawn BLACK_PAWN = new Pawn(BLACK);
    public static final Pawn WHITE_PAWN = new Pawn(WHITE);
}
