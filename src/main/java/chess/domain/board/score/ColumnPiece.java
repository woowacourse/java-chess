package chess.domain.board.score;

import chess.domain.piece.slider.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.jumper.King;
import chess.domain.piece.jumper.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.slider.Queen;
import chess.domain.piece.slider.Rook;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.domain.board.score.PieceScore.*;

public class ColumnPiece {

    private static final Map<Class<? extends Piece>, Score> scoreMap = Map.of(
            Rook.class, Score.from(LOOK.value()),
            Bishop.class, Score.from(BISHOP.value()),
            Knight.class, Score.from(KNIGHT.value()),
            Pawn.class, Score.from(PAWN.value()),
            Queen.class, Score.from(QUEEN.value()),
            King.class, Score.from(KING.value())
    );
    private static final Class<Pawn> PAWN_CLASS_TYPE = Pawn.class;
    private static final int COUNT_MAP_DEFAULT_VALUE = 0;
    private static final int SINGLE_PAWN_COUNT = 1;
    private static final int VALUE_PER_ELEMENT = 1;

    private final List<Piece> pieces;

    public ColumnPiece(final List<Piece> pieces) {
        this.pieces = List.copyOf(pieces);
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
                             Collectors.summingInt(value -> VALUE_PER_ELEMENT)));
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
