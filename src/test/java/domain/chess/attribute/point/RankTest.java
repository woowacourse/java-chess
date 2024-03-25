package domain.chess.attribute.point;

import domain.chess.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void generate_different_instance_from_integer(final Rank rank, final int number) {
        Assertions.assertThat(Rank.from(number))
                  .isEqualTo(rank);
    }

    @Test
    @DisplayName("EIGHT 은 랭크 위 끝이다.")
    void eight_is_up_end() {
        final var sut = Rank.EIGHT;

        final var result = sut.isUpEnd();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("ONE 은 랭크 아래 끝이다.")
    void one_is_down_end() {
        final var sut = Rank.ONE;

        final var result = sut.isDownEnd();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("THREE 에서 위로 두번 움직이면 FIVE이다.")
    void three_move_up_two_is_five() {
        final var sut = Rank.THREE;

        final var result = sut.moveUp(2);

        assertThat(result).isEqualTo(Rank.FIVE);
    }

    @Test
    @DisplayName("위로 이동할 수 있는 범위를 벗어나면 예외를 발생한다.")
    void throw_exception_when_up_move_is_out_of_range() {
        final var sut = Rank.EIGHT;

        assertThatThrownBy(() -> sut.moveUp(5))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("FOUR 에서 아래로 세번 움직이면 ONE이다.")
    void four_move_down_three_is_one() {
        final var sut = Rank.FOUR;

        final var result = sut.moveDown(3);

        assertThat(result).isEqualTo(Rank.ONE);
    }

    @Test
    @DisplayName("아래로 이동할 수 있는 범위를 벗어나면 예외를 발생한다.")
    void throw_exception_when_down_move_is_out_of_range() {
        final var sut = Rank.EIGHT;

        assertThatThrownBy(() -> sut.moveDown(8))
                .isInstanceOf(IllegalStateException.class);
    }
}
