package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.CachedPosition;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Pawn 은")
class PawnTest {

    @DisplayName("입력된 방향에 대해")
    @Nested
    class DirectionTest {

        @DisplayName("유효하지 않으면 예외를 반환한다.")
        @Test
        void invalid_Direction() {
            Position current = CachedPosition.a2;
            Position invalidTarget = CachedPosition.c3;
            Pawn pawn = new Pawn(Color.BLACK);

            assertThatThrownBy(() -> pawn.findValidDirection(current, invalidTarget));
        }

        @DisplayName("유효하면 방향 객체를 반환한다.")
        @Test
        void valid_Direction() {
            Position current = CachedPosition.a1;
            Position target = CachedPosition.a2;
            Pawn pawn = new Pawn(Color.WHITE);

            Direction actual = pawn.findValidDirection(current, target);

            assertThat(actual).isEqualTo(Direction.N);
        }
    }

    @DisplayName("입력된 범위에 대해")
    @Nested
    class RangeTest {

        @DisplayName("유효하지 않으면 예외를 반환한다.")
        @Test
        void invalid_Range() {
            Position current = CachedPosition.a1;
            Position invalidTarget = CachedPosition.a3;
            Pawn pawn = new Pawn(Color.WHITE);

            assertThatThrownBy(() -> pawn.findValidDirection(current, invalidTarget));
        }

        @DisplayName("유효하면 방향 객체를 반환한다.")
        @ParameterizedTest
        @CsvSource(value = {"SECOND, FOURTH", "FOURTH, FIFTH"})
        void valid_Direction(Row start, Row end) {
            Position current = new Position(Column.A, start);
            Position target = new Position(Column.A, end);
            Pawn pawn = new Pawn(Color.WHITE);

            Direction actual = pawn.findValidDirection(current, target);

            assertThat(actual).isEqualTo(Direction.N);
        }
    }
}
