package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.CachedPosition;
import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("King 은")
class KingTest {

    @DisplayName("입력된 방향에 대해")
    @Nested
    class DirectionTest {

        @DisplayName("유효하지 않으면 예외를 반환한다.")
        @Test
        void invalid_Direction() {
            Position current = CachedPosition.a1;
            Position invalidTarget = CachedPosition.b3;
            King king = new King(Color.BLACK);

            assertThatThrownBy(() -> king.findValidDirection(current, invalidTarget));
        }

        @DisplayName("유효하면 방향 객체를 반환한다.")
        @Test
        void valid_Direction() {
            Position current = CachedPosition.a1;
            Position target = CachedPosition.b2;
            King king = new King(Color.BLACK);

            Direction actual = king.findValidDirection(current, target);

            assertThat(actual).isEqualTo(Direction.NE);
        }
    }

    @DisplayName("입력된 범위에 대해")
    @Nested
    class RangeTest {

        @DisplayName("유효하지 않으면 예외를 반환한다.")
        @Test
        void invalid_Range() {
            Position current = CachedPosition.a1;
            Position invalidTarget = CachedPosition.c3;
            King king = new King(Color.BLACK);

            assertThatThrownBy(() -> king.findValidDirection(current, invalidTarget));
        }

        @DisplayName("유효하면 방향 객체를 반환한다.")
        @Test
        void valid_Direction() {
            Position current = CachedPosition.a1;
            Position target = CachedPosition.b2;
            King king = new King(Color.BLACK);

            Direction actual = king.findValidDirection(current, target);

            assertThat(actual).isEqualTo(Direction.NE);
        }
    }
}
