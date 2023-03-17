package chess.domain.piece.moveRule;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class QueenMoveRule extends UnJumpableMoveRule {

    private static QueenMoveRule instance;

    private QueenMoveRule() {
    }

    public static QueenMoveRule getInstance() {
        if (instance == null) {
            instance = new QueenMoveRule();
        }
        return instance;
    }

    @Override
    public void move(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        validateStraightOrDiagonal(currentPosition, nextPosition);
        List<Position> route = currentPosition.getRoute(nextPosition);
        validateRoute(board, route);
        validateDestination(currentPosition, nextPosition, board);

        updatePiecePosition(currentPosition, nextPosition, board);
    }

    private void validateStraightOrDiagonal(Position currentPosition, Position nextPosition) {
        if (!(currentPosition.isDiagonalEqual(nextPosition) || currentPosition.isStraightEqual(nextPosition))) {
            throw new IllegalArgumentException("퀸은 대각선 또는 직선 상으로만 움직일 수 있습니다.");
        }
    }

    @Override
    public PieceType pieceType() {
        return PieceType.QUEEN;
    }
}
