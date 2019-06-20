package chess.domain;

import chess.domain.RuleImpl.Pawn;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreCalculatorTest {
    @Test
    public void 초기화_점수_총합() {
        Board board = new Board(BoardGenerator.generate());
        ScoreCalculator scoreCalculator = board.createScoreCalculator();

        assertThat(scoreCalculator.getScore(Piece.Color.WHITE)).isEqualTo(scoreCalculator.getScore(Piece.Color.BLACK));
    }

    @Test
    public void 세로_겹칠_떄() {
        Map<Position, Square> map = new HashMap<>();
        Position position = Position.of("2", "a");
        map.put(position, Square.of(position, Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM)));
        position = Position.of("3", "a");
        map.put(position, Square.of(position, Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM)));
        position = Position.of("4", "a");
        map.put(position, Square.of(position, Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM)));
        position = Position.of("2", "b");
        map.put(position, Square.of(position, Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM)));

        Board board = new Board(map);
        ScoreCalculator scoreCalculator = board.createScoreCalculator();

        assertThat(scoreCalculator.getScore(Piece.Color.WHITE)).isEqualTo(2.5);
    }

    @Test
    public void 화이트_말의_점수_총합() {
        Board board = new Board(BoardGenerator.generate());
        ScoreCalculator scoreCalculator = board.createScoreCalculator();

        assertThat(scoreCalculator.getScore(Piece.Color.WHITE)).isEqualTo(38);
    }

}
