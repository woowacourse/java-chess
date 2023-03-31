package domain.board;

import domain.piece.Piece;
import domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public final class ChessAlignmentMock {
    private static class Base implements ChessAlignment {
        @Override
        public Map<Position, Piece> makeInitialPieces() {
            return null;
        }
    }

    public static ChessAlignment testStrategy(Map<Position, Piece> map) {
        return new Base() {
            @Override
            public Map<Position, Piece> makeInitialPieces() {
                return new HashMap<>(map);
            }
        };
    }
}
