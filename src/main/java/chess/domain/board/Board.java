package chess.domain.board;

import chess.domain.piece.*;

import java.util.*;

public class Board {

    private final Map<Square, Piece> board;

    public Board(final Map<Square, Piece> board) {
        this.board = board;
    }

    public Optional<Piece> findPieceOf(final Square square) {
        return Optional.ofNullable(board.get(square));
    }

    public boolean canMove(final Square source, final List<Square> routes) {
        final boolean isExistHurdle = isExistHurdle(routes.subList(0, routes.size() - 1));
        final Square destination = routes.get(routes.size() - 1);
        if (!board.containsKey(destination)) {
            return !isExistHurdle;
        }
        return isAttackable(source, destination);
    }

    private boolean isExistHurdle(final List<Square> squares) {
        return squares.stream()
                .anyMatch(square -> board.containsKey(square));
    }

    private boolean isAttackable(final Square source, final Square destination) {
        return board.containsKey(destination) && !isSameColor(source, destination);
    }

    private boolean isSameColor(final Square source, final Square destination) {
        return board.get(source).isBlack() == board.get(destination).isBlack();
    }

    public boolean canMovePawn(final Square source, final List<Square> routes) {
        if (isDiagonalUnit(source, routes.get(0))) {
            return isAttackable(source, routes.get(0));
        }
        return canMoveStraight(source, routes);
    }

    private boolean isDiagonalUnit(final Square source, final Square destination) {
        final int distanceFile = destination.calculateDistanceFile(source);
        final int distanceRank = destination.calculateDistanceRank(source);
        return Math.abs(distanceFile) == Math.abs(distanceRank) && Math.abs(distanceFile) == 1;
    }

    private boolean canMoveStraight(final Square source, final List<Square> routes) {
        final Piece piece = board.get(source);
        final Square destination = routes.get(0);
        final int distanceRank = Math.abs(destination.calculateDistanceRank(source));
        return !board.containsKey(destination)
                && (distanceRank == 2 && source.isInitPawnPosition(piece.isBlack()) || (distanceRank == 1));
    }

    public void move(final Square source, final Square destination) {
        board.put(destination, board.remove(source));
    }

    public Map<Square, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Double resultOf(final Color color) {
        final double totalWithoutPawnScore = calculateWithoutPawn(color);
        return totalWithoutPawnScore + calculatePawn(color);
    }

    private double calculatePawn(final Color color) {
        int countPawn = countPawn(color);
        int countPawnInSameFile = countPawnInSameFile(color);
        return PieceScore.calculatePawn(countPawn, countPawnInSameFile);
    }

    private int countPawn(final Color color) {
        return (int) board.values().stream()
                .filter(piece -> piece.isPawn() && piece.isBlack() == color.isBlack())
                .count();
    }

    private int countPawnInSameFile(final Color color) {
        int countSamePawn = 0;
        for (File file : File.values()) {
            long countSamePawns = Arrays.stream(Rank.values())
                    .filter(rank -> isPawnInSameFile(file, rank, color))
                    .count();
            if (countSamePawns > 1) {
                countSamePawn += countSamePawns;
            }
        }
        return countSamePawn;
    }

    private boolean isPawnInSameFile(File file, Rank rank, Color color) {
        Piece piece = getPiece(file, rank);
        return piece.isPawn() && piece.isSameColor(color);
    }

    private Piece getPiece(File file, Rank rank) {
        return board.getOrDefault(new Square(file, rank), new Empty(Color.BLACK));
    }

    public double calculateWithoutPawn(Color color) {
        Map<Class, Integer> numberOfPieces = new HashMap<>(Map.of(
                Pawn.class, 0,
                Rook.class, 0,
                Knight.class, 0,
                Bishop.class, 0,
                Queen.class, 0,
                King.class, 0
        ));
        for (Class pieceType : numberOfPieces.keySet()) {
            int countPieces = (int) board.values().stream()
                    .filter(piece -> !piece.isPawn() && piece.isBlack() == color.isBlack() && piece.getClass().equals(pieceType))
                    .count();
            numberOfPieces.put(pieceType, countPieces);
        }
        return PieceScore.calculateWithoutPawn(numberOfPieces);
    }
}
