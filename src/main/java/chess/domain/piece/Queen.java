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
    public boolean isQueen() {
        return true;
    }

    @Override
    public String getImage() {
        if (this.color == Color.WHITE) {
            return "white_queen.png";
        }
        return "black_queen.png";
    }

    @Override
    public double getScore() {
        return 9.0;
    }

    @Override
    public String getName() {
        return "Queen";
    }
}
