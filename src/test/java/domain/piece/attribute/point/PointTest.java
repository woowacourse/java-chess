package domain.piece.attribute.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PointTest {
    @Test
    @DisplayName("File(행) 과 Rank(열) 을 통해 포인트를 만든다.")
    void create_with_file_and_rank() {
        final var sut = new Point(File.E, Rank.ONE);

        assertThat(sut).isEqualTo(new Point(File.E, Rank.ONE));
    }

    @Test
    @DisplayName("파일과 랭크가 포함된 문자열을 통해 포인트를 만든다.")
    void create_point_with_string_that_contain_file_and_rank() {
        final String value = "b2";

        final var sut = Point.from(value);

        assertThat(sut.file()).isEqualTo(File.B);
        assertThat(sut.rank()).isEqualTo(Rank.TWO);
    }

    @ParameterizedTest
    @ValueSource(strings = {"b22", "c", "b3c4", ""})
    @DisplayName("2글자가 아닌 문자열을 통해 포인트를 만들 시 예외를 발생시킨다.")
    void throw_exception_when_string_length_is_not_two(String invalidValue) {
        assertThatThrownBy(() -> Point.from(invalidValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"z3", "i9", "i1", "k0"})
    @DisplayName("파일 문자가 a~h를 벗어나거나 , 랭크 문자가 1~8 을 벗어나면 예외를 발생시킨다.")
    void throw_exception_when_file_or_rank_char_out_of_range(String invalidValue) {
        assertThatThrownBy(() -> Point.from(invalidValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("포인트가 자신의 인덱스를 알려준다.")
    void get_index_by_point() {
        Point point = Point.from("e5");

        final var sut = point.toIndex();

        assertThat(sut).isEqualTo(new Index(4, 4));
    }

    @Test
    @DisplayName("파일 과 랭크의 인덱스를 통해 포인트를 생성한다.")
    void create_with_index() {
        final var index = new Index(3, 5);

        final var sut = Point.fromIndex(index);

        assertThat(sut).isEqualTo(new Point(File.F, Rank.FOUR));
    }

    @Test
    @DisplayName("포인트는 아래로 이동할 수 있다.")
    void point_can_move_to_down() {
        Point sut = new Point(File.A, Rank.TWO);

        assertThat(sut.moveDown(1))
                .isEqualTo(new Point(File.A, Rank.ONE));
    }

    @Test
    @DisplayName("포인트는 위로 이동할 수 있다.")
    void point_can_move_to_up() {
        Point sut = new Point(File.A, Rank.ONE);

        assertThat(sut.moveUp(1))
                .isEqualTo(new Point(File.A, Rank.TWO));
    }

    @Test
    @DisplayName("포인트는 왼쪽으로 이동할 수 있다.")
    void point_can_move_to_left() {
        Point sut = new Point(File.B, Rank.ONE);

        assertThat(sut.moveLeft(1))
                .isEqualTo(new Point(File.A, Rank.ONE));
    }

    @Test
    @DisplayName("포인트는 오른쪽으로 이동할 수 있다.")
    void point_can_move_to_right() {
        Point sut = new Point(File.A, Rank.ONE);

        assertThat(sut.moveRight(1))
                .isEqualTo(new Point(File.B, Rank.ONE));
    }


}
