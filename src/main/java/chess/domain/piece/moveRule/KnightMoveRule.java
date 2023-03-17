package chess.domain.piece.moveRule;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.Map;

public class KnightMoveRule implements MoveRule {

    private static KnightMoveRule instance;
    private KnightMoveRule() {}

    public static KnightMoveRule getInstance() {
        if (instance == null) {
            instance = new KnightMoveRule();
        }
        return instance;
    }

    @Override
    public void move(Position currentPosition, Position nextPosition,  Map<Position, Piece> board) {
        validateKnightDirection(currentPosition, nextPosition);
        validateDestination(currentPosition, nextPosition, board);

        updatePiecePosition(currentPosition, nextPosition, board);
    }

    private void validateKnightDirection(Position currentPosition, Position nextPosition) {
        if (!currentPosition.isKnightPosition(nextPosition)) {
            throw new IllegalArgumentException("나이트는 L 모양으로만 움직일 수 있습니다.");
        }
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KNIGHT;
    }
}
