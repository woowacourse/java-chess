package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ChessGameTest {

    @Nested
    class 게임시작 {
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

    @Nested
    class 게임진행 {

        ChessGame chessGame;

        @BeforeEach
        void setup() {
            chessGame = ChessGame.startNewGame("start");
        }

        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = {"end move move", "move h9 d3", "move h4 a5 d2", "move h2", "move", "sjfldjfldj"
                , "move a b", "move a33 a9", "move  c2    c4"})
        void should_예외를_던진다_when_올바르지_않은_명령이_전달되었을때(String input) {
            //given
            List<String> commands = Arrays.stream(input.split(" "))
                    .collect(Collectors.toUnmodifiableList());
            //when
            final ThrowingCallable throwingCallable = () -> chessGame.executeCommand(commands);

            //then
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}