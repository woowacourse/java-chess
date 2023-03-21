package chess.model.piece;

import chess.model.piece.sliding.Bishop;
import chess.model.piece.nonsliding.King;
import chess.model.piece.nonsliding.Knight;
import chess.model.piece.sliding.Queen;
import chess.model.piece.sliding.Rook;

public class PieceFixture {

    public static final Piece WHITE_BISHOP = new Bishop(PieceColor.WHITE);
    public static final Piece BLACK_KNIGHT = new Knight(PieceColor.BLACK);
    public static final Piece WHITE_KING = new King(PieceColor.WHITE);
    public static final Piece BLACK_QUEEN = new Queen(PieceColor.BLACK);
    public static final Piece BLACK_ROOK = new Rook(PieceColor.BLACK);
}
