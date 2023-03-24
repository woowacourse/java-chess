package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ScoreTest {

    @DisplayName("같은 값의 score 객체는 캐싱된다.")
    @Test
    void score_객체_캐싱() {
        Score first = Score.from(1);
        Score second = Score.from(1);

        assertThat(first).isSameAs(second);
    }

    @DisplayName("score 점수를 더한다.")
    @Test
    void 점수_덧셈() {
        Score first = Score.from(1);
        Score second = Score.from(1.5);

        assertThat(first.add(second)).isEqualTo(Score.from(2.5));
    }

    @DisplayName("pawn의 개수에 따라 점수를 감소한다.")
    @Test
    void 점수_감소() {
        Score first = Score.from(10);
        long pawnCount = 2;

        assertThat(first.reducePawnScore(pawnCount)).isEqualTo(Score.from(9));
    }
}