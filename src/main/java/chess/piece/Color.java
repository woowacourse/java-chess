package chess.piece;

import chess.position.Rank;
import chess.position.Position;

public enum Color {

    BLACK(Rank.SEVEN), WHITE(Rank.TWO);

    private Rank firstPawnRank;

    Color(Rank firstPawnRank) {
        this.firstPawnRank = firstPawnRank;
    }

    public boolean isForward(Position from, Position to) {
        if (this == WHITE) {
            return from.isUpward(to);
        }
        return from.isDownward(to);
    }

    public Color reverse() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isFirstMove(Position position) {
        return position.isSameRank(firstPawnRank);
    }
}
