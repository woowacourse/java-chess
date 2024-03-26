package domain.piece.attribute.point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class RankTest {
    private static Stream<Arguments> maskingValues() {
        return Stream.of(
                Arguments.of(Rank.ONE, 1),
                Arguments.of(Rank.TWO, 2),
                Arguments.of(Rank.THREE, 3),
                Arguments.of(Rank.FOUR, 4),
                Arguments.of(Rank.FIVE, 5),
                Arguments.of(Rank.SIX, 6),
                Arguments.of(Rank.SEVEN, 7),
                Arguments.of(Rank.EIGHT, 8)
        );
    }

    @ParameterizedTest
    @MethodSource("maskingValues")
    @DisplayName("랭크는 가지고 있는 값에 따라 생성 인스턴스가 바뀐다.")
    void generate_different_instance_from_integer(Rank rank, int number) {
        assertThat(Rank.from(number)).isEqualTo(rank);
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, mode = Mode.EXCLUDE, names = {"EIGHT", "SEVEN"})
    @DisplayName("랭크는 위로 이동할 거리만큼 이동이 가능하다면 참을 반환한다.")
    void return_true_when_rank_can_move_to_up_by_insert_value(Rank rank) {
        assertThat(rank.canMoveUp(2))
                .isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, mode = Mode.INCLUDE, names = {"EIGHT", "SEVEN"})
    @DisplayName("랭크는 위로 이동할 거리만큼 이동이 불가능하다면 거짓을 반환한다.")
    void return_false_when_rank_can_not_move_to_up_by_insert_values(Rank rank) {
        assertThat(rank.canMoveUp(2))
                .isFalse();
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, mode = Mode.EXCLUDE, names = {"ONE", "TWO"})
    @DisplayName("랭크는 아래로 이동할 거리만큼 이동이 가능하다면 참을 반환한다.")
    void return_true_when_rank_can_move_to_down_by_insert_value(Rank rank) {
        assertThat(rank.canMoveDown(2))
                .isTrue();
    }


    @ParameterizedTest
    @EnumSource(value = Rank.class, mode = Mode.INCLUDE, names = {"ONE", "TWO"})
    @DisplayName("랭크는 아래로 이동할 거리만큼 이동이 가능하다면 참을 반환한다.")
    void return_false_when_rank_can_not_move_to_down_by_insert_value(Rank rank) {
        assertThat(rank.canMoveDown(2))
                .isFalse();
    }

    @Test
    @DisplayName("랭크는 위로 이동하고 싶은 거리만큼 이동이 가능하다면 다음 이동한 랭크를 반환한다.")
    void return_new_rank_that_moved_up_position_by_insert_value() {
        assertThat(Rank.FIVE.moveUp(3))
                .isSameAs(Rank.EIGHT);
    }

    @Test
    @DisplayName("랭크는 위로 이동하고 싶은 거리만큼 이동이 불가능하다면 예외를 발생한다.")
    void throw_exception_when_can_not_move_up_by_insert_value() {
        assertThatThrownBy(() -> Rank.SIX.moveUp(3))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("랭크는 아래로 이동하고 싶은 거리만큼 이동이 가능하다면 다음 이동한 랭크를 반환한다.")
    void return_new_rank_that_moved_down_position_by_insert_value() {
        assertThat(Rank.FOUR.moveDown(3))
                .isSameAs(Rank.ONE);
    }

    @Test
    @DisplayName("랭크는 아래로 이동하고 싶은 거리만큼 이동이 불가능하다면 예외를 발생시킨다.")
    void throw_exception_when_can_not_move_down_by_insert_value() {
        assertThatThrownBy(() -> Rank.THREE.moveDown(3))
                .isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, mode = Mode.INCLUDE, names = "EIGHT" )
    @DisplayName("현재 랭크가 가장 높다면 참을 반한한다.")
    void return_true_if_rank_is_top(Rank rank) {
        assertThat(rank.isTop())
                .isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, mode = Mode.EXCLUDE, names = "EIGHT")
    @DisplayName("현재 랭크가 가장 높지 않다면 거짓을 반환한다.")
    void return_false_if_rank_is_not_top(Rank rank) {
        assertThat(rank.isTop())
                .isFalse();
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, mode = Mode.INCLUDE, names= "ONE")
    @DisplayName("현재 랭크가 가장 낮다면 참을 반환한다.")
    void return_true_if_rank_is_bottom(Rank rank) {
        assertThat(rank.isBottom())
                .isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, mode = Mode.EXCLUDE, names= "ONE")
    @DisplayName("현재 랭크가 가장 낮지 않다면 거짓을 반환한다.")
    void return_false_if_rank_is_not_bottom(Rank rank) {
        assertThat(rank.isBottom())
                .isFalse();
    }

}
