package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScoreTest {

    @Test
    void 점수는_0점_이하는_생성되지_않는다() {
        //given
        final double point = -1;

        //when & then
        assertThatThrownBy(() -> Score.from(point))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("점수는 0점보다 작을 수 없습니다.");
    }

    @Test
    void 점수는_0이상_은_생성된다() {
        //given
        final double point1 = 0;
        final double point2 = 1;

        //when & then
        assertAll(
                () -> assertDoesNotThrow(() -> Score.from(point1)),
                () -> assertDoesNotThrow(() -> Score.from(point2))
        );
    }

    @Test
    void 점수는_다른_점수를_받아_덧셈을_할_수_있다(){
        //given
        final Score score1 = Score.from(1);
        final Score score2 = Score.from(2);

        //when
        final Score sumScore = score1.add(score2);

        //then
        assertThat(sumScore).isEqualTo(Score.from(3));
    }

    @Test
    void 점수는_다른_점수를_받아_뺼샘을_할_수_있다(){
        //given
        final Score score1 = Score.from(1);
        final Score score2 = Score.from(2);

        //when
        final Score subtractScore = score2.subtract(score1);

        //then
        assertThat(subtractScore).isEqualTo(Score.from(1));
    }

    @Test
    void 점수는_비율을_받아_곱을_할_수_있다(){
        //given
        final Score score = Score.from(5);
        final double ratio = 0.5;

        //when
        final Score resultScore = score.multiply(ratio);

        //then
        assertThat(resultScore).isEqualTo(Score.from(2.5));
    }
}
