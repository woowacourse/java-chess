package chess.domain.piece;

import chess.domain.direction.DirectionDecider;
import chess.domain.direction.KnightDirection;
import java.util.List;

import chess.domain.position.Position;
import chess.domain.direction.Direction;

public class Knight extends Piece {

    private static final String INVALID_DIRECTION_KNIGHT = "Knight가 갈 수 없는 방향입니다.";
    private static final double KNIGHT_SCORE = 2.5;

    private static final Knight whiteKing = new Knight(Color.WHITE);
    private static final Knight blackKing = new Knight(Color.BLACK);

    private static final List<Direction> DIRECTIONS = List.of(KnightDirection.values());

    private Knight(Color color) {
        super(color);
    }

    public static Knight createWhite() {
        return whiteKing;
    }

    public static Knight createBlack() {
        return blackKing;
    }

    @Override
    public Direction matchDirection(Position from, Position to) {
        return DirectionDecider.generateUnitPosition(DIRECTIONS, from, to);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double getScore() {
        return KNIGHT_SCORE;
    }
}
