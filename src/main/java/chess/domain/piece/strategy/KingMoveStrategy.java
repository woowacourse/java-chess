package chess.domain.piece.strategy;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import java.util.List;

public final class KingMoveStrategy extends MoveStrategy {

    private static final String NO_MOVE_MESSAGE = "킹이 이동할 수 없는 위치입니다.";

    @Override
    public boolean isValidateCanMove(Team team, Piece targetPiece, Position from, Position to) {
        List<Direction> directions = Direction.royalDirection(team);

        if (isInvalidDistance(from, to, directions)) {
            throw new IllegalArgumentException(NO_MOVE_MESSAGE);
        }

        return true;
    }
}
