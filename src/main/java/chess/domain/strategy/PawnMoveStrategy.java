package chess.domain.strategy;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import chess.domain.piece.pawn.Pawn;
import java.util.Map;
import java.util.Set;

public class PawnMoveStrategy extends MoveStrategy {

    public PawnMoveStrategy(Map<Position, Piece> board) {
        super(board);
    }

    @Override
    public void move(Color turnColor, Position from, Position to) {
        Pawn currentPiece = (Pawn) board.get(from);
        checkTurnOf(currentPiece, turnColor);
        Piece destinationPiece = board.get(to);
        validateMovable(to, currentPiece);
        validateWithCapture(to, currentPiece, destinationPiece);
        updateBoard(from, to, currentPiece);
    }

    private void validateWithCapture(Position to, Pawn currentPiece, Piece destinationPiece) {
        if (!currentPiece.isCaptureMove(to) && destinationPiece.pieceType() != PieceType.BLANK) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
        if (currentPiece.isCaptureMove(to) && !currentPiece.isOppositeColor(destinationPiece)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
    }

    private void validateMovable(Position to, Pawn currentPiece) {
        Set<Position> movablePositions = currentPiece.findPathTo(to);
        if (!isAllBlankCourses(movablePositions)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
    }
}
