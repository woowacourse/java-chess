package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ColumnTest {

    @Test
    @DisplayName("Column에 a~h이 포함되는지 확인한다.")
    void contain() {
        assertThat(Column.values())
                .containsOnly(Column.A, Column.B, Column.C, Column.D, Column.E, Column.F, Column.G, Column.H);
    }

    @Test
    @DisplayName("이름을 이용해 Column을 찾는다.")
    void findFileByName() {
        assertThat(Column.of("c")).isEqualTo(Column.C);
    }

    @Test
    @DisplayName("이름을 이용해 Column을 찾는다.")
    void findFileByValue() {
        assertThat(Column.of(5)).isEqualTo(Column.E);
    }

    @Test
    @DisplayName("a~h 이외의 값이 들어오는 경우 예외를 발생시킨다.")
    void exceptionIllegalName() {
        assertThatThrownBy(() -> Column.of("i"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 Column입니다.");
    }

    @Test
    @DisplayName("a~h 이외의 값이 들어오는 경우 예외를 발생시킨다.")
    void exceptionIllegalValue() {
        assertThatThrownBy(() -> Column.of(9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 Column입니다.");
    }

    @Test
    @DisplayName("Column을 1 증가시킨다.")
    void plus() {
        Column file = Column.of("b");
        assertThat(file.move(1)).isEqualTo(Column.C);
    }

    @Test
    @DisplayName("Column을 1 감소시킨다.")
    void minus() {
        Column file = Column.of("b");
        assertThat(file.move(-1)).isEqualTo(Column.A);
    }

    @Test
    @DisplayName("Column을 1 증가 시킬 때, 경계선을 넘어가면 예외를 발생시킨다.")
    void exceptionPlusOutOfBounds() {
        Column file = Column.of("h");

        assertThatThrownBy(() -> file.move(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] Column을 해당 거리만큼 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("File을 1 감소 시킬 때, 경계선을 넘어가면 예외를 발생시킨다.")
    void exceptionMinusOutOfBounds() {
        Column file = Column.of("b");

        assertThatThrownBy(() -> file.move(-2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] Column을 해당 거리만큼 이동시킬 수 없습니다.");
    }
}
