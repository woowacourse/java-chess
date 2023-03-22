package chess.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("GameStatus 클래스")
public class GameStatusTest {

    @Nested
    @DisplayName("startGame 메서드는")
    class startGame {
        @Nested
        @DisplayName("사용자 입력으로")
        class given_code {
//            @ParameterizedTest(name = "{0}가 주어지면 객체 {1}을 반환한다")
//            @CsvSource({"start,START"})
//            void it_returns_gameStatus(String code, GameStatus gameStatus) {
//                assertThat(GameStatus.startGame(code)).isEqualTo(gameStatus);
//            }

//            @ParameterizedTest(name = "{0}가 주어지면 예외를 던진다")
//            @ValueSource(strings = {"move", "end", "", "  "})
//            void it_throws_exception(String code) {
//                assertThatThrownBy(() -> GameStatus.startGame(code))
//                        .isInstanceOf(IllegalArgumentException.class)
//                        .hasMessage("게임을 시작하기 위해서는 start를 입력하세요");
//            }
        }
    }

    @Nested
    @DisplayName("changeStatus 메서드는")
    class changeStatus {
//        @Test
//        @DisplayName("start가 주어지면 예외를 던진다")
//        void it_throws_exception1() {
//            assertThatThrownBy(() -> GameStatus.changeStatus("start"))
//                    .isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage("게임 도중에는 다시 시작할 수 없습니다.");
//        }

        @ParameterizedTest(name = "{0}가 주어지면 예외를 던진다")
        @ValueSource(strings = {"", "  ", "Move", "enD"})
        void it_throws_exception2(String code) {
            assertThatThrownBy(() -> GameStatus.changeStatus(code))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("잘못된 명령어입니다.");
        }

        @Nested
        @DisplayName("사용자 입력으로")
        class given_code {
            @ParameterizedTest(name = "{0}가 주어지면 객체 {1}을 반환한다")
            @CsvSource({"move,PLAYING", "end,END"})
            void it_returns_gameStatus(String code, GameStatus gameStatus) {
                assertThat(GameStatus.changeStatus(code)).isEqualTo(gameStatus);
            }
        }
    }
}
