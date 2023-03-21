package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {
    
    @ParameterizedTest
    @CsvSource(value = {"a2:a3", "a2:a4", "a2:b3"}, delimiter = ':')
    @DisplayName("화이트 폰 생성 테스트")
    void canMove(String from, String to) {
        Pawn whitePawn = Pawn.create(Color.WHITE);
        assertDoesNotThrow(() -> whitePawn.canMove(Position.from(from), Position.from(to)));
    }
    
    @ParameterizedTest
    @CsvSource(value = {"a7:a6", "a7:a5", "a7:b6"}, delimiter = ':')
    @DisplayName("블랙 폰 생성 테스트")
    void canMove2(String from, String to) {
        Pawn blackPawn = Pawn.create(Color.BLACK);
        assertDoesNotThrow(() -> blackPawn.canMove(Position.from(from), Position.from(to)));
    }
    
    @ParameterizedTest
    @CsvSource(value = {"a2:a1", "a2:b1"}, delimiter = ':')
    @DisplayName("화이트 폰 움직일 수 없는 위치 테스트")
    void canNotMove(String from, String to) {
        Pawn whitePawn = Pawn.create(Color.WHITE);
        Assertions.assertThatThrownBy(() ->
                whitePawn.canMove(Position.from(from), Position.from(to))
        ).isInstanceOf(IllegalArgumentException.class);
    }
    
    @ParameterizedTest
    @CsvSource(value = {"a7:a8", "a7:b8"}, delimiter = ':')
    @DisplayName("블랙 폰 움직일 수 없는 위치 테스트")
    void canNotMove2(String from, String to) {
        Pawn blackPawn = Pawn.create(Color.BLACK);
        Assertions.assertThatThrownBy(() ->
                blackPawn.canMove(Position.from(from), Position.from(to))
        ).isInstanceOf(IllegalArgumentException.class);
    }
    
    @ParameterizedTest
    @CsvSource(value = {"a2:a5", "a2:a6"}, delimiter = ':')
    @DisplayName("화이트 폰 움직일 수 없는 거리 테스트")
    void canNotMove3(String from, String to) {
        Pawn whitePawn = Pawn.create(Color.WHITE);
        Assertions.assertThatThrownBy(() ->
                whitePawn.canMove(Position.from(from), Position.from(to))
        ).isInstanceOf(IllegalArgumentException.class);
    }
    
    @ParameterizedTest
    @CsvSource(value = {"a7:a4", "a7:a3"}, delimiter = ':')
    @DisplayName("블랙 폰 움직일 수 없는 거리 테스트")
    void canNotMove4(String from, String to) {
        Pawn blackPawn = Pawn.create(Color.BLACK);
        Assertions.assertThatThrownBy(() ->
                blackPawn.canMove(Position.from(from), Position.from(to))
        ).isInstanceOf(IllegalArgumentException.class);
    }
    
}