package chess.domain.piece.moveRule;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.Map;

public class KingMoveRule implements MoveRule {

    private static KingMoveRule instance;

    private KingMoveRule() {
    }

    public static KingMoveRule getInstance() {
        if (instance == null) {
            instance = new KingMoveRule();
        }
        return instance;
    }

    @Override
    public void move(Position currentPosition, Position nextPosition, Map<Position, Piece> board) {
        validateNear(currentPosition, nextPosition);
        validateDestination(currentPosition, nextPosition, board);

        updatePiecePosition(currentPosition, nextPosition, board);
    }

    private void validateNear(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isNear(nextPosition)) {
            throw new IllegalArgumentException("킹은 인접한 칸으로만 이동할 수 있습니다.");
        }
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KING;
    }
}
