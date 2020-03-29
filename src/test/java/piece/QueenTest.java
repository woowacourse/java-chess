package piece;

import chess.domain.board.Position;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class QueenTest {

    @ParameterizedTest
    @DisplayName("목표 위치값으로 제자리가 들어오면 예외가 발생해야 함")
    @ValueSource(strings = {"a1", "b8", "f7"})
    void inputCurrentPositionThenThrowException(String input) {
        Queen queen = new Queen(PieceColor.BLACK, new Position(input));

        Assertions.assertThatThrownBy(() -> queen.getPathTo(new Position(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 자리한 위치로는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("퀸이 갈 수 있는 목표 위치값이 들어오면 경로를 정상적으로 반환해야 함")
    void inputMovablePositionThenReturnPath() {
        Queen queen = new Queen(PieceColor.BLACK, new Position("d4"));
        List<Position> path = queen.getPathTo(new Position("a7"));

        Assertions.assertThat(path.contains(new Position("c5"))).isTrue();
        Assertions.assertThat(path.contains(new Position("b6"))).isTrue();
        Assertions.assertThat(path.contains(new Position("a7"))).isTrue();
    }

    @ParameterizedTest
    @DisplayName("퀸이 이동할 수 없는 목표 위치값이 들어오면 예외가 발생해야 함")
    @CsvSource(value = {"d4,e7", "f8, d4", "g1,f4", "b5,a7", "c3,h7"})
    void inputNotMovablePositionThenThrowException(String source, String target) {
        Queen queen = new Queen(PieceColor.BLACK, new Position(source));

        Assertions.assertThatThrownBy(() -> queen.getPathTo(new Position(target)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("퀸이 이동할 수 없는 위치입니다.");
    }
}
