package chess.domain.state;

import chess.domain.board.ChessBoardFactory;
import chess.domain.state.command.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("End 은")
class EndTest {

    @Test
    void 실행할_수_없다() {
        // given
        final Initialize initialize = new Initialize(ChessBoardFactory.create());
        final ChessState running = initialize.execute(Command.parse(List.of("start")));
        final ChessState end = running.execute(Command.parse(List.of("end")));

        // when & then
        assertThat(end.executable()).isFalse();
    }

    @Test
    void 실행하면_오류이다() {
        // given
        final Initialize initialize = new Initialize(ChessBoardFactory.create());
        final ChessState running = initialize.execute(Command.parse(List.of("start")));
        final ChessState end = running.execute(Command.parse(List.of("end")));

        // when & then
        assertThatThrownBy(() -> end.execute(Command.parse(List.of("end"))))
                .isInstanceOf(IllegalStateException.class);
    }
}
