package chess.domain.board;

import chess.domain.piece.Direction;
import chess.domain.piece.TeamType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Coordinate {
    private static final String DELIMITER = "";
    private static final int VALID_COORDINATE_TOKEN_SIZE = 2;

    private final File file;
    private final Rank rank;

    public Coordinate(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Coordinate from(String coordinateInput) {
        List<String> coordinateToken = Arrays.asList(coordinateInput.split(DELIMITER));
        validateCoordinate(coordinateToken);
        String fileInput = coordinateToken.get(0);
        String rankInput = coordinateToken.get(1);
        File file = File.findFileBySignature(fileInput);
        Rank rank = Rank.findRankBySignature(rankInput);
        return new Coordinate(file, rank);
    }

    private static void validateCoordinate(List<String> coordinateToken) {
        if (coordinateToken.size() != VALID_COORDINATE_TOKEN_SIZE) {
            throw new IllegalArgumentException("위치에 해당하는 유효한 좌표 값은 두 자리의 문자열입니다.");
        }
    }

    public Direction evaluateDirection(Coordinate targetCoordinate) {
        int fileDiff = targetCoordinate.file.calculateDifference(file);
        int rankDiff = targetCoordinate.rank.calculateDifference(rank);
        return Direction.findDirection(fileDiff, rankDiff);
    }

    public Coordinate move(Direction direction) {
        return new Coordinate(file.move(direction), rank.move(direction));
    }

    public List<Coordinate> findRouteCoordinatesTo(Coordinate targetCoordinate) {
        List<Coordinate> possibleCoordinates = new ArrayList<>();
        Direction direction = this.evaluateDirection(targetCoordinate);
        Coordinate nextCoordinate = this.move(direction);
        while (!nextCoordinate.equals(targetCoordinate)) {
            possibleCoordinates.add(nextCoordinate);
            nextCoordinate = nextCoordinate.move(direction);
        }
        return possibleCoordinates;
    }

    public boolean isTwoRankForwardFrom(Coordinate targetCoordinate) {
        return Math.abs(targetCoordinate.rank.calculateDifference(rank)) == 2 && targetCoordinate.file == file;
    }

    public boolean isDefaultPawnRank(TeamType teamType) {
        if (teamType == TeamType.BLACK) {
            return rank == Rank.SEVEN;
        }
        return rank == Rank.TWO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return file == that.file && rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
