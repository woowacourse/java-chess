package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PositionTest {

    @Test
    void Rank_와_File_을_받아_정상적으로_생성된다() {
        // expect
        assertThatNoException().isThrownBy(() -> Position.of(Rank.A, File.ONE));
    }

    @Test
    void 입력받는_포지션과의_랭크_차이를_반환한다() {
        // given
        final Position source = Position.of(Rank.A, File.FOUR);
        final Position target = Position.of(Rank.G, File.FIVE);

        // when
        final int result = source.calculateRankGap(target);

        // then
        assertThat(result).isEqualTo(-6);
    }

    @Test
    void 입력받는_포지션과의_파일_차이를_반환한다() {
        // given
        final Position source = Position.of(Rank.A, File.FOUR);
        final Position target = Position.of(Rank.G, File.FIVE);

        // when
        final int result = source.calculateFileGap(target);

        // then
        assertThat(result).isEqualTo(-1);
    }
}
