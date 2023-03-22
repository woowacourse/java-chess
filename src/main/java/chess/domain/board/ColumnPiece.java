package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ColumnPiece {

    private static final Map<Class<? extends Piece>, Score> scoreMap = Map.of(
            Rook.class, Score.from(5),
            Bishop.class, Score.from(3),
            Knight.class, Score.from(2.5),
            Pawn.class, Score.from(0.5),
            Queen.class, Score.from(9),
            King.class, Score.from(0)
    );
    private static final Class<Pawn> PAWN_CLASS_TYPE = Pawn.class;
    private static final int COUNT_MAP_DEFAULT_VALUE = 0;
    private static final int SINGLE_PAWN_COUNT = 1;

    private final List<Piece> pieces;

    public ColumnPiece(final List<Piece> pieces) {
        this.pieces = pieces;
    }


    public Score calculatePiecesScore(final Color color) {

        final Map<Class<? extends Piece>, Integer> classTypeMap = countPerClassTypeBy(color);

        final Score score = calculateScore(classTypeMap);

        if (hasSinglePawn(classTypeMap)) {
            return score.plus(scoreMap.get(PAWN_CLASS_TYPE));
        }

        return score;
    }

    private Map<Class<? extends Piece>, Integer> countPerClassTypeBy(final Color color) {
        return pieces.stream()
                     .filter(it -> it.isSameColor(color))
                     .collect(Collectors.groupingBy(
                             Piece::getClass,
                             Collectors.summingInt(p -> 1)));
    }

    private Score calculateScore(final Map<Class<? extends Piece>, Integer> countingPerClassType) {
        return countingPerClassType.entrySet()
                                   .stream()
                                   .map(it -> scoreMap.get(it.getKey())
                                                      .multiply(it.getValue()))
                                   .reduce(Score.ZERO, Score::plus);
    }

    private boolean hasSinglePawn(final Map<Class<? extends Piece>, Integer> countingPerClassType) {
        return countingPerClassType.getOrDefault(PAWN_CLASS_TYPE, COUNT_MAP_DEFAULT_VALUE) == SINGLE_PAWN_COUNT;
    }
}
