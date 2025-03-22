package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @DisplayName("퀸이 이동가능한 경로를 구한다. (상향)")
    @Test
    void calculate_queen_can_move_direction() {
        Position departure = new Position(Column.D, Row.TWO);
        Queen queen = new Queen(Color.BLACK);
        Position arrival = new Position(Column.D, Row.EIGHT);
        List<Position> canAblePositions = queen.calculateCanMovePosition(departure, arrival);

        List<Position> expectedPosition = List.of(
            new Position(Column.D, Row.THREE),
            new Position(Column.D, Row.FOUR),
            new Position(Column.D, Row.FIVE),
            new Position(Column.D, Row.SIX),
            new Position(Column.D, Row.SEVEN),
            new Position(Column.D, Row.EIGHT));
        assertThat(canAblePositions).containsExactlyInAnyOrderElementsOf(expectedPosition);
    }

    @DisplayName("퀸이 이동가능한 경로를 구한다. (우상향)")
    @Test
    void calculate_king_can_move_direction() {
        Position departure = new Position(Column.A, Row.ONE);
        Queen queen = new Queen(Color.BLACK);
        Position arrival = new Position(Column.C, Row.THREE);
        List<Position> canAblePositions = queen.calculateCanMovePosition(departure, arrival);
        List<Position> expectedPosition = List.of(
            new Position(Column.B, Row.TWO),
            new Position(Column.C, Row.THREE));
        assertThat(canAblePositions).containsExactlyInAnyOrderElementsOf(expectedPosition);
    }

    @DisplayName("퀸이 이동 불가능한 좌표라면, 예외를 발생시킨다")
    @Test
    void when_queen_cannot_move_then_throw_exception() {
        Position departure = new Position(Column.D, Row.FOUR);
        Queen queen = new Queen(Color.BLACK);
        Position arrival = new Position(Column.F, Row.FIVE);
        assertThatThrownBy(() -> queen.calculateCanMovePosition(departure, arrival));
        }
}
