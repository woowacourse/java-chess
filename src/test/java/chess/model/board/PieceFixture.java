package chess.model.board;

import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.piece.PieceType;

public class PieceFixture {
    public static final Piece WHITE_BISHOP = new Piece(Color.WHITE, PieceType.BISHOP);
    public static final Piece BLACK_KNIGHT = new Piece(Color.BLACK, PieceType.KNIGHT);
    public static final Piece WHITE_PAWN = new Piece(Color.WHITE, PieceType.PAWN);


    public static final Position A1 = new Position(File.A, Rank.FIRST);
    public static final Position B2 = new Position(File.B, Rank.SECOND);
}
