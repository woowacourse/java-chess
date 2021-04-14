package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.moveStrategy.SpecifiedLocationStrategy;

import java.util.List;

public class Knight extends Piece {
    private static final String KNIGHT_NAME = "N";
    private static final double SCORE = 2.5;

    public Knight(Team team) {
        super(KNIGHT_NAME, team, SCORE, new SpecifiedLocationStrategy(), false);
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        return movablePositions(target, Direction.knight());
    }
}
