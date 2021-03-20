package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color, false);
        this.type = Type.PAWN;
    }

    @Override
    public List<Direction> direction() {
        if (isBlack()) {
            return Direction.blackPawnDirection();
        }
        return Direction.whitePawnDirection();
    }
}
