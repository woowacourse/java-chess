package chess.domain.state;

import chess.domain.board.ChessBoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.state.command.Command.parse;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Initialize 은")
class InitializeTest {

    @Test
    void 실행_가능하다() {
        // given
        final Initialize initialize = new Initialize(ChessBoardFactory.create());

        // when & then
        assertThat(initialize.runnable()).isTrue();
    }

    @Test
    void Start_커맨드가_아니면_오류가_발생한다() {
        // given
        final Initialize initialize = new Initialize(ChessBoardFactory.create());

        // when & then
        assertThatThrownBy(() -> initialize.command(parse(of("end"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Start_커맨드를_받으면_Running_상태로_넘어간다() {
        // given
        final Initialize initialize = new Initialize(ChessBoardFactory.create());

        // when
        final ChessState state = initialize.command(parse(of("start")));

        // then
        assertThat(state).isInstanceOf(Running.class);
    }
}
