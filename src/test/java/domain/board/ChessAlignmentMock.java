package domain.board;

import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

final class ChessAlignmentMock {
    private static class Base implements ChessAlignment {
        @Override
        public void addInitialPawns(final Map<Position, Piece> board) {
        }

        @Override
        public void addInitialKings(final Map<Position, Piece> board) {
        }

        @Override
        public void addInitialQueens(final Map<Position, Piece> board) {
        }

        @Override
        public void addInitialBishops(final Map<Position, Piece> board) {
        }

        @Override
        public void addInitialKnights(final Map<Position, Piece> board) {
        }

        @Override
        public void addInitialRooks(final Map<Position, Piece> board) {
        }
    }

    ;

    static ChessAlignment testStrategy(Map<Position, Piece> map) {
        return new Base() {
            @Override
            public void addInitialPawns(final Map<Position, Piece> board) {
                board.putAll(map);
            }
        };
    }
}
