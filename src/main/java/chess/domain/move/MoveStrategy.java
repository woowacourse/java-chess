package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.domain.piece.Piece;

public abstract class MoveStrategy {

    protected static final int NOT_MOVE = 0;

    public abstract boolean isMovable(final Board board, final Position source, final Position target);

    protected abstract boolean isTargetPositionMovable(final Piece targetPiece, final Team team);
}
