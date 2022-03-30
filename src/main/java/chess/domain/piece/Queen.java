package chess.domain.piece;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import java.util.List;

public final class Queen extends AbstractPiece {
    private static final String NO_MOVE_MESSAGE = "퀸이 이동할 수 없는 위치입니다.";
    private static final double SCORE = 9;

    public Queen(Team team) {
        super(new Name("Q"), team);
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        List<Direction> directions = Direction.royalDirection(team);

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
