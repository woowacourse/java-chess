package chess.domain.attribute;

import static chess.domain.attribute.File.A;
import static chess.domain.attribute.File.B;
import static chess.domain.attribute.File.C;
import static chess.domain.attribute.File.D;
import static chess.domain.attribute.File.E;
import static chess.domain.attribute.File.F;
import static chess.domain.attribute.File.G;
import static chess.domain.attribute.File.H;
import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.FIVE;
import static chess.domain.attribute.Rank.FOUR;
import static chess.domain.attribute.Rank.ONE;
import static chess.domain.attribute.Rank.SEVEN;
import static chess.domain.attribute.Rank.SIX;
import static chess.domain.attribute.Rank.THREE;
import static chess.domain.attribute.Rank.TWO;
import static chess.domain.chessboard.attribute.Direction.DOWN;
import static chess.domain.chessboard.attribute.Direction.DOWN_LEFT;
import static chess.domain.chessboard.attribute.Direction.DOWN_RIGHT;
import static chess.domain.chessboard.attribute.Direction.LEFT;
import static chess.domain.chessboard.attribute.Direction.RIGHT;
import static chess.domain.chessboard.attribute.Direction.UP;
import static chess.domain.chessboard.attribute.Direction.UP_LEFT;
import static chess.domain.chessboard.attribute.Direction.UP_RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SquareTest {

    private static Stream<Arguments> stringConstructor() {
        return Stream.of(
                Arguments.of("a1", Square.of(A, ONE)),
                Arguments.of("b2", Square.of(B, TWO)),
                Arguments.of("c3", Square.of(C, THREE)),
                Arguments.of("d4", Square.of(D, FOUR)),
                Arguments.of("e5", Square.of(E, FIVE)),
                Arguments.of("f6", Square.of(F, SIX)),
                Arguments.of("g7", Square.of(G, SEVEN)),
                Arguments.of("h8", Square.of(H, EIGHT))
        );
    }

    @DisplayName("좌표를 입력받아 체스판의 칸을 생성한다.")
    @MethodSource
    @ParameterizedTest
    void stringConstructor(String input, Square expected) {
        assertThat(Square.of(input)).isEqualTo(expected);
    }

    private static Stream<Arguments> move() {
        return Stream.of(
                Arguments.of(UP, Square.of(D, FIVE)),
                Arguments.of(UP_LEFT, Square.of(C, FIVE)),
                Arguments.of(UP_RIGHT, Square.of(E, FIVE)),
                Arguments.of(DOWN, Square.of(D, THREE)),
                Arguments.of(DOWN_LEFT, Square.of(C, THREE)),
                Arguments.of(DOWN_RIGHT, Square.of(E, THREE)),
                Arguments.of(LEFT, Square.of(C, FOUR)),
                Arguments.of(RIGHT, Square.of(E, FOUR))
        );
    }

    @DisplayName("현재위치에서 방향을 입력받아 다음 위치를 반환한다.")
    @ParameterizedTest
    @MethodSource
    void move(Direction direction, Square next) {
        Square current = Square.of(D, FOUR);
        Square actual = current.move(direction).get();
        assertThat(actual).isEqualTo(next);
    }

    private static Stream<Arguments> moveException() {
        return Stream.of(
                Arguments.of(Square.of(D, EIGHT), UP),
                Arguments.of(Square.of(A, EIGHT), UP_LEFT),
                Arguments.of(Square.of(H, EIGHT), UP_RIGHT),
                Arguments.of(Square.of(D, ONE), DOWN),
                Arguments.of(Square.of(A, ONE), DOWN_LEFT),
                Arguments.of(Square.of(H, ONE), DOWN_RIGHT),
                Arguments.of(Square.of(A, FOUR), LEFT),
                Arguments.of(Square.of(H, FOUR), RIGHT)
        );
    }

    @DisplayName("이동할 수 없는 칸으로 이동하면 빈 값을 반환한다.")
    @ParameterizedTest
    @MethodSource
    void moveException(Square current, Direction direction) {
        Optional<Square> square = current.move(direction);
        assertThat(square).isEmpty();
    }

    @DisplayName("현재 위치에서 위로 이동한다.")
    @Test
    void moveUp() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveUp()).isEqualTo(Square.of(F, THREE));
    }

    @DisplayName("현재 위치에서 아래로 이동한다.")
    @Test
    void moveDown() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveDown()).isEqualTo(Square.of(F, ONE));
    }

    @DisplayName("현재 위치에서 왼쪽으로 이동한다.")
    @Test
    void moveLeft() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveLeft()).isEqualTo(Square.of(E, TWO));
    }

    @DisplayName("현재 위치에서 오른쪽으로 이동한다.")
    @Test
    void moveRight() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveRight()).isEqualTo(Square.of(G, TWO));
    }

    @DisplayName("현재 위치에서 왼쪽_위로 이동한다.")
    @Test
    void moveLeftUp() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveLeftUp()).isEqualTo(Square.of(E, THREE));
    }

    @DisplayName("현재 위치에서 왼쪽_아래로 이동한다.")
    @Test
    void moveLeftDown() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveLeftDown()).isEqualTo(Square.of(E, ONE));
    }

    @DisplayName("현재 위치에서 오른쪽_위로 이동한다.")
    @Test
    void moveRightUp() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveRightUp()).isEqualTo(Square.of(G, THREE));
    }

    @DisplayName("현재 위치에서 오른쪽_아래로 이동한다.")
    @Test
    void moveRightDown() {
        Square square = Square.of(F, TWO);

        Assertions.assertThat(square.moveRightDown()).isEqualTo(Square.of(G, ONE));
    }

    @DisplayName("위로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveUp() {
        assertAll(
                () -> assertTrue(Square.of(F, ONE)::canMoveUp),
                () -> assertFalse(Square.of(F, EIGHT)::canMoveUp)
        );
    }

    @DisplayName("아래로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveDown() {
        assertAll(
                () -> assertTrue(Square.of(F, EIGHT)::canMoveDown),
                () -> assertFalse(Square.of(F, ONE)::canMoveDown)
        );
    }

    @DisplayName("왼쪽으로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveLeft() {
        assertAll(
                () -> assertTrue(Square.of(H, TWO)::canMoveLeft),
                () -> assertFalse(Square.of(A, TWO)::canMoveLeft)
        );
    }

    @DisplayName("오른쪽으로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveRight() {
        assertAll(
                () -> assertTrue(Square.of(A, TWO)::canMoveRight),
                () -> assertFalse(Square.of(H, TWO)::canMoveRight)
        );
    }

    @DisplayName("왼쪽_아래로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveLeftDown() {
        assertAll(
                () -> assertTrue(Square.of(B, TWO)::canMoveLeftDown),
                () -> assertFalse(Square.of(A, ONE)::canMoveLeftDown)
        );
    }

    @DisplayName("왼쪽_위로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveLeftUp() {
        assertAll(
                () -> assertTrue(Square.of(B, SEVEN)::canMoveLeftUp),
                () -> assertFalse(Square.of(A, EIGHT)::canMoveLeftUp)
        );
    }

    @DisplayName("오른쪽_아래로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveRightDown() {
        assertAll(
                () -> assertTrue(Square.of(F, TWO)::canMoveRightDown),
                () -> assertFalse(Square.of(H, ONE)::canMoveRightDown)
        );
    }

    @DisplayName("오른쪽_위로 이동할 수 있는지 반환한다.")
    @Test
    void canMoveRightUp() {
        assertAll(
                () -> assertTrue(Square.of(G, SEVEN)::canMoveRightUp),
                () -> assertFalse(Square.of(H, EIGHT)::canMoveRightUp)
        );
    }
}
