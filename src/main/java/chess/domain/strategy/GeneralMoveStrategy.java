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
    public void move(Color turnColor, Position from, Position to) {
        Piece currentPiece = board.get(from);
        checkTurnOf(currentPiece, turnColor);
        Piece destinationPiece = board.get(to);
        Set<Position> pathToDestination = currentPiece.findPathTo(to);
        validateMovable(turnColor, pathToDestination, destinationPiece);
        updateBoard(from, to, currentPiece);
    }

    private void validateMovable(Color turnColor, Set<Position> pathToDestination, Piece destinationPiece) {
        if (destinationPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("이동할 수 없는 경로 입니다.");
        }
        if (isNotAllBlankPath(pathToDestination)) {
            throw new IllegalArgumentException("이동할 수 없는 경로 입니다.");
        }
    }
}
