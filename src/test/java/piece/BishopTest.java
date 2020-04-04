package piece;

import chess.domain.board.Position;
import chess.domain.board.PositionFactory;
import chess.domain.piece.Bishop;
import chess.domain.piece.PieceColor;
import chess.exception.NotMovableException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class BishopTest {

    @ParameterizedTest
    @DisplayName("목표 위치값으로 제자리가 들어오면 예외가 발생해야 함")
    @ValueSource(strings = {"a1", "b8", "f7"})
    void inputCurrentPositionThenThrowException(String input) {
        Bishop bishop = new Bishop(PieceColor.BLACK, PositionFactory.of(input));

        Assertions.assertThatThrownBy(() -> bishop.getPathTo(PositionFactory.of(input)))
                .isInstanceOf(NotMovableException.class)
                .hasMessage(String.format("현재 자리한 위치(%s)로는 이동할 수 없습니다.", input));
    }

    @Test
    @DisplayName("비숍이 갈 수 있는 목표 위치값이 들어오면 경로를 정상적으로 반환해야 함")
    void inputMovablePositionThenReturnPath() {
        Bishop bishop = new Bishop(PieceColor.BLACK, PositionFactory.of("d5"));
        List<Position> path = bishop.getPathTo(PositionFactory.of("a2"));

        Assertions.assertThat(path.contains(PositionFactory.of("c4"))).isTrue();
        Assertions.assertThat(path.contains(PositionFactory.of("b3"))).isTrue();
    }

    @ParameterizedTest
    @DisplayName("비숍이 이동할 수 없는 목표 위치값이 들어오면 예외가 발생해야 함")
    @CsvSource(value = {"d5,f8", "g3, d5", "b1,e7", "a5,f6", "c1,c4", "e7,e3", "f4,g4", "f4,c4"})
    void inputNotMovablePositionThenThrowException(String source, String target) {
        Bishop bishop = new Bishop(PieceColor.BLACK, PositionFactory.of(source));

        Assertions.assertThatThrownBy(() -> bishop.getPathTo(PositionFactory.of(target)))
                .isInstanceOf(NotMovableException.class)
                .hasMessage(String.format("지정한 위치 %s는 비숍이 이동할 수 없는 곳입니다.", target));
    }
}
