package chess.model.board;

import chess.model.piece.Piece;
import chess.model.piece.PieceColor;
import chess.model.piece.PieceType;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;

public class PieceFixture {

    public static final Piece WHITE_BISHOP = new Piece(PieceColor.WHITE, PieceType.BISHOP);
    public static final Piece BLACK_KNIGHT = new Piece(PieceColor.BLACK, PieceType.KNIGHT);
    public static final Piece WHITE_PAWN = new Piece(PieceColor.WHITE, PieceType.PAWN);

    public static final Position A1 = new Position(File.A, Rank.FIRST);
    public static final Position B2 = new Position(File.B, Rank.SECOND);
}
