package chess.piece;

import chess.position.Position;
import chess.position.Rank;

public enum Color {

    BLACK(Rank.SEVEN), WHITE(Rank.TWO);

    private Rank rankOfStartPawn;

    Color(Rank rankOfStartPawn) {
        this.rankOfStartPawn = rankOfStartPawn;
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

    public boolean isStartPawnPosition(Position position) {
        return position.isSameRank(rankOfStartPawn);
    }
}
