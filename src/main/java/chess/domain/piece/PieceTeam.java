package chess.domain.piece;

import chess.domain.board.position.Rank;

public enum PieceTeam {
    WHITE(+1, Rank.TWO),
    BLACK(-1, Rank.SEVEN),
    EMPTY(0, Rank.ONE);

    private final int movingDirection;
    private final Rank firstRank;

    PieceTeam(int movingDirection, Rank firstRank) {
        this.movingDirection = movingDirection;
        this.firstRank = firstRank;
    }

    public int direction() {
        return movingDirection;
    }

    public Rank firstRank() {
        return firstRank;
    }
}
