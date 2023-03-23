package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.game.exception.ChessGameException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class EndStateTest {

    private final EndState endState = EndState.getInstance();

    @Test
    void getInstance_를_통해_가져오면_같은_객체를_가져온다() {
        //given
        EndState resultState = EndState.getInstance();

        //when
        boolean result = endState == resultState;

        //then
        assertTrue(result);
    }

    @Test
    void start_를_시도하면_처음_상태로_돌아간다() {
        //given
        GameState resultState = endState.start();

        //when
        boolean result = resultState == StartState.getInstance();

        //then
        assertTrue(result);
    }

    @Test
    void end_를_시도하면_예외가_발생한다() {
        //expect
        assertThatThrownBy(endState::end)
                .isInstanceOf(ChessGameException.class)
                .hasMessage("게임이 종료되었습니다.");
    }

    @Test
    void run_을_시도하면_예외가_발생한다() {
        //expect
        assertThatThrownBy(endState::run)
                .isInstanceOf(ChessGameException.class)
                .hasMessage("게임이 종료되었습니다.");
    }
}
