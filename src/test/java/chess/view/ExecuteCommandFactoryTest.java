package chess.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.controller.command.EndCommand;
import chess.controller.command.MoveCommand;
import chess.controller.command.StartCommand;
import chess.controller.command.StatusCommand;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ExecuteCommandFactoryTest {

    @Nested
    class getInstance_메서드는 {

        @Test
        void 시작_명령이라면_시작_커멘드를_반환한다() {
            final String input = "start";

            assertThat(ExecuteCommandFactory.getInstance(input)).isInstanceOf(StartCommand.class);
        }

        @Test
        void 상태_명령이라면_상태_커멘드를_반환한다() {
            final String input = "status";

            assertThat(ExecuteCommandFactory.getInstance(input)).isInstanceOf(StatusCommand.class);
        }

        @Test
        void 동작_명령이라면_동작_커멘드를_반환한다() {
            final String input = "move b2 b3";

            assertThat(ExecuteCommandFactory.getInstance(input)).isInstanceOf(MoveCommand.class);
        }

        @Test
        void 종료_명령이라면_종료_커멘드를_반환한다() {
            final String input = "end";

            assertThat(ExecuteCommandFactory.getInstance(input)).isInstanceOf(EndCommand.class);
        }

        @Test
        void 유효하지_않은_명령이라면_예외를_던진다() {
            final String input = "move b2 b9";

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> ExecuteCommandFactory.getInstance(input))
                    .withMessage("유효하지 않은 게임 명령입니다.");
        }
    }
}
