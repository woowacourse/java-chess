package domain.board;

import domain.piece.move.Coordinate;
import domain.piece.move.Situation;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.Color;
import domain.piece.Piece;

import java.util.Map;
import java.util.stream.Collectors;

public final class Board {

    private static final int KING_COUNT = 2;

    private final Map<Coordinate, Piece> squareLocations;

    public Board() {
        this.squareLocations = BoardInitialImage.getCachedBoard();
    }

    public Board(final Map<Coordinate, Piece> squareLocations) {
        this.squareLocations = squareLocations;
    }

    public Piece findSquare(final Coordinate target) {
        return squareLocations.get(target);
    }

    public void replaceSquare(final Coordinate target, final Piece piece) {
        if (piece.isPawn()) {
            replaceSquareIfIsPawn(target, piece);
            return;
        }
        squareLocations.put(target, piece);
    }

    private void replaceSquareIfIsPawn(final Coordinate target, final Piece piece) {
        if (piece.hasSameColorWith(Color.BLACK)) {
            squareLocations.put(target, new BlackPawn(Color.BLACK));
            return;
        }
        squareLocations.put(target, new WhitePawn(Color.WHITE));
    }

    public void replaceWithEmptySquare(final Coordinate target) {
        squareLocations.put(target, Piece.ofEmpty());
    }

    public boolean isMovable(final Coordinate start, final Coordinate end) {
        validateOverBoardSize(start, end);
        Situation situation = Situation.of(findSquare(start), findSquare(end));
        return findSquare(start).isMovable(start, end, situation);
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
                .filter(square -> square.hasSameColorWith(color))
                .map(Piece::getPoint)
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
                .filter(entry -> entry.getValue().hasSameColorWith(color))
                .filter(entry -> entry.getValue().isPawn())
                .collect(Collectors.groupingBy(entry -> entry.getKey().getCol(), Collectors.counting()));
    }

    public boolean isSquareEmptyAt(final Coordinate target) {
        return squareLocations.get(target)
                .hasSameColorWith(Color.NEUTRAL);
    }

    public boolean allKingAlive() {
        return squareLocations.values().stream()
                .filter(Piece::isKing)
                .count() == KING_COUNT;
    }

    public Map<Coordinate, Piece> getSquareLocations() {
        return squareLocations;
    }
}
