package chessgame.domain.piece.attribute.point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chessgame.domain.piece.attribute.point.File;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class FileTest {
    private static Stream<Arguments> maskingValues() {
        return Stream.of(
                Arguments.of(File.A, 'a'),
                Arguments.of(File.B, 'b'),
                Arguments.of(File.C, 'c'),
                Arguments.of(File.D, 'd'),
                Arguments.of(File.E, 'e'),
                Arguments.of(File.F, 'f'),
                Arguments.of(File.G, 'g'),
                Arguments.of(File.H, 'h')
        );
    }

    @ParameterizedTest
    @MethodSource("maskingValues")
    @DisplayName("파일은 가지고 있는 값에 따라 생성 인스턴스가 바뀐다.")
    void generate_different_instance_from_character(File file, char text) {
        Assertions.assertThat(File.from(text)).isEqualTo(file);
    }

    @ParameterizedTest
    @EnumSource(value = File.class, mode = Mode.EXCLUDE, names = {"G", "H"})
    @DisplayName("파일은 오른쪽 이동할 거리만큼 이동이 가능하다면 참을 반환한다.")
    void return_true_when_file_can_move_to_right_by_insert_step(File file) {
        assertThat(file.canMoveRight(2)).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = File.class, mode = Mode.INCLUDE, names = {"G", "H"})
    @DisplayName("파일은 오른쪽 이동할 거리만큼 이동이 불가능하다면 거짓을 반환한다.")
    void return_false_when_file_can_move_to_right_by_insert_step(File file) {
        assertThat(file.canMoveRight(2)).isFalse();
    }


    @ParameterizedTest
    @EnumSource(value = File.class, mode = Mode.EXCLUDE, names = {"A", "B"})
    @DisplayName("파일은 왼쪽 이동할 거리만큼 이동이 가능하다면 참을 반환한다.")
    void return_true_when_file_can_move_to_left_by_insert_step(File file) {
        assertThat(file.canMoveLeft(2)).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = File.class, mode = Mode.INCLUDE, names = {"A", "B"})
    @DisplayName("파일은 왼쪽 이동할 거리만큼 이동이 불가능하다면 거짓을 반환한다.")
    void return_false_when_file_can_move_to_left_by_insert_step(File file) {
        assertThat(file.canMoveLeft(2)).isFalse();
    }

    @ParameterizedTest
    @EnumSource(value = File.class, mode = Mode.INCLUDE, names = {"A"})
    @DisplayName("해당 파일이 왼쪽 끝이라면 참을 반환한다.")
    void return_true_while_file_is_left_end(File file) {
        assertThat(file.isFarLeft()).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = File.class, mode = Mode.EXCLUDE, names = {"A"})
    @DisplayName("해당 파일이 왼쪽 끝이 아니라면 거짓을 반환한다.")
    void return_false_while_file_is_left_end(File file) {
        assertThat(file.isFarLeft()).isFalse();
    }

    @ParameterizedTest
    @EnumSource(value = File.class, mode = Mode.INCLUDE, names = {"H"})
    @DisplayName("해당 파일이 오른쪽 끝이라면 참을 반환한다.")
    void return_true_while_file_is_right_end(File file) {
        assertThat(file.isFarRight()).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = File.class, mode = Mode.EXCLUDE, names = {"H"})
    @DisplayName("해당 파일이 오른쪽 끝이 아니라면 거짓을 반환한다.")
    void return_false_while_file_is_right_end(File file) {
        assertThat(file.isFarRight()).isFalse();
    }

    @Test
    @DisplayName("파일은 이동이 가능한 위치라면 원하는 스텝만큼 오른쪽 이동이 가능하다.")
    void file_can_move_to_right_position_by_insert_step() {
        assertThat(File.A.moveRight(3))
                .isSameAs(File.D);
    }

    @Test
    @DisplayName("파일은 이동이 불가능한 위치로 오른쪽 이동을 시도할 시 예외를 발생시킨다.")
    void file_throw_exception_when_can_not_move_to_next_right_position() {
        assertThatThrownBy(() -> File.G.moveRight(2))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("파일은 이동이 가능한 위치라면 원하는 스텝만큼 왼쪽 이동이 가능하다.")
    void file_can_move_to_left_position_by_insert_step() {
        assertThat(File.A.moveRight(3))
                .isSameAs(File.D);
    }

    @Test
    @DisplayName("파일은 이동이 불가능한 위치로 왼쪽 이동을 시도할 시 예외를 발생시킨다.")
    void file_throw_exception_when_can_not_move_to_next_left_position() {
        assertThatThrownBy(() -> File.G.moveRight(2))
                .isInstanceOf(IllegalStateException.class);
    }

}
