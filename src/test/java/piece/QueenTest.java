package piece;

import chess.domain.board.Position;
import chess.domain.board.PositionFactory;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import chess.exception.NotMovableException;
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
        Queen queen = new Queen(PieceColor.BLACK, PositionFactory.of(input));

        Assertions.assertThatThrownBy(() -> queen.getPathTo(PositionFactory.of(input)))
                .isInstanceOf(NotMovableException.class)
                .hasMessage(String.format("현재 자리한 위치(%s)로는 이동할 수 없습니다.", input));
    }

    @Test
    @DisplayName("퀸이 갈 수 있는 목표 위치값이 들어오면 경로를 정상적으로 반환해야 함")
    void inputMovablePositionThenReturnPath() {
        Queen queen = new Queen(PieceColor.BLACK, PositionFactory.of("d4"));
        List<Position> path = queen.getPathTo(PositionFactory.of("a7"));

        Assertions.assertThat(path.contains(PositionFactory.of("c5"))).isTrue();
        Assertions.assertThat(path.contains(PositionFactory.of("b6"))).isTrue();
        Assertions.assertThat(path.contains(PositionFactory.of("a7"))).isTrue();
    }

    @ParameterizedTest
    @DisplayName("퀸이 이동할 수 없는 목표 위치값이 들어오면 예외가 발생해야 함")
    @CsvSource(value = {"d4,e7", "f8, d4", "g1,f4", "b5,a7", "c3,h7"})
    void inputNotMovablePositionThenThrowException(String source, String target) {
        Queen queen = new Queen(PieceColor.BLACK, PositionFactory.of(source));

        Assertions.assertThatThrownBy(() -> queen.getPathTo(PositionFactory.of(target)))
                .isInstanceOf(NotMovableException.class)
                .hasMessage(String.format("지정한 위치 %s는 퀸이 이동할 수 없는 곳입니다.", target));
    }
}
