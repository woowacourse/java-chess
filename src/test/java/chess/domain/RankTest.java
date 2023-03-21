package chess.domain;

import chess.domain.position.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RankTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void 잘못된_위치인_경우_예외를_던진다(int index) {
        Assertions.assertThatThrownBy(() -> Rank.getRank(index))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치입니다.");
    }
}
