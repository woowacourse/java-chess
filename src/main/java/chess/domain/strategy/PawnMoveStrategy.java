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
    public void move(Color turnColor, Position source, Position destination) {
        Pawn currentPiece = (Pawn) board.get(source);
        checkTurnOf(currentPiece, turnColor);
        Piece destinationPiece = board.get(destination);
        validateMovable(destination, currentPiece);
        validateWithCapture(destination, currentPiece, destinationPiece);
        updateBoard(source, destination, currentPiece);
    }

    private void validateWithCapture(Position destination, Pawn currentPiece, Piece destinationPiece) {
        if (!currentPiece.isCaptureMove(destination) && destinationPiece.pieceType() != PieceType.BLANK) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
        if (currentPiece.isCaptureMove(destination) && !currentPiece.isOppositeColor(destinationPiece)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
    }

    private void validateMovable(Position destination, Pawn currentPiece) {
        Set<Position> movablePositions = currentPiece.findPathTo(destination);
        if (!isAllBlankCourses(movablePositions)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
    }
}
