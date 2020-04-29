package chess.domain.piece.policy.move;

import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Distance;
import chess.domain.piece.position.Position;

class HasHindranceInBetween {
    static boolean check(PiecesState piecesState, Distance amount, Direction direction, Position targetPosition) {
        for (int i = Position.FORWARD_AMOUNT; i < amount.getValue(); i++) {
            targetPosition = targetPosition.go(direction);
            if (piecesState.isFilled(targetPosition)) {
                return true;
            }
        }
        return false;
    }
}
