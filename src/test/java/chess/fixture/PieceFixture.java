package chess.fixture;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

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
