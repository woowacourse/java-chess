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

    private final double point;
    private final Class<? extends Piece> pieceClass;

    Point(final double point, final Class<? extends Piece> pieceClass) {
        this.point = point;
        this.pieceClass = pieceClass;
    }

    public static double calculatePointByTeam(final Team team, final Map<Square, Piece> board) {
        double sum = 0;
        List<Integer> counts = getCounts();
        for (Square square : board.keySet()) {
            Piece piece = board.get(square);
            if (piece.isAlly(team)) {
                if (piece.isPawn()) {
                    addCount(counts, square.getFileNumber());
                }
                sum += getPoint(piece);
            }
        }
        for (int count : counts) {
            if (count == 1) {
                sum += PAWN.point;
            }
        }
        return sum;
    }

    private static List<Integer> getCounts() {
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            counts.add(0);
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
