package chess;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.strategy.piecemovestrategy.BlackPawnMove;
import chess.domain.strategy.piecemovestrategy.WhitePawnMove;

import static chess.PositionFixture.THREE_C;
import static chess.PositionFixture.TWO_B;

public class PieceFixture {
    public static final Piece WHITE_PAWN_TWO_B = new Pawn(Color.WHITE, TWO_B, new WhitePawnMove());
    public static final Piece BLACK_PAWN_THREE_C = new Pawn(Color.BLACK, THREE_C, new BlackPawnMove());
}
