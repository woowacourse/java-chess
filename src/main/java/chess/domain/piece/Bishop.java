package chess.domain.piece;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import java.util.List;

public final class Bishop extends AbstractPiece {
    private static final String NO_MOVE_MESSAGE = "비숍이 이동할 수 없는 위치입니다.";
    private static final int SCORE = 3;

    public Bishop(Team team) {
        super(new Name("B"), team);
    }

    public List<Direction> bishopDirection() {
        return List.of(Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        List<Direction> directions = bishopDirection();

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
