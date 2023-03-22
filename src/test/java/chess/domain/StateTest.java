package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.State;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StateTest {

    @Test
    void 시작_상태를_판단할_수_있다() {
        final State state = State.START;

        assertThat(state.isStart()).isTrue();
    }

    @Test
    void 종료_상태가_아니라면_실행중_상태로_판단할_수_있다() {
        final State state = State.START;

        assertThat(state.isRunnable()).isTrue();
    }
}
