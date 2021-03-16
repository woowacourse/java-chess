package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @DisplayName("Position의 equals 테스트")
    @ParameterizedTest
    @EnumSource(File.class)
    void getPositionTest(File file) {
        for (Rank rank : Rank.values()) {
            Position position = Position.of(file, rank);

            assertThat(position).isEqualTo(Position.of(file, rank));
        }
    }
}