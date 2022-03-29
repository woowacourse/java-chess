package chess.domain.piece.strategy;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import java.util.List;

public final class QueenMoveStrategy extends MoveStrategy {

    private static final String NO_MOVE_MESSAGE = "퀸이 이동할 수 없는 위치입니다.";

    @Override
    public boolean canMove(Team team, Piece targetPiece, Position from, Position to) {
        List<Direction> directions = Direction.royalDirection(team);

        if (isInvalidDirection(from, to, directions)) {
            throw new IllegalArgumentException(NO_MOVE_MESSAGE);
        }

        return true;
    }
}
