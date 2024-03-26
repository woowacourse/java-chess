package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public FileDifference calculateFileDifferenceTo(Position position) {
        return new FileDifference(file, position.file);
    }

    public RankDifference calculateRankDifferenceTo(Position position) {
        return new RankDifference(rank, position.rank);
    }

    public List<Position> findRoute(Position position) {
        List<File> files = file.findFileRouteToTargetFile(position.file);
        List<Rank> ranks = rank.findRankRouteToTargetRank(position.rank);

        if (files.size() == ranks.size()) {
            return findDiagonalRoute(files, ranks);
        }
        return findStraightRoute(files, ranks);
    }

    private List<Position> findDiagonalRoute(List<File> files, List<Rank> ranks) {
        List<Position> positions = new ArrayList<>();
        if (files.size() != ranks.size()) {
            throw new IllegalArgumentException("이동할 수 없는 경로가 입력되었습니다.");
        }
        for (int i = 0; i < files.size(); i++) {
            positions.add(new Position(
                    files.get(i), ranks.get(i)
            ));
        }

        return positions;
    }

    private List<Position> findStraightRoute(List<File> files, List<Rank> ranks) {
        List<Position> positions = new ArrayList<>();
        if(files.isEmpty()){
            ranks.forEach((routeRank) -> positions.add(new Position(file, routeRank)));
        }
        if(ranks.isEmpty()){
            files.forEach((routeFile) -> positions.add(new Position(routeFile, rank)));
        }
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;

        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
