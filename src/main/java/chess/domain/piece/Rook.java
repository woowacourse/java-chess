package chess.domain.piece;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import java.util.List;

public final class Rook extends AbstractPiece {
    private static final String NO_MOVE_MESSAGE = "룩이 이동할 수 없는 위치입니다.";
    private static final double SCORE = 5;

    public Rook(Team team) {
        super(new Name("R"), team);
    }

    public List<Direction> rookDirection() {
        return Direction.getAbsoluteDirections(team, List.of(
                Direction.TOP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        List<Direction> directions = rookDirection();

        if (Direction.isInvalidDirection(from, to, directions)) {
            throw new IllegalArgumentException(NO_MOVE_MESSAGE);
        }

        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
