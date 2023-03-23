package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RankTest {

    @Test
    void 인덱스_값으로_Rank_를_찾을_수_있다() {
        final int index = 1;

        final Rank rank = Rank.findByIndex(index);

        assertThat(rank).isEqualTo(Rank.ONE);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void 잘못된_인덱스인_경우_예외를_던진다(int index) {
        assertThatThrownBy(() -> Rank.findByIndex(index))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"0,false", "1,true", "8,true", "9,false"})
    void 인덱스를_검증할_수_있다(final int index, final boolean expected) {
        assertThat(Rank.isIndexValid(index)).isEqualTo(expected);
    }
}
