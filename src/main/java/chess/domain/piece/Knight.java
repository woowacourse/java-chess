package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.piece.strategy.LengthBasedMovingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Position;

public class Knight extends Piece {

    private static final MovingStrategy MOVING_STRATEGY = new LengthBasedMovingStrategy(number -> number == 5);
    private static final String NOTATION = "N";
    private static final double SCORE = 2.5;

    public Knight(Color color) {
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
