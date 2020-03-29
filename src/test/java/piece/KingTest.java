package piece;

import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.King;
import chess.domain.piece.PieceColor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class KingTest {

    @ParameterizedTest
    @DisplayName("목표 위치값으로 제자리가 들어오면 예외가 발생해야 함")
    @ValueSource(strings = {"a1", "b8", "f7"})
    void inputCurrentPositionThenThrowException(String input) {
        King king = new King(PieceColor.BLACK, Positions.of(input));

        Assertions.assertThatThrownBy(() -> king.getPathTo(Positions.of(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 자리한 위치로는 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("킹이 갈 수 있는 목표 위치값이 들어오면 경로를 정상적으로 반환해야 함")
    @ValueSource(strings = {"e4", "e5", "e6", "f4", "f6", "g4", "g5", "g6"})
    void inputMovablePositionThenReturnPath(String target) {
        King king = new King(PieceColor.BLACK, Positions.of("f5"));
        List<Position> path = king.getPathTo(Positions.of(target));

        Assertions.assertThat(path.contains(Positions.of(target))).isTrue();
    }


    @ParameterizedTest
    @DisplayName("킹이 이동할 수 없는 목표 위치값이 들어오면 예외가 발생해야 함")
    @CsvSource(value = {"d5,f7", "a6,c8", "c2,e7", "f3,b5"})
    void inputNotMovablePositionThenThrowException(String source, String target) {
        King king = new King(PieceColor.BLACK, Positions.of(source));

        Assertions.assertThatThrownBy(() -> king.getPathTo(Positions.of(target)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹이 이동할 수 없는 위치입니다.");
    }
}
