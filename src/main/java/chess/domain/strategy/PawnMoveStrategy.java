package chess.domain.strategy;

import chess.domain.Blank;
import chess.domain.Color;
import chess.domain.Pawn;
import chess.domain.Piece;
import chess.domain.PieceType;
import chess.domain.Position;
import java.util.Map;
import java.util.Set;

public class PawnMoveStrategy extends MoveStrategy {

    public PawnMoveStrategy(Map<Position, Piece> board) {
        super(board);
    }

    @Override
    public MoveStrategy changeStrategy(Position from) {
        return null;
    }

    @Override
    public void move(Color turnColor, Position from, Position to) {
        Pawn currentPiece = (Pawn) board.get(from);
        checkTurnOf(currentPiece, turnColor);
        Piece destinationPiece = board.get(to);
        validateMovable(to, currentPiece);
        if (!currentPiece.isCaptureMove(to) && destinationPiece.pieceType() != PieceType.NONE) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
        if (currentPiece.isCaptureMove(to) && !currentPiece.isOppositeColor(destinationPiece)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
        board.replace(to, currentPiece.update(to));
        board.replace(from, new Blank(from));
    }

    private void validateMovable(Position to, Pawn currentPiece) {
        Set<Position> movablePositions = currentPiece.findMovablePositions(to);
        if (!isAllBlankCourses(movablePositions)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
    }
}
