package chess.domain.strategy;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Map;
import java.util.Set;

public class GeneralMoveStrategy extends MoveStrategy {

    public GeneralMoveStrategy(Map<Position, Piece> board) {
        super(board);
    }

    @Override
    public void move(Color turnColor, Position source, Position destination) {
        Piece currentPiece = board.get(source);
        checkTurnOf(currentPiece, turnColor);
        Piece destinationPiece = board.get(destination);
        Set<Position> path = currentPiece.findPathTo(destination);
        validatePath(turnColor, path, destinationPiece);
        updateBoard(source, destination, currentPiece);
    }

    private void validatePath(Color turnColor, Set<Position> path, Piece destinationPiece) {
        if (destinationPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("이동할 수 없는 경로 입니다.");
        }
        if (!isAllBlankCourses(path)) {
            throw new IllegalArgumentException("이동할 수 없는 경로 입니다.");
        }
    }
}
