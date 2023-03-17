package chess.domain.piece.moveRule;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class BishopMoveRule extends UnJumpableMoveRule {

    private static BishopMoveRule instance;

    private BishopMoveRule() {
    }

    public static BishopMoveRule getInstance() {
        if (instance == null) {
            instance = new BishopMoveRule();
        }
        return instance;
    }

    @Override
    public void move(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        validateDiagonal(currentPosition, nextPosition);
        List<Position> route = currentPosition.getRoute(nextPosition);
        validateRoute(board, route);
        validateDestination(currentPosition, nextPosition, board);

        updatePiecePosition(currentPosition, nextPosition, board);
    }

    private void validateDiagonal(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isDiagonalEqual(nextPosition)) {
            throw new IllegalArgumentException("비숍은 대각선상으로만 움직일 수 있습니다.");
        }
    }

    public PieceType pieceType() {
        return PieceType.BISHOP;
    }
}
