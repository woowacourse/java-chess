package chess.domain.piece.info;

import chess.domain.position.Rank;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    public static Team initialOf(Rank rank) {
        if (rank == Rank.SEVEN || rank == Rank.EIGHT) {
            return BLACK;
        }
        if(rank == Rank.ONE || rank == Rank.TWO){
            return WHITE;
        }
        return EMPTY;
    }
}
