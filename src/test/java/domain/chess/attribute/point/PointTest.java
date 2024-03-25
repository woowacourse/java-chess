package domain.chess.attribute.point;

import domain.chess.File;
import domain.chess.Point;
import domain.chess.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static fixture.PointFixture.*;
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
    void throw_exception_when_string_length_is_not_two(final String invalidValue) {
        assertThatThrownBy(() -> Point.from(invalidValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"z3", "i9", "i1", "k0"})
    @DisplayName("파일 문자가 a~h를 벗어나거나 , 랭크 문자가 1~8 을 벗어나면 예외를 발생시킨다.")
    void throw_exception_when_file_or_rank_char_out_of_range(final String invalidValue) {
        assertThatThrownBy(() -> Point.from(invalidValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("왼쪽으로 포인트를 이동한다.")
    void move_point_left() {
        final var point = C4;

        final var sut = point.moveLeft();

        assertThat(sut).isEqualTo(B4);
    }

    @Test
    @DisplayName("포인트가 왼쪽으로 이동 가능하면 참을 반환한다")
    void true_if_point_can_move_left() {
        final var point = C4;

        final var sut = point.canMoveLeft();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 왼쪽으로 이동 불가능하면 거짓을 반환한다")
    void false_if_point_can_not_move_left() {
        final var point = C4;

        final var sut = point.canMoveLeft(3);

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("오른쪽으로 포인트를 이동한다.")
    void move_point_right() {
        final var point = C4;

        final var sut = point.moveRight();

        assertThat(sut).isEqualTo(D4);
    }

    @Test
    @DisplayName("포인트가 오른쪽으로 이동 가능하면 참을 반환한다")
    void true_if_point_can_move_right() {
        final var point = C4;

        final var sut = point.canMoveRight();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 오른쪽으로 이동 불가능하면 거짓을 반환한다")
    void false_if_point_can_not_move_right() {
        final var point = C4;

        final var sut = point.canMoveRight(7);

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("위로 포인트를 이동한다.")
    void move_point_up() {
        final var point = C4;

        final var sut = point.moveUp();

        assertThat(sut).isEqualTo(C5);
    }

    @Test
    @DisplayName("포인트가 위로 이동 가능하면 참을 반환한다")
    void true_if_point_can_move_up() {
        final var point = C4;

        final var sut = point.canMoveUp();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 위로 이동 불가능하면 거짓을 반환한다")
    void false_if_point_can_not_move_up() {
        final var point = C4;

        final var sut = point.canMoveUp(5);

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("아래로 포인트를 이동한다.")
    void move_point_down() {
        final var point = C4;

        final var sut = point.moveDown();

        assertThat(sut).isEqualTo(C3);
    }

    @Test
    @DisplayName("포인트가 아래로 이동 가능하면 참을 반환한다")
    void true_if_point_can_move_down() {
        final var point = C4;

        final var sut = point.canMoveDown();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 아래로 이동 불가능하면 거짓을 반환한다")
    void false_if_point_can_not_move_down() {
        final var point = C1;

        final var sut = point.canMoveDown(1);

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("왼쪽 위로 포인트를 이동한다.")
    void move_point_up_left() {
        final var point = C4;

        final var sut = point.moveUpLeft();

        assertThat(sut).isEqualTo(B5);
    }

    @Test
    @DisplayName("포인트가 왼쪽 위로 이동 가능하면 참을 반환한다")
    void true_if_point_can_move_up_left() {
        final var point = D4;

        final var sut = point.canMoveUpLeft();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 왼쪽 위로 이동 불가능하면 거짓을 반환한다")
    void false_if_point_can_not_move_up_left() {
        final var point = A1;

        final var sut = point.canMoveUpLeft(1);

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("오른쪽 위로 포인트를 이동한다.")
    void move_point_up_right() {
        final var point = C4;

        final var sut = point.moveUpRight();

        assertThat(sut).isEqualTo(D5);
    }

    @Test
    @DisplayName("포인트가 오른쪽 위로 이동 가능하면 참을 반환한다")
    void true_if_point_can_move_up_right() {
        final var point = D4;

        final var sut = point.canMoveUpRight();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 오른쪽 위로 이동 불가능하면 거짓을 반환한다")
    void false_if_point_can_not_move_up_right() {
        final var point = H1;

        final var sut = point.canMoveUpRight(1);

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("왼쪽 아래로 포인트를 이동한다.")
    void move_point_down_left() {
        final var point = C4;

        final var sut = point.moveDownLeft();

        assertThat(sut).isEqualTo(B3);
    }

    @Test
    @DisplayName("포인트가 왼쪽 아래로 이동 가능하면 참을 반환한다")
    void true_if_point_can_move_down_left() {
        final var point = D4;

        final var sut = point.canMoveDownLeft();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 왼쪽 아래로 이동 불가능하면 거짓을 반환한다")
    void false_if_point_can_not_move_down_left() {
        final var point = new Point(File.D, Rank.FOUR);

        final var sut = point.canMoveDownLeft(4);

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("오른쪽 아래로 포인트를 이동한다.")
    void move_point_down_right() {
        final var point = C4;

        final var sut = point.moveDownRight();

        assertThat(sut).isEqualTo(D3);
    }

    @Test
    @DisplayName("포인트가 오른쪽 아래로 이동 가능하면 참을 반환한다")
    void true_if_point_can_move_down_right() {
        final var point = D4;

        final var sut = point.canMoveDownRight();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 오른쪽 아래로 이동 불가능하면 거짓을 반환한다")
    void false_if_point_can_not_move_down_right() {
        final var point = G4;

        final var sut = point.canMoveDownRight(2);

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("위-왼쪽위로 포인트를 이동한다.")
    void move_point_up_and_up_left() {
        final var point = G4;

        final var sut = point.moveUpUpLeft();

        assertThat(sut).isEqualTo(F6);
    }

    @Test
    @DisplayName("포인트가 위-왼쪽위로 이동 가능하면 참을 반환한다.")
    void true_if_point_can_move_up_and_up_left() {
        final var point = G4;

        final var sut = point.canMoveUpUpLeft();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 위-왼쪽위로 이동 불가능하면 거짓을 반환한다.")
    void false_if_point_can_move_up_and_up_left() {
        final var point = G7;

        final var sut = point.canMoveUpUpLeft();

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("위-오른쪽위로 포인트를 이동한다.")
    void move_point_up_and_up_right() {
        final var point = G4;

        final var sut = point.moveUpUpRight();

        assertThat(sut).isEqualTo(H6);
    }

    @Test
    @DisplayName("포인트가 위-오른쪽위로 이동 가능하면 참을 반환한다.")
    void true_if_point_can_move_up_and_up_right() {
        final var point = A4;

        final var sut = point.canMoveUpUpRight();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 위-오른쪽위로 이동 불가능하면 거짓을 반환한다.")
    void false_if_point_can_move_up_and_up_right() {
        final var point = H7;

        final var sut = point.canMoveUpUpRight();

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("오른쪽-오른쪽위로 포인트를 이동한다.")
    void move_point_right_and_up_right() {
        final var point = D4;

        final var sut = point.moveRightUpRight();

        assertThat(sut).isEqualTo(F5);
    }

    @Test
    @DisplayName("포인트가 오른쪽-오른쪽위로 이동 가능하면 참을 반환한다.")
    void true_if_point_can_move_right_and_up_right() {
        final var point = F7;

        final var sut = point.canMoveRightUpRight();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 오른쪽-오른쪽위로 이동 불가능하면 거짓을 반환한다.")
    void false_if_point_can_move_right_and_up_right() {
        final var point = G7;

        final var sut = point.canMoveUpUpRight();

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("오른쪽-아래오른쪽 으로 포인트를 이동한다.")
    void move_point_right_and_down_right() {
        final var point = D4;

        final var sut = point.moveRightDownRight();

        assertThat(sut).isEqualTo(F3);
    }

    @Test
    @DisplayName("포인트가 오른쪽-아래오른쪽 으로 이동 가능하면 참을 반환한다.")
    void true_if_point_can_move_right_and_down_right() {
        final var point = F7;

        final var sut = point.canMoveRightUpRight();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 오른쪽-아래오른쪽 으로 이동 불가능하면 거짓을 반환한다.")
    void false_if_point_can_move_right_and_down_right() {
        final var point = D1;

        final var sut = point.canMoveRightDownRight();

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("왼쪽-아래왼쪽 으로 포인트를 이동한다.")
    void move_point_left_and_down_left() {
        final var point = D4;

        final var sut = point.moveLeftDownLeft();

        assertThat(sut).isEqualTo(B3);
    }

    @Test
    @DisplayName("포인트가 왼쪽-아래왼쪽 으로 이동 가능하면 참을 반환한다.")
    void true_if_point_can_move_left_and_down_left() {
        final var point = C2;

        final var sut = point.canMoveLeftDownLeft();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 왼쪽-아래왼쪽 으로 이동 불가능하면 거짓을 반환한다.")
    void false_if_point_can_move_left_and_down_left() {
        final var point = B2;

        final var sut = point.canMoveLeftDownLeft();

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("왼쪽-위왼쪽 으로 포인트를 이동한다.")
    void move_point_left_and_up_left() {
        final var point = D4;

        final var sut = point.moveLeftUpLeft();

        assertThat(sut).isEqualTo(B5);
    }

    @Test
    @DisplayName("포인트가 왼쪽-위왼쪽 으로 이동 가능하면 참을 반환한다.")
    void true_if_point_can_move_left_and_up_left() {
        final var point = C2;

        final var sut = point.canMoveLeftUpLeft();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 왼쪽-위왼쪽 으로 이동 불가능하면 거짓을 반환한다.")
    void false_if_point_can_move_left_and_up_left() {
        final var point = B7;

        final var sut = point.canMoveLeftUpLeft();

        assertThat(sut).isFalse();
    }

    @Test
    @DisplayName("아래-아래왼쪽 으로 포인트를 이동한다")
    void move_point_down_and_down_left() {
        final var point = D4;

        final var sut = point.moveDownDownLeft();

        assertThat(sut).isEqualTo(C2);
    }

    @Test
    @DisplayName("포인트가 아래-아래왼쪽 으로 이동 가능하면 참을 반환한다.")
    void true_if_point_can_move_down_and_down_left() {
        final var point = C3;

        final var sut = point.canMoveDownDownLeft();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 아래-아래왼쪽 으로 이동 불가능하면 거짓을 반환한다.")
    void false_if_point_can_move_down_and_down_left() {
        final var point = B2;

        final var sut = point.canMoveDownDownLeft();

        assertThat(sut).isFalse();
    }




    @Test
    @DisplayName("아래-아래오른쪽 으로 포인트를 이동한다")
    void move_point_down_and_down_right() {
        final var point = D4;

        final var sut = point.moveDownDownRight();

        assertThat(sut).isEqualTo(E2);
    }

    @Test
    @DisplayName("포인트가 아래-아래오른쪽 으로 이동 가능하면 참을 반환한다.")
    void true_if_point_can_move_down_and_down_right() {
        final var point = C3;

        final var sut = point.canMoveDownDownRight();

        assertThat(sut).isTrue();
    }

    @Test
    @DisplayName("포인트가 아래-아래오른쪽 으로 이동 불가능하면 거짓을 반환한다.")
    void false_if_point_can_move_down_and_down_right() {
        final var point = C2;

        final var sut = point.canMoveDownDownRight();

        assertThat(sut).isFalse();
    }
}
