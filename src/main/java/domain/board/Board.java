package domain.board;

import domain.piece.move.Coordinate;
import domain.piece.move.Situation;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.square.Color;
import domain.square.Square;

import java.util.Map;
import java.util.stream.Collectors;

public final class Board {

    private static final int KING_COUNT = 2;

    private final Map<Coordinate, Square> squareLocations;

    public Board() {
        this.squareLocations = BoardInitialImage.getCachedBoard();
    }

    public Board(final Map<Coordinate, Square> squareLocations) {
        this.squareLocations = squareLocations;
    }

    public Square findSquare(final Coordinate target) {
        return squareLocations.get(target);
    }

    public void replaceSquare(final Coordinate target, final Square square) {
        if (square.getPiece().isPawn()) {
            replaceSquareIfIsPawn(target, square);
            return;
        }
        squareLocations.put(target, square);
    }

    private void replaceSquareIfIsPawn(final Coordinate target, final Square square) {
        if (square.getColor() == Color.BLACK) {
            squareLocations.put(target, new Square(new BlackPawn(), Color.BLACK));
            return;
        }
        squareLocations.put(target, new Square(new WhitePawn(), Color.WHITE));
    }

    public void replaceWithEmptySquare(final Coordinate target) {
        squareLocations.put(target, Square.ofEmptyPiece());
    }

    public boolean isMovable(final Coordinate start, final Coordinate end) {
        validateOverBoardSize(start, end);

        return squareLocations.get(start).isPieceMovable(
                start,
                end,
                Situation.of(findSquare(start), findSquare(end))
        );
    }

    private void validateOverBoardSize(final Coordinate start, final Coordinate end) {
        if (squareLocations.containsKey(start) && squareLocations.containsKey(end)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 보드 좌표 범위를 벗어났습니다.");
    }

    public double collectPointFor(final Color color) {
        double purePoint = collectPurePointFor(color);
        return reducePointIfPawnsExistStraight(color, purePoint);
    }

    private double collectPurePointFor(final Color color) {
        return squareLocations.values().stream()
                .filter(square -> square.getColor() == color)
                .map(Square::getPoint)
                .reduce(Double::sum)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 보드가 초기화되지 않아 점수를 계산할 수 없습니다"));
    }

    private double reducePointIfPawnsExistStraight(final Color color, final double purePoint) {
        Map<Integer, Long> pawnCountForEachFile = makePawnCountForEachFile(color);

        return purePoint - pawnCountForEachFile.values().stream()
                .filter(col -> col >= 2)
                .map(col -> col * 0.5)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private Map<Integer, Long> makePawnCountForEachFile(final Color color) {
        return squareLocations.entrySet().stream()
                .filter(entry -> entry.getValue().getColor() == color)
                .filter(entry -> entry.getValue().hasPawn())
                .collect(Collectors.groupingBy(entry -> entry.getKey().getCol(), Collectors.counting()));
    }

    public boolean isSquareEmptyAt(final Coordinate target) {
        return squareLocations.get(target)
                .getColor() == Color.NEUTRAL;
    }

    public boolean allKingAlive() {
        return squareLocations.values().stream()
                .filter(Square::hasKing)
                .count() == KING_COUNT;
    }

    public Map<Coordinate, Square> getSquareLocations() {
        return squareLocations;
    }
}
