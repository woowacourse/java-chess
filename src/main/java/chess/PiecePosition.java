package chess;

public class PiecePosition {
    private final File file;
    private final Rank rank;

    public PiecePosition(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Boolean isBlackKing() {
        return file == File.E && rank==Rank.EIGHT;
    }

    public Boolean isBlackQueen() {
        return file == File.D && rank==Rank.EIGHT;
    }

    public Boolean isBlackRook() {
        return rank == Rank.EIGHT && (file == File.A || file == File.H);
    }

    public Boolean isBlackBishop() {
        return rank == Rank.EIGHT && (file == File.C || file == File.F);
    }

    public Boolean isBlackKnight() {
        return rank == Rank.EIGHT && (file == File.D ||file == File.G);
    }

    public Boolean isBlackPawn() {
        return rank == Rank.SEVEN;
    }

    public Boolean isWhiteKing() {
        return file == File.E && rank==Rank.ONE;
    }

    public Boolean isWhiteQueen() {
        return file == File.D && rank==Rank.ONE;
    }

    public Boolean isWhiteRook() {
        return rank == Rank.ONE && (file == File.A || file == File.H);
    }

    public Boolean isWhiteBishop() {
        return rank == Rank.ONE && (file == File.C || file == File.F);
    }

    public Boolean isWhiteKnight() {
        return rank == Rank.ONE && (file == File.D ||file == File.G);
    }

    public Boolean isWhitePawn() {
        return rank == Rank.TWO;
    }

    public Boolean isNone() {
        return rank == Rank.THREE || rank == Rank.FOUR || rank == Rank.FIVE || rank == Rank.SIX;
    }
}
