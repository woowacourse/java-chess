package domain.piece;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.board.Square;
import domain.piece.type.Type;

public abstract class Piece {
    protected static final int FILE = 0;
    protected static final int RANK = 1;

    private final Camp camp;
    private final Type type;

    public Piece(Camp camp, Type type) {
        this.camp = camp;
        this.type = type;
    }

    protected abstract void validateMovable(List<Integer> gaps);


    public abstract List<Square> fetchMovePath(Square currentSquare, Square targetSquare);

    public abstract boolean canMove(Map<Square, Camp> pathInfo, Square targetSquare);

    public boolean isWhite() {
        return camp.equals(Camp.WHITE);
    }

    protected Integer calculateDistance(List<Integer> gaps) {
        List<Integer> absGaps = gaps.stream()
                .map(Math::abs)
                .collect(Collectors.toList());
        return Collections.max(absGaps);
    }

    protected List<Integer> calculateGap(Square currentSquare, Square targetSquare) {
        return IntStream.range(0, 2)
                .map(i -> targetSquare.toCoordinate().get(i) - currentSquare.toCoordinate().get(i))
                .boxed()
                .collect(Collectors.toList());
    }

    protected List<Integer> calculateDirection(List<Integer> gaps, Integer distance) {
        return gaps.stream()
                .mapToInt(gap -> gap / distance)
                .boxed()
                .collect(Collectors.toList());
    }

    protected List<Square> calculatePath(Square currentSquare, Integer distance, List<Integer> direction) {
        List<Integer> coordinate = currentSquare.toCoordinate();
        Integer currentFile = coordinate.get(FILE);
        Integer currentRank = coordinate.get(RANK);
        return IntStream.range(1, distance + 1)
                .mapToObj(dist -> Square.of(currentFile + direction.get(FILE) * dist,
                        currentRank + direction.get(RANK) * dist))
                .collect(Collectors.toUnmodifiableList());
    }

    protected boolean isExistPieceOnPath(Map<Square, Camp> pathInfo) {
        return pathInfo.values().stream()
                .anyMatch(camp -> camp != Camp.NONE);
    }

    protected boolean isDifferentCampOrEmptyOnTarget(Camp targetCamp) {
        return isDifferentCamp(targetCamp) || targetCamp.equals(Camp.NONE);
    }

    protected boolean isDifferentCamp(Camp otherCamp) {
        Camp camp = this.camp;
        if (camp.equals(Camp.BLACK) && otherCamp.equals(Camp.WHITE)) {
            return true;
        }
        if (camp.equals(Camp.WHITE) && otherCamp.equals(Camp.BLACK)) {
            return true;
        }
        return false;
    }

    public double getScore() {
        return type.getScore();
    }

    public Camp getCamp() {
        return camp;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return camp == piece.camp && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp, type);
    }
}
