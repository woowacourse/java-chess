package chess.domain.game.state;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class MovingStateTest {

    private final MovingState movingState = MovingState.getInstance();

    @Test
    void getInstance_를_통해_가져오면_동일한_객체가_반환된다() {
        //given
        MovingState resultState = MovingState.getInstance();

        //when
        boolean result = movingState == resultState;

        //then
        assertTrue(result);
    }

    @Test
    void start_를_실행하면_처음으로_돌아간다() {
        //given
        GameState resultState = movingState.start();

        //when
        boolean result = resultState == StartState.getInstance();

        //then
        assertTrue(result);
    }

    @Test
    void end_를_실행하면_종료된_상태가_반환된다() {
        //given
        GameState resultState = movingState.end();

        //when
        boolean result = resultState == EndState.getInstance();

        //then
        assertTrue(result);
    }

    @Test
    void run을_실행하면_현재_상태가_반환된다() {
        //given
        GameState resultState = movingState.run();

        //when
        boolean result = resultState == movingState;

        //then
        assertTrue(result);
    }
}
