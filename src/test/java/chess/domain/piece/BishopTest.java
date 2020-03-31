package chess.domain.piece;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BishopTest {
    @ParameterizedTest
    @DisplayName("비숍이 움직일 수 없는 위치가 들어갔을 때 예외를 잘 처리하는지 확인")
    @CsvSource(value = {"a2", "e7"})
    void invalidBishopMove(String destination) {
        Bishop bishop = new Bishop(new Position("d4"), Team.BLACK);
        assertThatThrownBy(() ->
                bishop.move(new Position(destination))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("비숍이 제대로 움직이는지 확인")
    @CsvSource(value = {"e5", "h8", "a7", "g1", "c5", "b6"})
    void bishopMove(String destination) {
        Bishop bishop = new Bishop(new Position("d4"), Team.BLACK);
        bishop.move(new Position(destination));
    }
}
