package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PositionTest {

    @Test
    @DisplayName("파일과 랭크를 통해 위치를 생성할 수 있다")
    void createPositionByFileAndRank() {
        assertThatNoException().isThrownBy(() -> Position.of(File.A, Rank.ONE));
    }

    @Test
    @DisplayName("문자열을 통해 위치를 생성할 수 있다")
    void createPositionByString() {
        assertThatNoException().isThrownBy(() -> Position.from("a1"));
    }

    @Test
    @DisplayName("파일의 차이를 구할 수 있다")
    void canDiffFile() {
        // given
        final var source = Position.of(File.A, Rank.ONE);
        final var target = Position.of(File.H, Rank.TWO);
        final var expected = 7;

        // when
        final var actual = source.diffFile(target);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("랭크의 차이를 구할 수 있다")
    void canDiffRank() {
        // given
        final var source = Position.of(File.A, Rank.ONE);
        final var target = Position.of(File.B, Rank.EIGHT);
        final var expected = 7;

        // when
        final var actual = source.diffRank(target);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("위치는 파일을 가진다")
    void hasFileInPosition() {
        // given
        final var position = Position.of(File.A, Rank.ONE);
        final var expected = 'a';

        // when
        final var actual = position.file();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("위치는 랭크를 가진다")
    void hasRankInPosition() {
        // given
        final var position = Position.of(File.A, Rank.ONE);
        final var expected = 1;

        // when
        final var actual = position.rank();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
