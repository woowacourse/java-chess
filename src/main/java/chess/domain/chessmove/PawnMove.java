package chess.domain.chessmove;

import chess.domain.Position;
import chess.domain.chesspiece.Team;

import java.util.ArrayList;
import java.util.List;

public class PawnMove implements Move {
    private static final int MIN_DISTANCE = 1;
    private static final int MAX_DISTANCE = 2;
    private static final int UNMOVED_STATE = 2;
    private static final int MOVED_STATE = 1;

    private int direction;
    private int moveFlag = UNMOVED_STATE;

    private PawnMove(Team team) {
        this.direction = team.getDirection();
    }

    private static class PawnMoveLazyHolder {
        private static final PawnMove BLACK_PAWN_MOVE_INSTANCE = new PawnMove(Team.BLACK);
        private static final PawnMove WHITE_PAWN_MOVE_INSTANCE = new PawnMove(Team.WHITE);
    }

    public static PawnMove getInstance(Team team) {
        if (Team.BLACK.equals(team)) {
            return PawnMoveLazyHolder.BLACK_PAWN_MOVE_INSTANCE;
        }
        return PawnMoveLazyHolder.WHITE_PAWN_MOVE_INSTANCE;
    }

    @Override
    public List<Position> move(Position source, Position target) {
        if (!isInRoute(source, target)) {
            throw new IllegalArgumentException();
        }

        List<Position> positions = new ArrayList<>();

        addRoute(source, target, positions);

        if (moveFlag == UNMOVED_STATE) changeFlagStatus();

        return positions;
    }

    @Override
    public boolean isInRoute(Position source, Position target) {
        int rowDistance = target.calculateRowDistance(source);

        return !source.equals(target)
                && isInFront(source, target, rowDistance) || isInRange(rowDistance);
    }

    private void addRoute(Position source, Position target, List<Position> positions) {
        int distance = Math.abs(target.calculateRowDistance(source));

        if (distance == MAX_DISTANCE) {
            positions.add(Position.of(source.getY() + direction, source.getX()));
        }

        positions.add(target);
    }

    private boolean isInRange(int rowDistance) {
        return rowDistance * direction >= MIN_DISTANCE && rowDistance * direction <= moveFlag;
    }

    private boolean isInFront(Position source, Position target, int rowDistance) {
        return rowDistance == direction && Math.abs(source.calculateColumnDistance(target)) == MIN_DISTANCE;
    }

    private void changeFlagStatus() {
        moveFlag = MOVED_STATE;
    }
}