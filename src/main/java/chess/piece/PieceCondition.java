package chess.piece;

import chess.File;
import chess.Rank;

public class PieceCondition {

    private final File file;
    private final Rank rank;

    public PieceCondition(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isKing() {
        return file == File.E && isNotPawnOrNone();
    }

    public boolean isQueen() {
        return file == File.D && isNotPawnOrNone();
    }

    public boolean isRook() {
        return (file == File.A || file == File.H) && isNotPawnOrNone();
    }

    public boolean isBishop() {
        return (file == File.C || file == File.F) && isNotPawnOrNone();
    }

    public boolean isKnight() {
        return (file == File.B || file == File.G) && isNotPawnOrNone();
    }

    private boolean isNotPawnOrNone() {
        return rank == Rank.ONE || rank == Rank.EIGHT;
    }

    public boolean isPawn() {
        return rank==Rank.TWO||rank==Rank.SEVEN;
    }

    public boolean isNone() {
        return rank == Rank.THREE || rank == Rank.FOUR || rank == Rank.FIVE || rank == Rank.SIX;
    }
}
