package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {
    
    @ParameterizedTest
    @CsvSource(value = {"d4:e2", "d4:f3", "d4:f5", "d4:e6", "d4:c6", "d4:b5", "d4:b3", "d4:c2"}, delimiter = ':')
    @DisplayName("나이트 움직일 수 있는 위치 테스트")
    void canMove(String from, String to) {
        Knight knight = Knight.create(Color.WHITE);
        assertDoesNotThrow(() ->
                knight.canMove(Position.from(from), Position.from(to))
        );
    }
    
    @ParameterizedTest
    @CsvSource(value = {"d4:e4", "d4:d3", "d4:c4", "d4:d5", "d4:c3", "d4:f4", "d4:b2", "d4:d7"}, delimiter = ':')
    @DisplayName("나이트 움직일 수 없는 위치 테스트")
    void canNotMove(String from, String to) {
        Knight knight = Knight.create(Color.WHITE);
        assertThrows(IllegalArgumentException.class, () ->
                knight.canMove(Position.from(from), Position.from(to))
        );
    }
    
}