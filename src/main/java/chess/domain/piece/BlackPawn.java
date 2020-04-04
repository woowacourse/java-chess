package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.pathStrategy.BlackPawnPathStrategy;

public class BlackPawn extends Pawn {
    public BlackPawn(Position position) {
        super(PieceColor.BLACK, position, new BlackPawnPathStrategy());
    }
}
