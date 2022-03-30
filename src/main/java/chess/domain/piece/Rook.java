package chess.domain.piece;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.DirectionDecider;
import java.util.List;

import chess.domain.position.Position;
import chess.domain.direction.Direction;

public class Rook extends Piece {

    private static final String INVALID_DIRECTION_ROOK = "Rook이 갈 수 없는 방향입니다.";
    private static final double ROOK_SCORE = 5.0;

    private static final Rook whiteRook = new Rook(Color.WHITE);
    private static final Rook blackRook = new Rook(Color.BLACK);

    private static final List<Direction> DIRECTIONS = List.of(BasicDirection.values());

    private Rook(Color color) {
        super(color);
    }

    public static Rook createWhite() {
        return whiteRook;
    }

    public static Rook createBlack() {
        return blackRook;
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
        return ROOK_SCORE;
    }
}
