package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import chess.domain.square.Square;

public enum Point {

    PAWN(0.5, Pawn.class),
    ROOK(5, Rook.class),
    KNIGHT(2.5, Knight.class),
    BISHOP(3, Bishop.class),
    QUEEN(9, Queen.class),
    KING(0, King.class);

    public static final int NUMBER_OF_FILE = 8;
    public static final int INITIAL_COUNT = 0;

    private final double point;
    private final Class<? extends Piece> pieceClass;

    Point(final double point, final Class<? extends Piece> pieceClass) {
        this.point = point;
        this.pieceClass = pieceClass;
    }

    public static double calculatePointByTeam(final Team team, final Map<Square, Piece> board) {
        List<Integer> counts = getCounts();
        double sum = board.keySet().stream()
                .peek(square -> addCountWhenPieceIsAllyPawn(board.get(square), team, counts, square))
                .mapToDouble(square -> getPointWhenPieceIsAlly(board.get(square), team))
                .sum();
        sum += getPawnAdditionalPoint(counts);
        return sum;
    }

    private static void addCountWhenPieceIsAllyPawn(Piece piece, Team team, List<Integer> counts, Square square) {
        if (isAllyPawn(piece, team)) {
            addCount(counts, square.getFileNumber());
        }
    }

    private static double getPointWhenPieceIsAlly(Piece piece, Team team) {
        if (piece.isAlly(team)) {
            return getPoint(piece);
        }
        return 0;
    }

    private static double getPawnAdditionalPoint(List<Integer> counts) {
        return counts.stream()
                .mapToDouble(Point::getPawnPointWhenCountIsOne)
                .sum();
    }

    private static double getPawnPointWhenCountIsOne(int count) {
        if (count == 1) {
            return PAWN.point;
        }
        return 0;
    }

    private static boolean isAllyPawn(Piece piece, Team team) {
        return piece.isAlly(team) && piece.isPawn();
    }

    private static List<Integer> getCounts() {
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_FILE; i++) {
            counts.add(INITIAL_COUNT);
        }
        return counts;
    }

    private static void addCount(List<Integer> counts, int fileNumber) {
        int increasedCount = counts.get(fileNumber) + 1;
        counts.set(fileNumber, increasedCount);
    }

    private static double getPoint(final Piece piece) {
        Class<? extends Piece> pieceClass = piece.getClass();
        return Arrays.stream(Point.values())
                .filter(it -> it.pieceClass == pieceClass)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 클래스는 존재하지 않습니다."))
                .point;
    }
}
