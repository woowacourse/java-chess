package domain.piece;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.board.Square;

public abstract class Piece {
    protected static final int FILE = 0;
    protected static final int RANK = 1;

    public Camp getCamp() {
        return camp;
    }

    private final Camp camp;

    public Piece(Camp camp) {
        this.camp = camp;
    }

    public boolean isWhite() {
        return camp.equals(Camp.WHITE);
    }


    public List<Square> fetchMovePath(Square currentSquare, Square targetSquare) {
        List<Integer> gaps = calculateGap(currentSquare, targetSquare);
        Integer distance = calculateDistance(gaps);
        validateMovable(gaps);
        List<Integer> direction = calculateDirection(gaps, distance);
        return calculatePath(currentSquare, distance, direction);
    }

    protected Integer calculateDistance(List<Integer> gaps) {
        List<Integer> absGaps = gaps.stream()
                .map(Math::abs)
                .collect(Collectors.toList());
        return Collections.max(absGaps);
    }

    protected abstract void validateMovable(List<Integer> gaps);

    protected List<Integer> calculateGap(Square currentSquare, Square targetSquare) {
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

    protected boolean isNotForward(List<Integer> gap) {
        Integer fileGap = gap.get(FILE);
        Integer RankGap = gap.get(RANK);
        return fileGap != 0 && RankGap != 0;
    }

    protected boolean isNotDiagonal(List<Integer> gap) {
        return !Objects.equals(Math.abs(gap.get(FILE)), Math.abs(gap.get(RANK)));
    }

    public boolean canMove(Map<Square, Camp> pathInfo, Square targetSquare) {
        Camp targetCamp = pathInfo.get(targetSquare);
        pathInfo.remove(targetSquare);
        return isDifferentCampOrEmptyOnTarget(targetCamp) && !isExistPieceOnPath(pathInfo);
    }

    private boolean isDifferentCampOrEmptyOnTarget(Camp targetCamp) {
        return isDifferentCamp(targetCamp) || targetCamp.equals(Camp.NONE);
    }

    protected boolean isExistPieceOnPath(Map<Square, Camp> pathInfo) {
        return pathInfo.values().stream()
                .anyMatch(camp -> camp != Camp.NONE);
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

    public boolean isPawn() {
        return false;
    }

    public boolean isRook() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isBishop() {
        return false;
    }

    public boolean isQueen() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }
}
