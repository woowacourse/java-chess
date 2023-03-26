package domain.board;

import domain.piece.move.Coordinate;
import domain.piece.Color;
import domain.piece.Piece;

import java.util.Map;
import java.util.stream.Collectors;

public final class Board {

    private static final int KING_COUNT = 2;

    private final Map<Coordinate, Piece> pieceLocations;

    public Board() {
        this(BoardInitialImage.getCachedBoard());
    }

    public Board(final Map<Coordinate, Piece> pieceLocations) {
        this.pieceLocations = pieceLocations;
    }

    public Piece findPiece(final Coordinate target) {
        return pieceLocations.get(target);
    }

    public void replacePiece(final Coordinate target, final Piece piece) {
        pieceLocations.put(target, piece);
    }

    public void replaceWithEmptyPiece(final Coordinate target) {
        pieceLocations.put(target, Piece.ofEmpty());
    }

    public boolean isMovable(final Coordinate start, final Coordinate end) {
        validateOverBoardSize(start, end);
        return findPiece(start).isMovable(start, end);
    }

    public boolean isAttackable(final Coordinate start, final Coordinate end) {
        validateOverBoardSize(start, end);
        return findPiece(start).isAttackable(start, end, findPiece(end));
    }

    private void validateOverBoardSize(final Coordinate start, final Coordinate end) {
        if (pieceLocations.containsKey(start) && pieceLocations.containsKey(end)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 보드 좌표 범위를 벗어났습니다.");
    }

    public double collectPointFor(final Color color) {
        double purePoint = collectPurePointFor(color);
        return reducePointIfPawnsExistStraight(color, purePoint);
    }

    private double collectPurePointFor(final Color color) {
        return pieceLocations.values().stream()
                .filter(piece -> piece.getColor() == color)
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    private double reducePointIfPawnsExistStraight(final Color color, final double purePoint) {
        Map<Integer, Long> pawnCountForEachFile = makePawnCountForEachFile(color);
        return purePoint - pawnCountForEachFile.values().stream()
                .filter(pawnCount -> pawnCount >= 2)
                .mapToDouble(col -> col * 0.5)
                .sum();
    }

    private Map<Integer, Long> makePawnCountForEachFile(final Color color) {
        return pieceLocations.entrySet().stream()
                .filter(entry -> entry.getValue().getColor() == color)
                .filter(entry -> entry.getValue().isPawn())
                .collect(Collectors.groupingBy(entry -> entry.getKey().getCol(), Collectors.counting()));
    }

    public boolean isPieceEmptyAt(final Coordinate target) {
        return pieceLocations.get(target)
                .getColor() == Color.NEUTRAL;
    }

    public boolean allKingAlive() {
        return pieceLocations.values().stream()
                .filter(Piece::isKing)
                .count() == KING_COUNT;
    }

    public Map<Coordinate, Piece> getPieceLocations() {
        return pieceLocations;
    }
}
