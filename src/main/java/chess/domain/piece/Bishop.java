package chess.domain.piece;

import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.DirectionDecider;
import java.util.Arrays;
import java.util.List;

import chess.domain.position.Position;
import chess.domain.direction.Direction;

public class Bishop extends Piece {

    private static final List<Direction> DIRECTIONS = Arrays.asList(DiagonalDirection.values());

    private static final Bishop whiteBishop = new Bishop(Color.WHITE);
    private static final Bishop blackBishop = new Bishop(Color.BLACK);
    private static final double BISHOP_SCORE = 3.0;

    private Bishop(Color color) {
        super(color);
    }

    public static Bishop createWhite() {
        return whiteBishop;
    }

    public static Bishop createBlack() {
        return blackBishop;
    }

    @Override
    public Direction matchDirection(Position from, Position to) {
        return DirectionDecider.generateUnitPosition(DIRECTIONS, from, to);
    }

    @Override
    public boolean isBishop() {
        return true;
    }

    @Override
    public String getImage() {
        if (this.color == Color.WHITE) {
            return "white_bishop.png";
        }
        return "black_bishop.png";
    }

    @Override
    public double getScore() {
        return BISHOP_SCORE;
    }

    @Override
    public String getName() {
        return "Bishop";
    }
}
