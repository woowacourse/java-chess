package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @DisplayName("a1을 수평 반전 하면 h1이 된다.")
    @Test
    void flip_horizontal_a1_to_h1() {
        //given
        Position a1 = new Position(Column.A, Row.ONE);
        Position expected = new Position(Column.H, Row.ONE);

        //when
        Position actual = a1.flipHorizontally();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("a1을 수직 반전 하면 a8이 된다.")
    @Test
    void flip_horizontal_a1_to_a8() {
        //given
        Position a1 = new Position(Column.A, Row.ONE);
        Position expected = new Position(Column.A, Row.EIGHT);

        //when
        Position actual = a1.flipVertically();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("a1을 대각 반전 하면 h8이 된다.")
    @Test
    void flip_diagonally_a1_to_h8() {
        //given
        Position a1 = new Position(Column.A, Row.ONE);
        Position expected = new Position(Column.H, Row.EIGHT);

        //when
        Position actual = a1.flipDiagonally();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
