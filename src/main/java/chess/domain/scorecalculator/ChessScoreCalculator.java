package chess.domain.scorecalculator;

import chess.domain.Score;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChessScoreCalculator implements ScoreCalculator {

    @Override
    public Score calculate(List<Piece> pieces) {
        return pieces.stream()
            .map(Piece::score)
            .reduce(Score.from(0), Score::add)
            .minusPawnCount(sameColumnPawnCount(pieces));
    }

    private int sameColumnPawnCount(List<Piece> pieces) {
        return pieces.stream()
            .filter(Piece::isPawn)
            .map(Piece::column)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))

            .values()
            .stream()
            .mapToInt(Long::intValue)
            .filter(number -> number > 1)
            .sum();
    }
}
