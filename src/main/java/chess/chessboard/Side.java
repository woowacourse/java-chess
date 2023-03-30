package chess.chessboard;

import chess.piece.Piece;

public enum Side {
    WHITE {
        @Override
        public Side nextTurn() {
            return BLACK;
        }
    },
    BLACK {
        @Override
        public Side nextTurn() {
            return WHITE;
        }
    },
    EMPTY {
        @Override
        public Side nextTurn() {
            throw new UnsupportedOperationException("EMPTY는 nextTurn을 지원하지 않습니다");
        }
    };

    public static Side initialTurn() {
        return WHITE;
    }

    public abstract Side nextTurn();

    public boolean isTurnOf(final Piece piece) {
        return piece;
    }
}
