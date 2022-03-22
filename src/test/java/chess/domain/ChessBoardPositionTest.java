package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class ChessBoardPositionTest {

    @ParameterizedTest
    @CsvSource(value = {"a:0", "a:9", "j:3", "j:10"}, delimiter = ':')
    @DisplayName("유효하지 않은 체스판 내부 위치를 생성하면 에외를 발생한다.")
    void validateChess(char column, int row) {
        assertThatThrownBy(() -> new ChessBoardPosition(column, row))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 체스판 범위를 벗어나는 위치가 입력되었습니다.");
    }
}
