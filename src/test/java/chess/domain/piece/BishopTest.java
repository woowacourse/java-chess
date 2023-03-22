package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {
    
    @ParameterizedTest
    @CsvSource(value = {"d4:e3", "d4:c3", "d4:e5", "d4:c5", "d4:g1", "d4:g7", "d4:a1", "d4:a7"}, delimiter = ':')
    @DisplayName("비숍 움직일 수 있는 위치 테스트")
    void canMove(String from, String to) {
        Bishop bishop = Bishop.create(Color.WHITE);
        assertDoesNotThrow(() ->
                bishop.canMove(Position.from(from), Position.from(to))
        );
    }
    
    @ParameterizedTest
    @CsvSource(value = {"d4:e4", "d4:d3", "d4:c4", "d4:d5", "d4:f3", "d4:f5", "d4:b3", "d4:b5"}, delimiter = ':')
    @DisplayName("비숍 움직일 수 없는 위치 테스트")
    void canNotMove(String from, String to) {
        Bishop bishop = Bishop.create(Color.WHITE);
        assertThatThrownBy(() ->
                bishop.canMove(Position.from(from), Position.from(to))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
