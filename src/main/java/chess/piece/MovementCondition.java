package chess.piece;

import chess.position.Position;
import java.util.Map;

public enum MovementCondition {

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
    UNOBSTRUCTED {
        @Override
        public boolean isPossibleMovement(Position from, Position to, Map<Position, Piece> board) {
            return from.getLinearPath(to).stream()
                    .noneMatch(board::containsKey);
        }
    },
    CATCHABLE {
        @Override
        public boolean isPossibleMovement(Position from, Position to, Map<Position, Piece> board) {
            return board.containsKey(to);
        }
    },
    UNCATCHABLE_AND_UNOBSTRUCTED {
        @Override
        public boolean isPossibleMovement(Position from, Position to, Map<Position, Piece> board) {
            return !board.containsKey(to) && from.getLinearPath(to).stream()
                    .noneMatch(board::containsKey);
        }
    };

    public abstract boolean isPossibleMovement(Position from, Position to, Map<Position, Piece> board);
}
