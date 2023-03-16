package chess.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ChessGameTest {

    @Test
    void should_게임을시작한다_when_시작명령이전달되면() {
        //given
        String command = "start";

        //when
        final Executable executable = () -> ChessGame.startNewGame(command);

        //then
        assertDoesNotThrow(executable);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"end", "123", "1start", "star t"})
    void should_예외를반환한다_when_잘못된명령이전달되면(String command) {
        //given

        //when
        final ThrowingCallable throwingCallable = () -> ChessGame.startNewGame(command);

        //then
        Assertions.assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 명령어가 올바르지 않습니다.");
    }



}