package domain.piece;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import domain.Square;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("폰은")
class PawnTest {

    @Nested
    @DisplayName("화이트의 경우")
    class case_white {

        @Nested
        @DisplayName("시작 상태일때")
        @TestInstance(Lifecycle.PER_CLASS)
        class state_init {

            @DisplayName("앞으로 1~2칸 또는 대각선으로 갈 수 있다.")
            @ParameterizedTest(name = "{index} : {0} => {1}")
            @MethodSource("parametersProvider1")
            void move_success(Square src, Square dest, List<Square> expected) {
                Pawn pawn = new Pawn(TeamColor.WHITE);
                List<Square> actual = pawn.findRoutes(src, dest);

                Assertions.assertThat(actual).isEqualTo(expected);
            }

            Stream<Arguments> parametersProvider1() {
                return Stream.of(
                    arguments(Square.of(3, 2), Square.of(3, 3),
                        List.of(Square.of(3, 3))),

                    arguments(Square.of(3, 2), Square.of(3, 4),
                        List.of(Square.of(3, 3),
                            Square.of(3, 4))),

                    arguments(Square.of(3, 2), Square.of(4, 3),
                        List.of(Square.of(4, 3))),

                    arguments(Square.of(3, 2), Square.of(2, 3),
                        List.of(Square.of(2, 3)))
                );
            }

            @DisplayName("앞으로 1~2칸 또는 대각선외에는 갈 수 없다.")
            @ParameterizedTest(name = "{index} : {0} => {1}")
            @MethodSource("parametersProvider2")
            void move_fail(Square src, Square dest) {
                Pawn pawn = new Pawn(TeamColor.WHITE);
                Assertions.assertThatThrownBy(() -> pawn.findRoutes(src, dest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 목적지로 갈 수 없습니다.");
            }

            Stream<Arguments> parametersProvider2() {
                return Stream.of(
                    arguments(Square.of(3, 2), Square.of(4, 4)),
                    arguments(Square.of(3, 2), Square.of(2, 4)),
                    arguments(Square.of(3, 2), Square.of(3, 5)),
                    arguments(Square.of(3, 2), Square.of(3, 1))
                );
            }
        }

        @Nested
        @DisplayName("진행 상태일때")
        @TestInstance(Lifecycle.PER_CLASS)
        class state_running {

            @DisplayName("앞으로 1칸 또는 대각선으로 갈 수 있다.")
            @ParameterizedTest(name = "{index} : {0} => {1}")
            @MethodSource("parametersProvider1")
            void move_success(Square src, Square dest) {
                Pawn pawn = new Pawn(TeamColor.WHITE);
                List<Square> actual = pawn.findRoutes(src, dest);

                Assertions.assertThat(actual).isEqualTo(List.of(dest));
            }

            Stream<Arguments> parametersProvider1() {
                return Stream.of(
                    arguments(Square.of(3, 2), Square.of(3, 3)),
                    arguments(Square.of(3, 2), Square.of(4, 3)),
                    arguments(Square.of(3, 2), Square.of(2, 3))
                );
            }

            @DisplayName("앞으로 1칸 또는 대각선외에는 갈 수 없다.")
            @ParameterizedTest(name = "{index} : {0} => {1}")
            @MethodSource("parametersProvider2")
            void move_fail(Square src, Square dest) {
                Pawn pawn = new Pawn(TeamColor.WHITE);
                pawn.changeState();

                Assertions.assertThatThrownBy(() -> pawn.findRoutes(src, dest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 목적지로 갈 수 없습니다.");
            }

            Stream<Arguments> parametersProvider2() {
                return Stream.of(
                    arguments(Square.of(3, 2), Square.of(3, 4))
                );
            }
        }
    }

    @Nested
    @DisplayName("블랙의 경우")
    class case_black {

        @Nested
        @DisplayName("시작 상태일때")
        @TestInstance(Lifecycle.PER_CLASS)
        class state_init {

            @DisplayName("아래로 1~2칸 또는 대각선으로 갈 수 있다.")
            @ParameterizedTest(name = "{index} : {0} => {1}")
            @MethodSource("parametersProvider1")
            void move_success(Square src, Square dest, List<Square> expected) {
                Pawn pawn = new Pawn(TeamColor.BLACK);
                List<Square> actual = pawn.findRoutes(src, dest);

                Assertions.assertThat(actual).isEqualTo(expected);
            }

            Stream<Arguments> parametersProvider1() {
                return Stream.of(
                    arguments(Square.of(3, 7), Square.of(3, 6),
                        List.of(Square.of(3, 6))),

                    arguments(Square.of(3, 7), Square.of(3, 5),
                        List.of(Square.of(3, 6),
                            Square.of(3, 5))),

                    arguments(Square.of(3, 7), Square.of(4, 6),
                        List.of(Square.of(4, 6))),

                    arguments(Square.of(3, 7), Square.of(2, 6),
                        List.of(Square.of(2, 6)))
                );
            }

            @DisplayName("아래로 1~2칸 또는 대각선외에는 갈 수 없다.")
            @ParameterizedTest(name = "{index} : {0} => {1}")
            @MethodSource("parametersProvider2")
            void move_fail(Square src, Square dest) {
                Pawn pawn = new Pawn(TeamColor.BLACK);

                Assertions.assertThatThrownBy(() -> pawn.findRoutes(src, dest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 목적지로 갈 수 없습니다.");
            }

            Stream<Arguments> parametersProvider2() {
                return Stream.of(
                    arguments(Square.of(3, 7), Square.of(4, 4)),
                    arguments(Square.of(3, 7), Square.of(4, 8)),
                    arguments(Square.of(3, 7), Square.of(3, 2)),
                    arguments(Square.of(3, 7), Square.of(3, 8))
                );
            }
        }

        @Nested
        @DisplayName("진행 상태일때")
        @TestInstance(Lifecycle.PER_CLASS)
        class state_running {

            @DisplayName("아래로 1칸 또는 대각선으로 갈 수 있다.")
            @ParameterizedTest(name = "{index} : {0} => {1}")
            @MethodSource("parametersProvider1")
            void move_success(Square src, Square dest) {
                Pawn pawn = new Pawn(TeamColor.BLACK);
                List<Square> actual = pawn.findRoutes(src, dest);

                Assertions.assertThat(actual).isEqualTo(List.of(dest));
            }

            Stream<Arguments> parametersProvider1() {
                return Stream.of(
                    arguments(Square.of(3, 7), Square.of(4, 6)),
                    arguments(Square.of(3, 7), Square.of(3, 6)),
                    arguments(Square.of(3, 7), Square.of(2, 6))
                );
            }

            @DisplayName("아래로 1칸 또는 대각선외에는 갈 수 없다.")
            @ParameterizedTest(name = "{index} : {0} => {1}")
            @MethodSource("parametersProvider2")
            void move_fail(Square src, Square dest) {
                Pawn pawn = new Pawn(TeamColor.BLACK);
                pawn.changeState();

                Assertions.assertThatThrownBy(() -> pawn.findRoutes(src, dest))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 목적지로 갈 수 없습니다.");
            }

            Stream<Arguments> parametersProvider2() {
                return Stream.of(
                    arguments(Square.of(3, 7), Square.of(3, 5)
                    ));
            }
        }
    }
}
