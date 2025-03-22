package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.Fixtures.A1;
import static chess.Fixtures.A2;
import static chess.Fixtures.A3;
import static chess.Fixtures.A6;
import static chess.Fixtures.A7;
import static chess.Fixtures.A8;
import static chess.Fixtures.B1;
import static chess.Fixtures.B2;
import static chess.Fixtures.B3;
import static chess.Fixtures.B7;
import static chess.Fixtures.B8;
import static chess.Fixtures.C1;
import static chess.Fixtures.F1;
import static chess.Fixtures.F8;
import static chess.Fixtures.G1;
import static chess.Fixtures.G2;
import static chess.Fixtures.G6;
import static chess.Fixtures.G7;
import static chess.Fixtures.G8;
import static chess.Fixtures.H1;
import static chess.Fixtures.H2;
import static chess.Fixtures.H7;
import static chess.Fixtures.H8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("위치")
class PositionTest {


    @DisplayName("A1은 맨 왼쪽이다.")
    @Test
    void nonFarLeft_A1() {
        assertThat(A1.isFarLeft()).isTrue();
    }

    @DisplayName("B1은 맨 왼쪽이 아니다.")
    @Test
    void nonFarLeft_B1() {
        assertThat(B1.isFarLeft()).isFalse();
    }

    @DisplayName("H1은 맨 오른쪽이다.")
    @Test
    void nonFarRight_H1() {
        assertThat(H1.isFarRight()).isTrue();
    }

    @DisplayName("F1은 맨 오른쪽이 아니다.")
    @Test
    void nonFarRight_F1() {
        assertThat(F1.isFarRight()).isFalse();
    }

    @DisplayName("A1은 왼쪽으로 이동할 수 없다.")
    @Test
    void canMoveLeft_A1() {
        assertThat(A1.canMoveLeft()).isFalse();
    }

    @DisplayName("A1은 왼쪽으로 이동할 수 없다.")
    @Test
    void canMoveLeft_Movement_A1() {
        assertThat(A1.canMove(Movement.LEFT)).isFalse();
    }

    @DisplayName("B1은 왼쪽으로 이동할 수 있다.")
    @Test
    void canMoveLeft_B1() {
        assertThat(B1.canMoveLeft()).isTrue();
    }

    @DisplayName("A1은 왼쪽으로 이동하면 예외가 발생한다.")
    @Test
    void moveLeft_A1() {
        assertThatThrownBy(A1::moveLeft)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("B1은 왼쪽으로 이동하면 A1이다.")
    @Test
    void moveLeft_B1() {
        final var moved = B1.moveLeft();

        assertThat(moved).isEqualTo(A1);
    }

    @DisplayName("B1은 왼쪽으로 2번 이동할 수 없다.")
    @Test
    void canMoveLeft_2_B1() {
        assertThat(B1.canMoveLeft(2)).isFalse();
    }

    @DisplayName("B1은 왼쪽으로 2번 이동하면 예외가 발생한다.")
    @Test
    void moveLeft_2_B1() {
        assertThatThrownBy(() -> B1.moveLeft(2))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("C1은 왼쪽으로 2번 이동하면 A1이다.")
    @Test
    void moveLeft_C2() {
        final var moved = C1.moveLeft(2);

        assertThat(moved).isEqualTo(A1);
    }

    @DisplayName("H1은 오른쪽으로 이동할 수 없다.")
    @Test
    void canMoveRight_H1() {
        assertThat(H1.canMoveRight()).isFalse();
    }

    @DisplayName("H1은 오른쪽으로 이동할 수 없다.")
    @Test
    void canMoveRight_Movement_H1() {
        assertThat(H1.canMove(Movement.RIGHT)).isFalse();
    }

    @DisplayName("H1은 오른쪽으로 이동하면 예외가 발생한다.")
    @Test
    void moveRight_H1() {
        assertThatThrownBy(H1::moveRight)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("G1은 오른쪽으로 이동하면 H1다.")
    @Test
    void moveRight_G1() {
        final var moved = G1.moveRight();

        assertThat(moved).isEqualTo(H1);
    }

    @DisplayName("G1은 오른쪽으로 2번 이동할 수 없다.")
    @Test
    void canMoveRight_2_G1() {
        assertThat(G1.canMoveRight(2)).isFalse();
    }

    @DisplayName("G1은 오른쪽으로 2번 이동하면 예외가 발생한다.")
    @Test
    void moveRight_2_G1() {
        assertThatThrownBy(() -> G1.moveRight(2))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("F1은 오른쪽으로 2번 이동하면 H1이다.")
    @Test
    void moveRight_2_F1() {
        final var moved = F1.moveRight(2);

        assertThat(moved).isEqualTo(H1);
    }

    /////

    @DisplayName("A8은 맨 위다.")
    @Test
    void nonTop_A8() {
        assertThat(A8.isTop()).isTrue();
    }

    @DisplayName("A7은 맨 위가 아니다.")
    @Test
    void nonTop_7() {
        assertThat(A7.isTop()).isFalse();
    }

    @DisplayName("A1은 맨 아래다.")
    @Test
    void nonBottom_A1() {
        assertThat(A1.isBottom()).isTrue();
    }

    @DisplayName("A2는 맨 아래가 아니다.")
    @Test
    void nonBottom_A2() {
        assertThat(A2.isBottom()).isFalse();
    }

    @DisplayName("A8은 위로 이동할 수 없다.")
    @Test
    void canMoveUp_A8() {
        assertThat(A8.canMoveUp()).isFalse();
    }

    @DisplayName("A8은 위로 이동하면 예외가 발생한다.")
    @Test
    void moveUp_A8() {
        assertThatThrownBy(A8::moveUp)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("A7은 위로 이동하면 A8이다.")
    @Test
    void moveUp_A7() {
        final var moved = A7.moveUp();

        assertThat(moved).isEqualTo(A8);
    }

    @DisplayName("A7은 위로 이동하면 A8이다.")
    @Test
    void moveUp_Movement_A7() {
        final var moved = A7.move(Movement.UP);

        assertThat(moved).isEqualTo(A8);
    }

    @DisplayName("A7은 위로 2번 이동할 수 없다.")
    @Test
    void canMoveUp_2_A7() {
        assertThat(A7.canMoveUp(2)).isFalse();
    }

    @DisplayName("A7은 위로 2번 이동하면 예외가 발생한다.")
    @Test
    void moveUp_2_A7() {
        assertThatThrownBy(() -> A7.moveUp(2))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("A6은 위로 2번 이동하면 A8이다.")
    @Test
    void moveUp_2_A6() {
        final var moved = A6.moveUp(2);

        assertThat(moved).isEqualTo(A8);
    }

    @DisplayName("A1은 아래로 이동할 수 없다.")
    @Test
    void canMoveDown_A1() {
        assertThat(A1.canMoveDown()).isFalse();
    }

    @DisplayName("A1은 아래로 이동하면 예외가 발생한다.")
    @Test
    void moveDown_A1() {
        assertThatThrownBy(A1::moveDown)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("A2는 아래로 이동하면 A1이다.")
    @Test
    void moveDown_A2() {
        final var moved = A2.moveDown();

        assertThat(moved).isEqualTo(A1);
    }

    @DisplayName("A2는 아래로 2번 이동할 수 없다.")
    @Test
    void canMoveDown_2_A2() {
        assertThat(A2.canMoveDown(2)).isFalse();
    }

    @DisplayName("A2는 아래로 2번 이동하면 예외가 발생한다.")
    @Test
    void moveDown_2_A2() {
        assertThatThrownBy(() -> A2.moveDown(2))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("A3는 아래로 2번 이동하면 A1이다.")
    @Test
    void moveDown_2_A3() {
        final var moved = A3.moveDown(2);

        assertThat(moved).isEqualTo(A1);
    }

    @DisplayName("왼쪽 위 대각선으로 움직이면 예외가 발생한다.")
    @MethodSource("immovableLeftUpSource")
    @ParameterizedTest(name = "{0}은 왼쪽 위 대각선으로 움직이면 예외가 발생한다.")
    void moveLeftUp_immovable(final Position position) {
        assertThatThrownBy(position::moveLeftUp)
                .isInstanceOf(IllegalStateException.class);
    }

    static Stream<Position> immovableLeftUpSource() {
        final var canMoveLeftPositions = Stream.of(A1, A2, A7, A8);
        final var canMoveUpPositions = Stream.of(A8, B8, F8, H8);

        return Stream.concat(canMoveLeftPositions, canMoveUpPositions);
    }

    @DisplayName("왼쪽 위 대각선으로 움직인다.")
    @MethodSource("movableLeftUpSource")
    @ParameterizedTest(name = "{0}을 왼쪽 위 대각선으로 움직인다.")
    void moveLeftUp_movable(final Position position) {
        assertThatCode(position::moveLeftUp)
                .doesNotThrowAnyException();
    }

    static Stream<Position> movableLeftUpSource() {
        return Stream.of(B2, B3, B7, G1, G2, G6, G7);
    }

    @DisplayName("오른쪽 위 대각선으로 움직이면 예외가 발생한다.")
    @MethodSource("immovableRightUpSource")
    @ParameterizedTest(name = "{0}은 오른쪽 위 대각선으로 움직이면 예외가 발생한다.")
    void moveRightUp_immovable(final Position position) {
        assertThatThrownBy(position::moveRightUp)
                .isInstanceOf(IllegalStateException.class);
    }

    static Stream<Position> immovableRightUpSource() {
        final var canMoveRightPositions = Stream.of(H1, H2, H7, H8);
        final var canMoveUpPositions = Stream.of(A8, B8, F8, H8);

        return Stream.concat(canMoveRightPositions, canMoveUpPositions);
    }

    @DisplayName("오른쪽 위 대각선으로 움직인다.")
    @MethodSource("movableRightUpSource")
    @ParameterizedTest(name = "{0}을 왼쪽 위 대각선으로 움직인다.")
    void moveRightUp_movable(final Position position) {
        assertThatCode(position::moveRightUp)
                .doesNotThrowAnyException();
    }

    static Stream<Position> movableRightUpSource() {
        return Stream.of(B1, B2, B3, B7, A2, A6, A7, G1, G2, G7);
    }

    @DisplayName("왼쪽 아래 대각선으로 움직이면 예외가 발생한다.")
    @MethodSource("immovableLeftDownSource")
    @ParameterizedTest(name = "{0}은 왼쪽 아래 대각선으로 움직이면 예외가 발생한다.")
    void moveLeftDown_immovable(final Position position) {
        assertThatThrownBy(position::moveLeftDown)
                .isInstanceOf(IllegalStateException.class);
    }

    static Stream<Position> immovableLeftDownSource() {
        final var canMoveLeftPositions = Stream.of(A1, A2, A7, A8);
        final var canMoveDownPositions = Stream.of(A1, B1, F1, H1);

        return Stream.concat(canMoveLeftPositions, canMoveDownPositions);
    }

    @DisplayName("왼쪽 아래 대각선으로 움직인다.")
    @MethodSource("movableLeftDownSource")
    @ParameterizedTest(name = "{0}을 왼쪽 아래 대각선으로 움직인다.")
    void moveLeftDown_movable(final Position position) {
        assertThatCode(position::moveLeftDown)
                .doesNotThrowAnyException();
    }

    static Stream<Position> movableLeftDownSource() {
        return Stream.of(B2, B3, B7, G8, G2, G6, G7);
    }

    @DisplayName("오른쪽 아래 대각선으로 움직이면 예외가 발생한다.")
    @MethodSource("immovableRightDownSource")
    @ParameterizedTest(name = "{0}은 오른쪽 아래 대각선으로 움직이면 예외가 발생한다.")
    void moveRightDown_immovable(final Position position) {
        assertThatThrownBy(position::moveRightDown)
                .isInstanceOf(IllegalStateException.class);
    }

    static Stream<Position> immovableRightDownSource() {
        final var canMoveRightPositions = Stream.of(H1, H2, H7, H8);
        final var canMoveDownPositions = Stream.of(A1, B1, F1, H1);

        return Stream.concat(canMoveRightPositions, canMoveDownPositions);
    }

    @DisplayName("오른쪽 아래 대각선으로 움직인다.")
    @MethodSource("movableRightDownSource")
    @ParameterizedTest(name = "{0}을 왼쪽 아래 대각선으로 움직인다.")
    void moveRightDown_movable(final Position position) {
        assertThatCode(position::moveRightDown)
                .doesNotThrowAnyException();
    }

    static Stream<Position> movableRightDownSource() {
        return Stream.of(B8, B2, B3, B7, A2, A6, A7, G8, G2, G7);
    }
}
