package chess.domain.piece;

import chess.domain.piece.*;

public class PieceFixture {

    private static final Team WHITE_TEAM = Team.WHITE;
    private static final Team BLACK_TEAM = Team.BLACK;

    public static final Pawn WHITE_PAWN = new Pawn(WHITE_TEAM, "p");
    public static final Rook WHITE_ROOK = new Rook(WHITE_TEAM, "r");
    public static final Knight WHITE_KNIGHT = new Knight(WHITE_TEAM, "n");
    public static final Bishop WHITE_BISHOP = new Bishop(WHITE_TEAM, "b");
    public static final Queen WHITE_QUEEN = new Queen(WHITE_TEAM, "q");
    public static final King WHITE_KING = new King(WHITE_TEAM, "k");

    public static final Pawn BLACK_PAWN = new Pawn(BLACK_TEAM, "P");
    public static final Rook BLACK_ROOK = new Rook(BLACK_TEAM, "R");
    public static final Knight BLACK_KNIGHT = new Knight(BLACK_TEAM, "N");
    public static final Bishop BLACK_BISHOP = new Bishop(BLACK_TEAM, "B");
    public static final Queen BLACK_QUEEN = new Queen(BLACK_TEAM, "Q");
    public static final King BLACK_KING = new King(BLACK_TEAM, "K");
}
