package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class KingTest {

    @Nested
    @DisplayName("킹이 이동가능한 경로를 구한다.")
    class FindKingDirection {

        @Test
        @DisplayName("up")
        void up_case() {
            Position departure = new Position(Column.A, Row.ONE);
            King king = new King();
            Position arrival = new Position(Column.A, Row.TWO);
            List<Position> canAblePositions = king.calculateCanMovePosition(departure, arrival);

            List<Position> expectedPosition = List.of(
                new Position(Column.A, Row.TWO));
            assertThat(canAblePositions).isEqualTo(expectedPosition);
        }

        @Test
        @DisplayName("down")
        void down_case() {
            Position departure = new Position(Column.D, Row.FOUR);
            King king = new King();
            Position arrival = new Position(Column.D, Row.THREE);
            List<Position> canAblePositions = king.calculateCanMovePosition(departure, arrival);

            List<Position> expectedPosition = List.of(
                new Position(Column.D, Row.THREE));
            assertThat(canAblePositions).isEqualTo(expectedPosition);
        }

        @Test
        @DisplayName("left")
        void left_case() {
            Position departure = new Position(Column.D, Row.FOUR);
            King king = new King();
            Position arrival = new Position(Column.C, Row.FOUR);
            List<Position> canAblePositions = king.calculateCanMovePosition(departure, arrival);

            List<Position> expectedPosition = List.of(
                new Position(Column.C, Row.FOUR));
            assertThat(canAblePositions).isEqualTo(expectedPosition);
        }

        @Test
        @DisplayName("right")
        void right_case() {
            Position departure = new Position(Column.D, Row.FOUR);
            King king = new King();
            Position arrival = new Position(Column.E, Row.FOUR);
            List<Position> canAblePositions = king.calculateCanMovePosition(departure, arrival);

            List<Position> expectedPosition = List.of(
                new Position(Column.E, Row.FOUR));
            assertThat(canAblePositions).isEqualTo(expectedPosition);
        }
    }

    @Test
    @DisplayName("킹이 이동 못 하는 경로일 경우 예외가 발생해야 한다")
    void right_case() {
        Position departure = new Position(Column.A, Row.ONE);
        King king = new King();
        Position arrival = new Position(Column.C, Row.SIX);
        assertThatThrownBy(() -> king.calculateCanMovePosition(departure, arrival));
    }
}
