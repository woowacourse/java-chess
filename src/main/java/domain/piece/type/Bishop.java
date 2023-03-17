package domain.piece.type;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Direction;
import domain.piece.Piece;

public class Bishop extends Piece {
    private static final List<Direction> movableDirection = List.of(Direction.NORTH_EAST, Direction.NORTH_WEST,
            Direction.SOUTH_EAST, Direction.SOUTH_WEST);

    public Bishop(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isBishop() {
        return true;
    }

    @Override
    public List<Square> fetchMovePath(Square currentSquare, Square targetSquare) {
        List<Integer> gaps = calculateGap(currentSquare, targetSquare);
        Integer distance = Collections.max(gaps);
        validateMovable(gaps);
        List<Integer> direction = calculateDirection(gaps, distance);
        return calculatePath(currentSquare, distance, direction);
    }

    private void validateMovable(List<Integer> gaps) {
        if (isNotDiagonal(gaps)) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }

    private List<Integer> calculateGap(Square currentSquare, Square targetSquare) {
        return IntStream.range(0, 2)
                .map(i -> targetSquare.toCoordinate().get(i) - currentSquare.toCoordinate().get(i))
                .boxed()
                .collect(Collectors.toList());
    }

    private static List<Integer> calculateDirection(List<Integer> gaps, Integer distance) {
        return gaps.stream()
                .mapToInt(gap -> gap / distance)
                .boxed()
                .collect(Collectors.toList());
    }

    private List<Square> calculatePath(Square currentSquare, Integer distance, List<Integer> direction) {
        List<Integer> coordinate = currentSquare.toCoordinate();
        Integer currentFile = coordinate.get(FILE);
        Integer currentRank = coordinate.get(RANK);
        return IntStream.range(1, distance + 1)
                .mapToObj(dist -> new Square(currentFile + direction.get(FILE) * dist,
                        currentRank + direction.get(RANK) * dist))
                .collect(Collectors.toUnmodifiableList());
    }

    private boolean isNotDiagonal(List<Integer> gap) {
        return !Objects.equals(Math.abs(gap.get(FILE)), Math.abs(gap.get(RANK)));
    }
}
