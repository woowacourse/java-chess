package chess.domain.move;

import chess.domain.move.enums.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MoveHandlerTest {

    @Nested
    class 상하좌우_테스트 {
        @Test
        @DisplayName("→로 이동시 RIGHT를 반환")
        void findByPosition_Horizontal_RIGHT() {
            // given
            final Position source = Position.from("a1");
            final Position target = Position.from("b1");
            final MoveEnum expected = HorizontalMove.RIGHT;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("←로 이동시 LEFT를 반환")
        void findByPosition_Horizontal_LEFT() {
            // given
            final Position source = Position.from("b1");
            final Position target = Position.from("a1");
            final MoveEnum expected = HorizontalMove.LEFT;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("↑로 이동시 UP을 반환")
        void findByPosition_Vertical_UP() {
            // given
            final Position source = Position.from("a1");
            final Position target = Position.from("a2");
            final MoveEnum expected = VerticalMove.UP;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("↓로 이동시 DOWN을 반환")
        void findByPosition_Vertical_DOWN() {
            // given
            final Position source = Position.from("a2");
            final Position target = Position.from("a1");
            final MoveEnum expected = VerticalMove.DOWN;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class 대각선_테스트 {

        @Test
        @DisplayName("↗로 이동시 RIGHT_UP을 반환")
        void findByPosition_Diagonal_RIGHT_UP() {
            // given
            final Position source = Position.from("a1");
            final Position target = Position.from("b2");
            final MoveEnum expected = DiagonalMove.RIGHT_UP;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("↖로 이동시 LEFT_UP을 반환")
        void findByPosition_Diagonal_LEFT_UP() {
            // given
            final Position source = Position.from("b1");
            final Position target = Position.from("a2");
            final MoveEnum expected = DiagonalMove.LEFT_UP;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("↘로 이동시 RIGHT_DOWN을 반환")
        void findByPosition_Diagonal_RIGHT_DOWN() {
            // given
            final Position source = Position.from("a2");
            final Position target = Position.from("b1");
            final MoveEnum expected = DiagonalMove.RIGHT_DOWN;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("↙로 이동시 LEFT_DOWN을 반환")
        void findByPosition_Diagonal_LEFT_DOWN() {
            // given
            final Position source = Position.from("b2");
            final Position target = Position.from("a1");
            final MoveEnum expected = DiagonalMove.LEFT_DOWN;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class 나이트_테스트 {
        @Test
        @DisplayName("(0, 0) → (-1, 2)로 이동시 LEFT_UP_UP을 반환")
        void findByPosition_Knight_LEFT_UP_UP() {
            // given
            final Position source = Position.from("b1");
            final Position target = Position.from("a3");
            final MoveEnum expected = KnightMove.LEFT_UP_UP;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (1, 2)로 이동시 RIGHT_UP_UP을 반환")
        void findByPosition_Knight_RIGHT_UP_UP() {
            // given
            final Position source = Position.from("a1");
            final Position target = Position.from("b3");
            final MoveEnum expected = KnightMove.RIGHT_UP_UP;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (2, 1)로 이동시 RIGHT_RIGHT_UP을 반환")
        void findByPosition_Knight_RIGHT_RIGHT_UP() {
            // given
            final Position source = Position.from("a1");
            final Position target = Position.from("c2");
            final MoveEnum expected = KnightMove.RIGHT_RIGHT_UP;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (2, -1)로 이동시 RIGHT_RIGHT_DOWN을 반환")
        void findByPosition_Knight_RIGHT_RIGHT_DOWN() {
            // given
            final Position source = Position.from("a2");
            final Position target = Position.from("c1");
            final MoveEnum expected = KnightMove.RIGHT_RIGHT_DOWN;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (1, -2)로 이동시 RIGHT_DOWN_DOWN을 반환")
        void findByPosition_Knight_RIGHT_DOWN_DOWN() {
            // given
            final Position source = Position.from("a3");
            final Position target = Position.from("b1");
            final MoveEnum expected = KnightMove.RIGHT_DOWN_DOWN;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (-1, -2)로 이동시 LEFT_DOWN_DOWN을 반환")
        void findByPosition_Knight_LEFT_DOWN_DOWN() {
            // given
            final Position source = Position.from("b3");
            final Position target = Position.from("a1");
            final MoveEnum expected = KnightMove.LEFT_DOWN_DOWN;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (-2, -1)로 이동시 LEFT_LEFT_DOWN을 반환")
        void findByPosition_Knight_LEFT_LEFT_DOWN() {
            // given
            final Position source = Position.from("c2");
            final Position target = Position.from("a1");
            final MoveEnum expected = KnightMove.LEFT_LEFT_DOWN;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (-2, 1)로 이동시 LEFT_LEFT_UP을 반환")
        void findByPosition_Knight_LEFT_LEFT_UP() {
            // given
            final Position source = Position.from("c1");
            final Position target = Position.from("a2");
            final MoveEnum expected = KnightMove.LEFT_LEFT_UP;

            // when
            final MoveEnum actual = MoveHandler.findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Test
    void 방향을_구할_수_없는_경우_예외를_발생한다() {
        // given
        final Position source = Position.from("a1");
        final Position target = Position.from("h7");

        // then
        assertThatThrownBy(() -> MoveHandler.findByPosition(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 방향입니다.");
    }
}
