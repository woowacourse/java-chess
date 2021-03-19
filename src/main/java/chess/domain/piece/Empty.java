package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.piece.strategy.PawnStrategy;

public class Empty extends Piece {

    public Empty() {
        super(PieceType.EMPTY, PieceColor.NOTHING, new PawnStrategy());
    }
}
