package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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

}
