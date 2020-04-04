package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.pathStrategy.WhitePawnPathStrategy;

public class WhitePawn extends Pawn {
    public WhitePawn(Position position) {
        super(PieceColor.WHITE, position, new WhitePawnPathStrategy());
    }
}
