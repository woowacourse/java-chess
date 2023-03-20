package chess.fixture;

import chess.piece.Bishop;
import chess.piece.EmptyPiece;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;

public class PieceFixture {

    public static final EmptyPiece EMPTY_PIECE = new EmptyPiece();

    public static final Queen QUEEN_WHITE = new Queen(Team.WHITE);
    public static final King KING_WHITE = new King(Team.WHITE);
    public static final Bishop BISHOP_WHITE = new Bishop(Team.WHITE);
    public static final Rook ROOK_WHITE = new Rook(Team.WHITE);
    public static final Knight KNIGHT_WHITE = new Knight(Team.WHITE);
    public static final Pawn PAWN_WHITE = new Pawn(Team.WHITE);

    public static final Queen QUEEN_BLACK = new Queen(Team.BLACK);
    public static final King KING_BLACK = new King(Team.BLACK);
    public static final Bishop BISHOP_BLACK = new Bishop(Team.BLACK);
    public static final Rook ROOK_BLACK = new Rook(Team.BLACK);
    public static final Knight KNIGHT_BLACK = new Knight(Team.BLACK);
    public static final Pawn PAWN_BLACK = new Pawn(Team.BLACK);

}
