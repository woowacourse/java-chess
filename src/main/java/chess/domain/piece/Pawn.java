package chess.domain.piece;

import static chess.domain.board.Direction.*;

import chess.domain.board.LocationDiff;
import chess.domain.board.Direction;
import java.util.List;

public class Pawn extends Piece {
    private static final List<Direction> BLACK_PAWN_DIRECTIONS = List.of(D, DR, DL);
    private static final List<Direction> WHITE_PAWN_DIRECTIONS = List.of(U, UR, UL);
    private static final double SCORE = 1;

    public Pawn(Team team) {
        super(team, Name.PAWN);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        if (isBlack()) {
            return BLACK_PAWN_DIRECTIONS.contains(direction);
        }
        return WHITE_PAWN_DIRECTIONS.contains(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        Direction direction = locationDiff.computeDirection();
        int distance = locationDiff.computeDistance();

        if ((direction == D || direction == Direction.U) && isFirst()) {
            return distance <= 2;
        }
        return distance <= 1;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
