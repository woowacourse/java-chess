package chess.domain.piece.movingstrategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightMovingStrategyTest {

    @DisplayName("한쪽 좌표의 차이가 1, 그리고 다른쪽 좌표의 차이가 2가 나는 위치로 이동가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"A,THREE", "C,THREE", "D,TWO"})
    void isMovable_withSevenShape(String xAxisName, String yAxisName) {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> knight = board.find(Position.of(XAxis.B, YAxis.ONE));

        // when
        boolean actual = knight.get().isAbleToMove(Position.of(XAxis.B, YAxis.ONE),
                Position.of(XAxis.valueOf(xAxisName), YAxis.valueOf(yAxisName)));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("한쪽 좌표의 차이가 1, 그리고 다른쪽 좌표의 차이가 2가 나지 않는 위치로 이동 불가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"A,FOUR", "C,FOUR", "D,THREE"})
    void isNotMovable_withNotSevenShape(String xAxisName, String yAxisName) {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> knight = board.find(Position.of(XAxis.B, YAxis.ONE));

        // when
        boolean actual = knight.get().isAbleToMove(Position.of(XAxis.B, YAxis.ONE),
                Position.of(XAxis.valueOf(xAxisName), YAxis.valueOf(yAxisName)));

        // then
        assertThat(actual).isFalse();
    }
}
