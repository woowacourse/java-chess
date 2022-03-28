package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public abstract class MoveStrategy {

    public abstract boolean isMovable(final Board board, final Position source, final Position target);

    protected abstract boolean isMovableToTarget(final Piece targetPiece, final Color color);
}
