package chess.piece.pawn;

import static chess.PositionFixtures.A2;
import static chess.PositionFixtures.A3;
import static chess.PositionFixtures.A6;
import static chess.PositionFixtures.A7;

import chess.piece.Piece;

public final class PawnFixtures {

    public static final Piece PAWN_WHITE_A2 = new FirstWhitePawn(A2);
    public static final Piece PAWN_WHITE_A3 = new WhitePawn(A3);
    public static final Piece PAWN_BLACK_A6 = new BlackPawn(A6);
    public static final Piece PAWN_BLACK_A7 = new FirstBlackPawn(A7);

    private PawnFixtures() {
    }
}
