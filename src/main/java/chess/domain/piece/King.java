package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.moveStrategy.SpecifiedLocationStrategy;

import java.util.List;

public class King extends Piece {
    private static final String KING_NAME = "K";
    private static final double SCORE = 0;

    public King(Team team) {
        super(KING_NAME, team, SCORE, new SpecifiedLocationStrategy()
                , false, true);
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        return movablePositions(target, Direction.all());
    }
}
