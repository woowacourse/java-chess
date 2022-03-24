package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class RookMoveStrategy extends FirstRowMoveStrategy {

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = Distance.of(source, target);
        final Piece targetPiece = board.getPiece(target);
        final Color color = board.getPiece(source).getColor();

        return false;
    }
}
