package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.Position;

public class KnightTest {

    @ParameterizedTest
    @DisplayName("나이트가 움직일 수 없는 위치가 들어갔을 때 예외를 잘 처리하는지 확인")
    @CsvSource(value = {"e2", "h6"})
    void invalidKingMove(String destination) {
        Knight knight = new Knight(new Position("e1"), Team.WHITE);
        assertThatThrownBy(() ->
            knight.validateMove(new Position(destination))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("나이트가 제대로 움직이는지 확인")
    @CsvSource(value = {"e6", "f5", "f3", "e2", "c2", "b3", "b5", "c6"})
    void kingMove(String destination) {
        Knight knight = new Knight(new Position("d4"), Team.WHITE);
        knight.move(new Position(destination));
    }

}
