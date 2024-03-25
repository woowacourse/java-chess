package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Movement;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhitePawnTest {
    @DisplayName("흰색 폰은 1칸 움직일 수 있다.")
    @Test
    void whitePawnIsMovable() {
        assertThat(new WhitePawn().move()
                .isMovable(new Movement(
                        Position.of(3, 1),
                        Position.of(4, 1))))
                .isTrue();
    }

    @DisplayName("흰색 폰은 1칸 초과하여 움직일 수 없다.")
    @Test
    void whitePawnMoveIsNotMovableOverTwo() {
        assertThat(new WhitePawn().move()
                .isMovable(new Movement(
                        Position.of(3, 1),
                        Position.of(5, 1))))
                .isFalse();
    }

    @DisplayName("흰색 폰은 시작 지점에 있는 경우, 2칸 움직일 수 있다.")
    @Test
    void whitePawnIsMovableTwo() {
        assertThat(new WhitePawn()
                .isMovable(new Movement(
                        Position.of(2, 1),
                        Position.of(4, 1))))
                .isTrue();
    }

    @DisplayName("흰색 폰은 시작 지점에 있는 경우, 2칸 초과시 예외가 발생한다.")
    @Test
    void startWhitePawnIsNotMovableOverTwo() {
        assertThat(new WhitePawn()
                .isMovable(new Movement(
                        Position.of(2, 1),
                        Position.of(5, 1))))
                .isFalse();
    }

    @DisplayName("폰은 옆으로 이동할 수 없다.")
    @Test
    void whitePawnIsNotMovableColumn() {
        assertThat(new WhitePawn()
                .isMovable(new Movement(
                        Position.of(7, 1),
                        Position.of(7, 2))))
                .isFalse();
    }

    @DisplayName("두 위치 사이의 폰이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPosition() {
        assertThat(new WhitePawn()
                .findBetweenPositions(new Movement(
                        Position.of(2, 3),
                        Position.of(4, 3))))
                .containsExactly(Position.of(3, 3));
    }

    @DisplayName("두 위치 사이의 폰이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionHasMoved() {
        assertThat(new WhitePawn().move()
                .findBetweenPositions(new Movement(
                        Position.of(3, 3),
                        Position.of(4, 3))))
                .isEmpty();
    }

    @DisplayName("공격할 때, 대각으로 움직일 수 있다.")
    @Test
    void movableDiagonalWhenAttack() {
        assertThat(new WhitePawn().isMovable(new Movement(
                        Position.of(2, 2),
                        Position.of(3, 3))
                , true))
                .isTrue();
    }

    @DisplayName("공격할 때, 직선으로 움직일 수 없다.")
    @Test
    void cannotMoveStraightWhenAttack() {
        assertThat(new WhitePawn().isMovable(new Movement(
                        Position.of(2, 2), Position.of(3, 2)),
                true))
                .isFalse();
    }
}
