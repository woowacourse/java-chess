package chess.domain.position;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PositionTest {

    @Test
    @DisplayName("위치는 파일을 가진다")
    void hasFileInPosition() {
        // given
        final var position = Position.of('a', 1);
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
        final var position = Position.of('a', 1);
        final var expected = 1;

        // when
        final var actual = position.rank();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
