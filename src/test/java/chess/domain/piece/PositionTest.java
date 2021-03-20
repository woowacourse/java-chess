package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("위치 테스트")
class PositionTest {

    @Test
    @DisplayName("생성자 테스트")
    void create() {
        assertThat(Position.of(0, 0)).isEqualTo(Position.of("a1"));

        assertThat(Position.of(1, 1)).isSameAs(Position.of("b2"));
    }

    @Test
    @DisplayName("이동범위가 1칸일때 이동이 가능한지 테스트")
    void canMoveOneTest() {
        Position position = Position.of("b3");
        Direction direction = Direction.NORTH;
        Position targetPosition = Position.of("b4");
        int ableLength = 1;

        final boolean canMove = position.canMove(targetPosition, direction, ableLength);

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("이동범위가 7칸일때 이동이 가능한지 테스트")
    void canMoveTest() {
        Position position = Position.of("a1");
        Direction direction = Direction.NORTHEAST;
        Position targetPosition = Position.of("h8");
        int ableLength = 7;

        final boolean canMove = position.canMove(targetPosition, direction, ableLength);

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("이동범위가 1칸일때 2칸이상 전진 테스트")
    void cannotMoveTest() {
        Position position = Position.of("b3");
        Direction direction = Direction.NORTH;
        int ableLength = 1;

        Position targetPosition = Position.of("b5");

        final boolean canMove = position.canMove(targetPosition, direction, ableLength);

        assertThat(canMove).isFalse();
    }

    @Test
    @DisplayName("포지션 더하기 테스트")
    void add() {
        Position position = Position.of("a1");

        assertThat(position.add(7, 7)).isEqualTo(Position.of("h8"));
        assertThat(position.add(7, 7)).isSameAs(Position.of("h8"));
    }

    @Test
    @DisplayName("포지션 더하기 실패 테스트")
    void addException() {
        Position position = Position.of("a1");

        assertThatThrownBy(() -> position.add(-1, -1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> position.add(8, 8)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 컬럼인지 확인")
    void isSameColumn() {
        Position position = Position.of("a1");

        assertThat(position.isSameColumn(Point.from(0))).isTrue();

        assertThat(position.isSameColumn(Point.from(7))).isFalse();
    }
}