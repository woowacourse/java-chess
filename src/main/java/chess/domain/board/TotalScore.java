package chess.domain.board;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public abstract class TotalScore {

    private static final double SAME_FILE_PAWN_SUBTRACT_VALUE = 0.5;

    private TotalScore() {
    }

    public static double getTotalPoint(final Map<Position, Piece> pieces) {
        double totalPoint = getDefaultPoints(pieces.values());
        final List<Position> pawnPosition = findPawnsPositions(pieces);

        for (File file : File.values()) {
            totalPoint -= countSameFilePawn(pawnPosition, file) * SAME_FILE_PAWN_SUBTRACT_VALUE;
        }
        return totalPoint;
    }

    private static List<Position> findPawnsPositions(final Map<Position, Piece> pieces) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isPawn())
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }

    private static double getDefaultPoints(final Collection<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private static int countSameFilePawn(final List<Position> pawnPositions, final File file) {
        final int sameFilePawnCount = (int) pawnPositions.stream()
                .filter(position -> position.isInFile(file))
                .count();
        if (sameFilePawnCount > 1) {
            return sameFilePawnCount;
        }
        return 0;
    }
}
