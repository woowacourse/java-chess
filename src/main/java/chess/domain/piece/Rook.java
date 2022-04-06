package chess.domain.piece;

import static chess.domain.position.Direction.BOTTOM;
import static chess.domain.position.Direction.LEFT;
import static chess.domain.position.Direction.RIGHT;
import static chess.domain.position.Direction.TOP;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.piece.strategy.LinearMovingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Rook extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(RIGHT, LEFT, TOP, BOTTOM);

    private static final MovingStrategy MOVING_STRATEGY = new LinearMovingStrategy(DIRECTIONS);
    private static final String NOTATION = "R";
    private static final double SCORE = 5;

    public Rook(Color color) {
        super(color);
    }

    @Override
    public void validateMove(Board board, Position source, Position target) {
        if (MOVING_STRATEGY.canMove(board, source, target)) {
            return;
        }

        throw new IllegalArgumentException("기물을 이동할 수 없습니다.");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public String getNotation() {
        return color.parse(NOTATION);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
