package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {
    
    @ParameterizedTest
    @CsvSource(value = {"d4:e4", "d4:d3", "d4:c4", "d4:d5", "d4:h4", "d4:a4", "d4:d1", "d4:d8"}, delimiter = ':')
    @DisplayName("룩 움직일 수 있는 위치 테스트")
    void canMove(String from, String to) {
        Rook rook = Rook.create(Color.WHITE);
        assertDoesNotThrow(() ->
                rook.canMove(Position.from(from), Position.from(to))
        );
    }
    
    @ParameterizedTest
    @CsvSource(value = {"d4:e2", "d4:a2", "d4:c2", "d4:h3", "d4:g1", "d4:g7", "d4:a1", "d4:a7"}, delimiter = ':')
    @DisplayName("룩 움직일 수 없는 위치 테스트")
    void canNotMove(String from, String to) {
        Rook rook = Rook.create(Color.WHITE);
        assertThrows(IllegalArgumentException.class, () ->
                rook.canMove(Position.from(from), Position.from(to))
        );
    }
    
}