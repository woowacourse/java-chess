package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ExecuteStateTest {

    @Test
    void 정상적인_게임_실행명령을_전달받으면_실행상태를_반환한다() {
        final String input = "start";

        final ExecuteState executeState = ExecuteState.from(input);

        assertThat(executeState).isEqualTo(ExecuteState.START);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"merry"})
    void 정상적이지_않은_게임_실행명령을_전달받으면_예외를_던진다(final String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ExecuteState.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .withMessage("유효하지 않은 실행 명령입니다.");
    }
}
