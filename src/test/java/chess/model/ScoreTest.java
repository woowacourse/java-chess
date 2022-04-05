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

class ScoreTest {

    @Test
    @DisplayName("초기 말들의 점수는 38.0 점이다.")
    void apply() {
        //given
        Score score = new Score(
            new Board(new defaultInitializer()).getValues(),
            new TurnDecider());

        //when
        double actual = score.calculate();

        Assertions.assertThat(actual).isEqualTo(38.0);
    }

    @Test
    @DisplayName("폰이 같은 File에 두 개, 일반적 폰 1개, 다른 팀 폰 1개 있을 경우 2점으로 계산한다.")
    void when_pawns_in_same_file() {
        Score score = new Score(
            new Board(new testInitializer()).getValues(),
            new TurnDecider());

        //then
        double actual = score.calculate();
        assertThat(actual).isEqualTo(2.0);
    }

    public static class testInitializer implements BoardInitializer {

        @Override
        public Map<Position, Piece> apply() {
            Map<Position, Piece> result = new HashMap<>();
            result.put(Position.of(A, FOUR), Pawn.colorOf(WHITE));
            result.put(Position.of(A, THREE), Pawn.colorOf(WHITE));
            result.put(Position.of(B, THREE), Pawn.colorOf(WHITE));
            result.put(Position.of(A, FIVE), Pawn.colorOf(BLACK));
            return result;
        }
    }
}
