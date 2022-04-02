package chess.domain.piece;

import chess.domain.board.Position;

public class Fixture {
    public static final Position E4 = Position.from("E4");
    public static final Position E6 = Position.from("E6");
    public static final Position E8 = Position.from("E8");
    public static final Position E7 = Position.from("E7");
    public static final Bishop BISHOP_WHITE = new Bishop(Color.WHITE);
    public static final Rook ROOK_WHITE = new Rook(Color.WHITE);
    public static final Queen QUEEN_WHITE = new Queen(Color.WHITE);
    public static final King KING_WHITE = new King(Color.WHITE);
    public static final Knight KNIGHT_WHITE = new Knight(Color.WHITE);
    public static final Pawn PAWN_WHITE = new Pawn(Color.WHITE);
    public static final Pawn PAWN_BLACK = new Pawn(Color.BLACK);
}
