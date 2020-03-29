package piece;

import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Rook;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class RookTest {

    @ParameterizedTest
    @DisplayName("목표 위치값으로 제자리가 들어오면 예외가 발생해야 함")
    @ValueSource(strings = {"a1", "b8", "f7"})
    void inputCurrentPositionThenThrowException(String input) {
        Rook rook = new Rook(PieceColor.BLACK, Positions.of(input));

        Assertions.assertThatThrownBy(() -> rook.getPathTo(Positions.of(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 자리한 위치로는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("룩이 갈 수 있는 목표 위치값이 들어오면 경로를 정상적으로 반환해야 함")
    void inputMovablePositionThenReturnPath() {
        Rook rook = new Rook(PieceColor.BLACK, Positions.of("d5"));
        List<Position> path = rook.getPathTo(Positions.of("h5"));

        Assertions.assertThat(path.contains(Positions.of("e5"))).isTrue();
        Assertions.assertThat(path.contains(Positions.of("f5"))).isTrue();
        Assertions.assertThat(path.contains(Positions.of("g5"))).isTrue();
        Assertions.assertThat(path.contains(Positions.of("h5"))).isTrue();
    }


    @ParameterizedTest
    @DisplayName("룩이 이동할 수 없는 목표 위치값이 들어오면 예외가 발생해야 함")
    @CsvSource(value = {"d5,e6", "f3,g2", "b7,a6", "g1,f2"})
    void inputNotMovablePositionThenThrowException(String source, String target) {
        Rook rook = new Rook(PieceColor.BLACK, Positions.of(source));

        Assertions.assertThatThrownBy(() -> rook.getPathTo(Positions.of(target)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("룩이 이동할 수 없는 위치입니다.");
    }
}
