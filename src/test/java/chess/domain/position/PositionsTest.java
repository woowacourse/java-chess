package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PositionsTest {

    @ParameterizedTest
    @CsvSource(value = {"a1:1:a", "a2:2:a", "h7:7:h"}, delimiter = ':')
    @DisplayName("위치 반환 확인")
    void moveSourceFormat(String rankFile, String rank, String file) {
        assertThat(Positions.findPosition(rankFile)).isEqualTo(Position.of(rank, file));
    }
}
