package chess.domain.attribute;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.chessboard.attribute.Rank;

class RankTest {
    @DisplayName("0~7 범위를 벗어나는 행 번호를 입력하면 예외가 발생한다.")
    @ValueSource(ints = {-1, 8})
    @ParameterizedTest
    void constructor(int row) {
        assertThatThrownBy(() -> Rank.from(row))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("랭크는 1~8 사이로 입력해주세요: " + row);
    }

    @DisplayName("1~8 범위를 벗어나는 랭크를 입력하면 예외가 발생한다.")
    @ValueSource(strings = {"0", "9"})
    @ParameterizedTest
    void constructor(String value) {
        assertThatThrownBy(() -> Rank.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("랭크는 1~8 사이로 입력해주세요: " + value);
    }
}
