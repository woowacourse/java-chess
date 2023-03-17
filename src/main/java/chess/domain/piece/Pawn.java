package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.Direction;
import chess.domain.Path;
import chess.domain.Position;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final int WHITE_START_RANK = 2;
    private static final int BLACK_START_RANK = 7;

    private final List<Direction> directions;

    public Pawn(Camp camp) {
        super(camp, PieceType.PAWN);
        this.directions = getDirectionsByColor();
    }

    private List<Direction> getDirectionsByColor() {
        if (camp == Camp.BLACK) {
            return List.of(Direction.SOUTH, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
        }
        return List.of(Direction.NORTH, Direction.NORTH_EAST, Direction.NORTH_WEST);
    }

    @Override
    public CheckablePaths findCheckablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : directions) {
            paths.add(Path.ofSinglePath(current, direction));
        }

        if (isStartRank(current)) {
            paths.add(Path.ofPawnStartPath(current, getForwardDirectionByColor()));
        }
        return new CheckablePaths(paths);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    public boolean isAttack(Position source, Position dest) {
        return Direction.find(source, dest)
                .map(this::isRightAttackDirectionByCamp)
                .orElse(false);
    }

    private boolean isRightAttackDirectionByCamp(Direction direction) {
        if (camp == Camp.WHITE) {
            return direction == Direction.NORTH_EAST || direction == Direction.NORTH_WEST;
        }
        return direction == Direction.SOUTH_EAST || direction == Direction.SOUTH_WEST;
    }

    private boolean isStartRank(final Position current) {
        if (camp == Camp.WHITE) {
            return current.isInExpectedRank(WHITE_START_RANK);
        }
        return current.isInExpectedRank(BLACK_START_RANK);
    }

    private Direction getForwardDirectionByColor() {
        if (camp == Camp.WHITE) {
            return Direction.NORTH;
        }
        return Direction.SOUTH;
    }

}
