package chess.domain.state;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Map;
import java.util.Set;

public class GeneralMoveState extends MoveState {

    public GeneralMoveState(final Map<Position, Piece> board) {
        super(board);
    }

    @Override
    public void move(final Color turnColor, final Position source, final Position destination) {
        final Piece currentPiece = board.get(source);
        checkTurnOf(currentPiece, turnColor);
        final Piece destinationPiece = board.get(destination);
        final Set<Position> path = currentPiece.findPathTo(destination);
        validatePath(turnColor, path, destinationPiece);
        updateBoard(source, destination, currentPiece);
    }

    private void validatePath(final Color turnColor, final Set<Position> path, final Piece destinationPiece) {
        if (destinationPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("이동할 수 없는 경로 입니다.");
        }
        if (isNotBlankPath(path)) {
            throw new IllegalArgumentException("이동할 수 없는 경로 입니다.");
        }
    }
}
