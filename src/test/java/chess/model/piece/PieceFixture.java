package chess.model.piece;

import chess.model.piece.type.Bishop;
import chess.model.piece.type.King;
import chess.model.piece.type.Knight;
import chess.model.piece.type.Piece;
import chess.model.piece.type.Queen;
import chess.model.piece.type.Rook;

public class PieceFixture {

    public static final Piece WHITE_BISHOP = new Bishop(PieceColor.WHITE);
    public static final Piece BLACK_KNIGHT = new Knight(PieceColor.BLACK);
    public static final Piece WHITE_KING = new King(PieceColor.WHITE);
    public static final Piece BLACK_QUEEN = new Queen(PieceColor.BLACK);
    public static final Piece BLACK_ROOK = new Rook(PieceColor.BLACK);
}
