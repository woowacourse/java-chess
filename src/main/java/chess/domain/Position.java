package chess.domain;

import java.util.ArrayList;
import java.util.List;

public record Position(File file, Rank rank) {

    public static Position from(String position) {
        File file = File.from(position.substring(0, 1));
        Rank rank = Rank.from(position.substring(1, 2));
        return new Position(file, rank);
    }

    public List<Position> generatePath(Position target) {
        List<Position> path = new ArrayList<>();

        int vectorFile = target.signumFile(file);
        int vectorRank = target.signumRank(rank);

        Position current = this;
        do {
            current = current.addVector(vectorFile, vectorRank);
            path.add(current);
        } while (!target.equals(current));

        return path;
    }

    private Position addVector(int vectorFile, int vectorRank) {
        return new Position(
                this.file.add(vectorFile),
                this.rank.add(vectorRank));
    }

    private int signumFile(File file) {
        return (int) Math.signum(this.file.diff(file));
    }

    private int signumRank(Rank rank) {
        return (int) Math.signum(this.rank.diff(rank));
    }

    public boolean isSameRank(Position position) {
        return rank == position.rank();
    }

    public boolean isSameFile(Position position) {
        return file == position.file();
    }

    public int calculateRankDiff(Rank target) {
        return Math.abs(rank.diff(target));
    }

    public int calculateFileDiff(File target) {
        return Math.abs(file.diff(target));
    }
}
