package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PositionTest {

    @Test
    void Rank_와_File_을_받아_정상적으로_생성된다() {
        // expect
        assertThatNoException().isThrownBy(() -> Position.of(File.A, Rank.ONE));
    }

    @Test
    void 입력받는_포지션과의_파일_차이를_반환한다() {
        // given
        final Position source = Position.of(File.A, Rank.FOUR);
        final Position target = Position.of(File.G, Rank.FIVE);

        // when
        final int result = source.calculateFileGap(target);

        // then
        assertThat(result).isEqualTo(-6);
    }

    @Test
    void 입력받는_포지션과의_랭크_차이를_반환한다() {
        // given
        final Position source = Position.of(File.A, Rank.FOUR);
        final Position target = Position.of(File.G, Rank.FIVE);

        // when
        final int result = source.calculateRankGap(target);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @ParameterizedTest(name = "입력받은 파일과 같은 파일인지 확인한다. 대상: ONE, 입력: {0}, 결과: {1}")
    @CsvSource({"ONE, true", "TWO, false"})
    void 입력받은_파일과_같은_랭크인지_확인한다(final Rank rank, final boolean result) {
        // given
        final Position source = Position.of(File.A, Rank.ONE);

        // expect
        assertThat(source.isSameRank(rank)).isEqualTo(result);
    }
}
