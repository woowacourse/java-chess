package chess.domain.pieces.Movable;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;

public interface SingleMove {
    default boolean isMoveAbleDirection(final Board board, final int nextRow, final int nextCol, final Team team) {
        if (!board.validateRange(nextRow, nextCol)) {
            return false;
        }
        return !board.piecesByTeam(team).containByPosition(new Position(nextRow, nextCol));
    }
}
