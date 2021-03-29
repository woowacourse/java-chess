package domain.piece;

import domain.board.Score;
import domain.position.Direction;
import domain.position.Position;
import java.util.Map;

public class Knight extends Piece {

    private static final String NAME = "n";
    private static final Score SCORE = new Score(2.5);

    public Knight(Color color) {
        super(NAME, color, SCORE);
    }

    @Override
    public boolean canMove(Map<Position, Piece> board, Position source, Position target) {
        return Direction.knightDirection()
            .stream()
            .anyMatch(direction -> source.sum(direction).equals(target));
    }

}
