package chess.domain.piece;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.DirectionDecider;
import java.util.Arrays;
import java.util.List;

import chess.domain.position.Position;
import chess.domain.direction.Direction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Queen extends Piece {

    private static final String INVALID_DIRECTION_QUEEN = "Queen이 갈 수 없는 방향입니다.";
    private static final double QUEEN_SCORE = 9.0;

    private static final Queen whiteQueen = new Queen(Color.WHITE);
    private static final Queen blackQueen = new Queen(Color.BLACK);

    private static final List<Direction> DIRECTIONS = Stream.concat(Arrays.stream(DiagonalDirection.values()),
            Arrays.stream(BasicDirection.values())).collect(Collectors.toList());

    private Queen(Color color) {
        super(color);
    }

    public static Queen createWhite() {
        return whiteQueen;
    }

    public static Queen createBlack() {
        return blackQueen;
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
        return QUEEN_SCORE;
    }
}
