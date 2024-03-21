package chess.domain;

import java.util.ArrayList;
import java.util.List;

public record Square(File file, Rank rank) {

    public static Square from(String square) {
        File file = File.from(square.substring(0, 1));
        Rank rank = Rank.from(square.substring(1, 2));
        return new Square(file, rank);
    }

    public List<Square> generatePath(Square target) {
        List<Square> path = new ArrayList<>();

        int vectorFile = target.signumFile(file);
        int vectorRank = target.signumRank(rank);

        Square current = this;
        do {
            current = current.addVector(vectorFile, vectorRank);
            path.add(current);
        } while (!target.equals(current));

        return path;
    }

    private Square addVector(int vectorFile, int vectorRank) {
        return new Square(
                this.file.add(vectorFile),
                this.rank.add(vectorRank));
    }

    private int signumFile(File file) {
        return (int) Math.signum(this.file.diff(file));
    }

    private int signumRank(Rank rank) {
        return (int) Math.signum(this.rank.diff(rank));
    }

    public boolean isSameRank(Square square) {
        return rank == square.rank();
    }

    public boolean isSameFile(Square square) {
        return file == square.file();
    }

    public int calculateRankDiff(Rank target) {
        return Math.abs(rank.diff(target));
    }

    public int calculateFileDiff(File target) {
        return Math.abs(file.diff(target));
    }
}
