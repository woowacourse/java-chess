package chess.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class ChessGameTest {

    @Nested
    class 게임시작 {

        @Test
        void startNewGame메서드를_호출하면_게임을시작한다() {
            //given

            //when
            final Executable executable = ChessGame::createGame;

            //then
            assertDoesNotThrow(executable);
        }
    }
}
