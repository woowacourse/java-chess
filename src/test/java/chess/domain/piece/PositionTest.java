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
    @DisplayName("생성유효 테스트")
    void validate() {
        assertThatThrownBy(() -> Position.of("a")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Position.of("h888")).isInstanceOf(IllegalArgumentException.class);
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
        Direction direction = Direction.NORTH_EAST;
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

        assertThat(position.addedPosition(7, 7)).isEqualTo(Position.of("h8"));
        assertThat(position.addedPosition(7, 7)).isSameAs(Position.of("h8"));
    }

    @Test
    @DisplayName("포지션 더하기 실패 테스트")
    void addException() {
        Position position = Position.of("a1");

        assertThatThrownBy(() -> position.addedPosition(-1, -1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> position.addedPosition(8, 8)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 column 인지 확인")
    void isSameColumn() {
        Position position = Position.of("a1");

        assertThat(position.isSameColumn(Point.from(0))).isTrue();

        assertThat(position.isSameColumn(Point.from(7))).isFalse();
    }

    @Test
    @DisplayName("같은 row 인지 확인")
    void isSameRow() {
        Position position = Position.of("b2");

        assertThat(position.isSameRow(Point.from(1))).isTrue();

        assertThat(position.isSameColumn(Point.from(0))).isFalse();
    }

    @Test
    @DisplayName("비교 테스트")
    void equalsHash() {
        assertThat(Position.of("a1").equals(Position.of(0, 0))).isTrue();

        assertThat(Position.of("a1").equals(Position.of(0, 1))).isFalse();

        assertThat(Position.of("a3").equals(Point.from(1))).isFalse();

        assertThat(Position.of("a2").hashCode()).isEqualTo(Position.of(1, 0).hashCode());
    }

    @Test
    @DisplayName("포지션위치를 문자로 반환하는 테스트")
    void name() {
        assertThat(Position.of("a1").changePositionToString()).isEqualTo("a1");
        assertThat(Position.of("h8").changePositionToString()).isEqualTo("h8");
    }
}