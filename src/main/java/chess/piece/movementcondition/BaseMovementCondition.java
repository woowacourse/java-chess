package chess.piece.movementcondition;

import chess.piece.Piece;
import chess.position.Position;
import java.util.Map;

public enum BaseMovementCondition implements MovementCondition {

    POSSIBLE {
        @Override
        public boolean isPossibleMovement(Position from, Position to, Map<Position, Piece> board) {
            return true;
        }
    },
    IMPOSSIBLE {
        @Override
        public boolean isPossibleMovement(Position from, Position to, Map<Position, Piece> board) {
            return false;
        }
    },
    MUST_OBSTACLE_FREE {
        @Override
        public boolean isPossibleMovement(Position from, Position to, Map<Position, Piece> board) {
            return from.getLinearPath(to).stream()
                    .filter(position -> !position.equals(from))
                    .filter(position -> !position.equals(to))
                    .noneMatch(board::containsKey);
        }
    },
    MUST_CAPTURE_PIECE {
        @Override
        public boolean isPossibleMovement(Position from, Position to, Map<Position, Piece> board) {
            return board.containsKey(to);
        }
    },
    MUST_EMPTY_DESTINATION {
        @Override
        public boolean isPossibleMovement(Position from, Position to, Map<Position, Piece> board) {
            return !board.containsKey(to);
        }
    }
}
