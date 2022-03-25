package chess.piece;

import chess.position.*;

public enum Color {

    BLACK(Rank.SEVEN), WHITE(Rank.TWO);

    private Rank rankOfStartPawn;

    Color(Rank rankOfStartPawn) {
        this.rankOfStartPawn = rankOfStartPawn;
    }

    public boolean isForward(Transition transition) {
        if (this == WHITE) {
            return transition.isUpward();
        }
        return transition.isDownward();
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
