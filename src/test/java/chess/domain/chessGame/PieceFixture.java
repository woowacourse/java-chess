package chess.domain.chessGame;

import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;

public class PieceFixture {
    static final Piece blackKing = new King(Color.BLACK);
    static final Piece blackQueen = new Queen(Color.BLACK);
    static final Piece blackRook = new Rook(Color.BLACK);
    static final Piece blackKnight = new Knight(Color.BLACK);
    static final Piece blackBishop = new Bishop(Color.BLACK);
    static final Piece blackPawn = new BlackPawn();
    static final Piece whiteKing = new King(Color.WHITE);
    static final Piece whiteQueen = new Queen(Color.WHITE);
    static final Piece whiteRook = new Rook(Color.WHITE);
    static final Piece whiteKnight = new Knight(Color.WHITE);
    static final Piece whiteBishop = new Bishop(Color.WHITE);
    static final Piece whitePawn = new WhitePawn();
    static final Piece emptyPiece = new EmptyPiece();
}
