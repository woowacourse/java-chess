package chess.domain.piece;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueenTest {
    @ParameterizedTest
    @DisplayName("퀸이 움직일 수 없는 위치가 들어갔을 때 예외를 잘 처리하는지 확인")
    @CsvSource(value = {"f4", "h5"})
    void invalidQueenMove(String destination) {
        Queen queen = new Queen(new Position("d3"), Team.BLACK);
        assertThatThrownBy(() ->
                queen.move(new Position(destination))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("퀸이 제대로 움직이는지 확인")
    @CsvSource(value = {"c5", "h5", "e1", "e6", "d6", "b8"})
    void queenMove(String destination) {
        Queen queen = new Queen(new Position("e5"), Team.BLACK);
        queen.move(new Position(destination));
    }
}
