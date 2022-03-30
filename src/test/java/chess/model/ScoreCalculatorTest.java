package chess.model;

import static chess.model.File.*;
import static chess.model.PieceColor.*;
import static chess.model.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.model.boardinitializer.BoardInitializer;
import chess.model.boardinitializer.defaultInitializer;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;

class ScoreCalculatorTest {

    @Test
    @DisplayName("초기 말들의 점수는 38.0 점이다.")
    void apply() {
        //given
        ScoreCalculator scoreCalculator = new ScoreCalculator(
            new Board(new TurnDecider(), new defaultInitializer()).getValues(),
            new TurnDecider());

        //when
        double actual = scoreCalculator.currentPlayerScore();

        Assertions.assertThat(actual).isEqualTo(38.0);
    }

    @Test
    @DisplayName("폰이 같은 File에 두 개, 일반적 폰 1개, 다른 팀 폰 1개 있을 경우 2점으로 계산한다.")
    void when_pawns_in_same_file() {
        ScoreCalculator scoreCalculator = new ScoreCalculator(
            new Board(new TurnDecider(), new testInitializer()).getValues(),
            new TurnDecider());

        //then
        double actual = scoreCalculator.currentPlayerScore();
        assertThat(actual).isEqualTo(2.0);
    }

    public static class testInitializer implements BoardInitializer {

        @Override
        public Map<Position, Piece> apply() {
            Map<Position, Piece> result = new HashMap<>();
            result.put(Position.of(FOUR, A), Pawn.colorOf(WHITE));
            result.put(Position.of(THREE, A), Pawn.colorOf(WHITE));
            result.put(Position.of(THREE, B), Pawn.colorOf(WHITE));
            result.put(Position.of(FIVE, A), Pawn.colorOf(BLACK));
            return result;
        }
    }
}
