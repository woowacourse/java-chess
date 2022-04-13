package chess.domain.piece;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.DirectionDecider;
import java.util.Arrays;
import java.util.List;

import chess.domain.direction.Direction;
import chess.domain.position.Position;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class King extends Piece {

    private static final String INVALID_DISTANCE_KING = "King이 갈 수 없는 거리입니다.";

    private static final int KING_MAX_DISTANCE = 1;
    private static final double KING_SCORE = 0.0;

    private static final King whiteKing = new King(Color.WHITE);
    private static final King blackKing = new King(Color.BLACK);

    private static final List<Direction> DIRECTIONS = Stream.concat(Arrays.stream(DiagonalDirection.values()),
            Arrays.stream(BasicDirection.values())).collect(Collectors.toList());

    private King(Color color) {
        super(color);
    }

    public static King createWhite() {
        return whiteKing;
    }

    public static King createBlack() {
        return blackKing;
    }

    @Override
    protected Direction matchDirection(Position from, Position to) {
        Direction direction = DirectionDecider.generateUnitPosition(DIRECTIONS, from, to);
        if (from.canReachUnderThreshold(to, KING_MAX_DISTANCE)) {
            return direction;
        }
        throw new IllegalArgumentException(INVALID_DISTANCE_KING);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public String getImage() {
        if (this.color == Color.WHITE) {
            return "white_king.png";
        }
        return "black_king.png";
    }

    @Override
    public double getScore() {
        return KING_SCORE;
    }

    @Override
    public String getName() {
        return "King";
    }
}
