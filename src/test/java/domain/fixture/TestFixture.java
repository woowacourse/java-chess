package domain.fixture;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.Team;

public final class TestFixture {
    public static final King BLACK_KING = new King(Team.BLACK);
    public static final King WHITE_KING = new King(Team.WHITE);
    public static final Queen BLACK_QUEEN = new Queen(Team.BLACK);
    public static final Knight BLACK_KNIGHT = new Knight(Team.BLACK);
    public static final Pawn BLACK_PAWN = new Pawn(Team.BLACK);
    public static final Pawn WHITE_PAWN = new Pawn(Team.WHITE);
    public static final Bishop BLACK_BISHOP = new Bishop(Team.BLACK);
    public static final Rook BLACK_ROOK = new Rook(Team.BLACK);
}
