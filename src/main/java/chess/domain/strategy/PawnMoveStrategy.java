package chess.domain.strategy;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import chess.domain.piece.blank.Blank;
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
        validateMovable(from, to, currentPiece);
        validateWithCapture(from, to, currentPiece, destinationPiece);
        updateBoard(from, to, currentPiece);
    }

    private void validateWithCapture(Position from, Position to, Pawn currentPiece, Piece destinationPiece) {
        if (!currentPiece.isCaptureMove(from, to) && !destinationPiece.isBlank()) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
        if (currentPiece.isCaptureMove(from, to) && !currentPiece.isOppositeColor(destinationPiece)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
    }

    private void validateMovable(Position from, Position to, Pawn currentPiece) {
        Set<Position> pathToDestination = currentPiece.findPath(from, to);
        if (isNotAllBlankPath(pathToDestination)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
    }

    public void updateBoard(Position from, Position to, Pawn currentPiece) {
        board.replace(to, currentPiece.update());
        board.replace(from, new Blank());
    }
}
