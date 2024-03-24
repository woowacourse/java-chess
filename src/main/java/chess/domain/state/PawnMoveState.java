package chess.domain.state;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import chess.domain.piece.pawn.Pawn;
import java.util.Map;
import java.util.Set;

public class PawnMoveState extends MoveState {

    public PawnMoveState(final Map<Position, Piece> board) {
        super(board);
    }

    @Override
    public void move(final Color turnColor, final Position source, final Position destination) {
        final Pawn currentPiece = (Pawn) board.get(source);
        checkTurnOf(currentPiece, turnColor);
        final Piece destinationPiece = board.get(destination);
        validatePath(destination, currentPiece);
        validateWithCapture(destination, currentPiece, destinationPiece);
        updateBoard(source, destination, currentPiece);
    }

    private void validateWithCapture(final Position destination, final Pawn currentPiece,
                                     final Piece destinationPiece) {
        if (!currentPiece.isCaptureMove(destination) && destinationPiece.pieceType() != PieceType.BLANK) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
        if (currentPiece.isCaptureMove(destination) && !currentPiece.isOppositeColor(destinationPiece)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
    }

    private void validatePath(final Position destination, final Pawn currentPiece) {
        final Set<Position> path = currentPiece.findPathTo(destination);
        if (isNotBlankPath(path)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
    }
}
