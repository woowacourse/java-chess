package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @Test
    @DisplayName("of 테스트")
    void of() {
        Position result = Position.of(File.F, Rank.SIX);

        assertThat(Position.of("f6")).isEqualTo(result);
    }

    @Test
    @DisplayName("newLine 일경우 True를 반환한다")
    void newLineTest() {
        Position position = Position.of("a8");
        assertThat(position.isNewLine()).isTrue();
    }

    @Test
    @DisplayName("같은 랭크이면 True를 반환한다")
    void sameRankTest() {
        Position source = Position.of("a8");
        Position target = Position.of("b8");

        assertThat(source.isSameRank(target)).isTrue();
    }

    @Test
    @DisplayName("같은 파일이면 True를 반환한다")
    void sameFileTest() {
        Position source = Position.of("b7");
        Position target = Position.of("b8");

        assertThat(source.isSameFile(target)).isTrue();
    }

    @Test
    @DisplayName("같은 파일이면 True를 반환한다")
    void calculateRankDistance() {
        Position source = Position.of("b7");
        Position target = Position.of("b8");

        assertThat(source.calculateRankDistance(target)).isEqualTo(-1);
    }

    @Test
    @DisplayName("같은 파일이면 True를 반환한다")
    void calculateFileDistance() {
        Position source = Position.of("a8");
        Position target = Position.of("b8");

        assertThat(source.calculateFileDistance(target)).isEqualTo(-1);
    }
}