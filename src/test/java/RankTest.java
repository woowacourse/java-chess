import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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
        Assertions.assertThat(Rank.from(number)).isEqualTo(rank);
    }


}
