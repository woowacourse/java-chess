package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Knight 테스트")
class KnightTest {

    @DisplayName("입력된 방향에 대해")
    @Nested
    class DirectionTest {

        @DisplayName("유효하지 않으면 예외를 반환한다.")
        @Test
        void invalid_Direction() {
            Position current = new Position(Column.a, Row.FIRST);
            Position invalidTarget = new Position(Column.c, Row.THIRD);
            Knight knight = new Knight(Color.BLACK);

            assertThatThrownBy(() -> knight.findValidDirection(current, invalidTarget));
        }

        @DisplayName("유효하면 방향 객체를 반환한다.")
        @Test
        void valid_Direction() {
            Position current = new Position(Column.a, Row.FIRST);
            Position target = new Position(Column.b, Row.THIRD);
            Knight knight = new Knight(Color.BLACK);

            Direction actual = knight.findValidDirection(current, target);

            assertThat(actual).isEqualTo(Direction.NNE);
        }
    }
}
