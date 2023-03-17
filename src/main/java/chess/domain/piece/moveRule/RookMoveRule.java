package chess.domain.piece.moveRule;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class RookMoveRule extends UnJumpableMoveRule {


    private static RookMoveRule instance;

    private RookMoveRule() {
    }

    public static RookMoveRule getInstance() {
        if (instance == null) {
            instance = new RookMoveRule();
        }
        return instance;
    }

    @Override
    public void move(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        validateStraight(currentPosition, nextPosition);
        List<Position> route = currentPosition.getRoute(nextPosition);
        validateRoute(board, route);
        validateDestination(currentPosition, nextPosition, board);

        updatePiecePosition(currentPosition, nextPosition, board);
    }

    private void validateStraight(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isStraightEqual(nextPosition)) {
            throw new IllegalArgumentException("룩은 직선상으로만 움직일 수 있습니다.");
        }
    }

    @Override
    public PieceType pieceType() {
        return PieceType.ROOK;
    }

}
