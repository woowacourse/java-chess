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
class ChessGameCommandTest {

    @Test
    void 정상적인_게임_실행명령을_전달받으면_실행상태를_반환한다() {
        final String input = "start";

        final ChessGameCommand chessGameCommand = ChessGameCommand.from(input);

        assertThat(chessGameCommand).isEqualTo(ChessGameCommand.START);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"merry"})
    void 정상적이지_않은_게임_실행명령을_전달받으면_예외를_던진다(final String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ChessGameCommand.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .withMessage("start, end, move 중 입력해야 합니다.");
    }
}
