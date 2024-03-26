package chess.domain.fixture;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.piece.DummyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.directionmove.Bishop;
import chess.domain.piece.directionmove.Queen;
import chess.domain.piece.directionmove.Rook;
import chess.domain.piece.fixedmove.King;
import chess.domain.piece.fixedmove.Knight;
import chess.domain.piece.pawn.InitialBlackPawn;
import chess.domain.piece.pawn.InitialWhitePawn;
import chess.domain.piece.pawn.NormalBlackPawn;
import chess.domain.piece.pawn.NormalWhitePawn;

public class PieceFixture {
    public static final Piece WHITE_KING = new King(WHITE);
    public static final Piece WHITE_QUEEN = new Queen(WHITE);
    public static final Piece WHITE_BISHOP = new Bishop(WHITE);
    public static final Piece WHITE_ROOK = new Rook(WHITE);
    public static final Piece WHITE_KNIGHT = new Knight(WHITE);
    public static final Piece NORMAL_WHITE_PAWN = new NormalWhitePawn();
    public static final Piece INITIAL_WHITE_PAWN = new InitialWhitePawn();

    public static final Piece BLACK_KING = new King(BLACK);
    public static final Piece BLACK_QUEEN = new Queen(BLACK);
    public static final Piece BLACK_BISHOP = new Bishop(BLACK);
    public static final Piece BLACK_ROOK = new Rook(BLACK);
    public static final Piece BLACK_KNIGHT = new Knight(BLACK);
    public static final Piece NORMAL_BLACK_PAWN = new NormalBlackPawn();
    public static final Piece INITIAL_BLACK_PAWN = new InitialBlackPawn();

    public static final Piece EMPTY_PIECE = DummyPiece.getInstance();
}
