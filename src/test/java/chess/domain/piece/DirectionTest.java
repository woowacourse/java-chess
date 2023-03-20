package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DirectionTest {

    @Nested
    class 상하좌우_테스트 {
        @Test
        @DisplayName("→로 이동시 RIGHT를 반환")
        void findByPosition_Horizontal_RIGHT() {
            // given
            final Position source = Position.of(File.A, Rank.ONE);
            final Position target = Position.of(File.B, Rank.ONE);
            final Direction expected = RIGHT;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("←로 이동시 LEFT를 반환")
        void findByPosition_Horizontal_LEFT() {
            // given
            final Position source = Position.of(File.B, Rank.ONE);
            final Position target = Position.of(File.A, Rank.ONE);
            final Direction expected = LEFT;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("↑로 이동시 UP을 반환")
        void findByPosition_Vertical_UP() {
            // given
            final Position source = Position.of(File.A, Rank.ONE);
            final Position target = Position.of(File.A, Rank.TWO);
            final Direction expected = UP;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("↓로 이동시 DOWN을 반환")
        void findByPosition_Vertical_DOWN() {
            // given
            final Position source = Position.of(File.A, Rank.TWO);
            final Position target = Position.of(File.A, Rank.ONE);
            final Direction expected = DOWN;

            // when
            final Direction actual = findByPosition(source, target);

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
            final Position source = Position.of(File.A, Rank.ONE);
            final Position target = Position.of(File.B, Rank.TWO);
            final Direction expected = RIGHT_UP;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("↖로 이동시 LEFT_UP을 반환")
        void findByPosition_Diagonal_LEFT_UP() {
            // given
            final Position source = Position.of(File.B, Rank.ONE);
            final Position target = Position.of(File.A, Rank.TWO);
            final Direction expected = LEFT_UP;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("↘로 이동시 RIGHT_DOWN을 반환")
        void findByPosition_Diagonal_RIGHT_DOWN() {
            // given
            final Position source = Position.of(File.A, Rank.TWO);
            final Position target = Position.of(File.B, Rank.ONE);
            final Direction expected = RIGHT_DOWN;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("↙로 이동시 LEFT_DOWN을 반환")
        void findByPosition_Diagonal_LEFT_DOWN() {
            // given
            final Position source = Position.of(File.B, Rank.TWO);
            final Position target = Position.of(File.A, Rank.ONE);
            final Direction expected = LEFT_DOWN;

            // when
            final Direction actual = findByPosition(source, target);

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
            final Position source = Position.of(File.B, Rank.ONE);
            final Position target = Position.of(File.A, Rank.THREE);
            final Direction expected = LEFT_UP_UP;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (1, 2)로 이동시 RIGHT_UP_UP을 반환")
        void findByPosition_Knight_RIGHT_UP_UP() {
            // given
            final Position source = Position.of(File.A, Rank.ONE);
            final Position target = Position.of(File.B, Rank.THREE);
            final Direction expected = RIGHT_UP_UP;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (2, 1)로 이동시 RIGHT_RIGHT_UP을 반환")
        void findByPosition_Knight_RIGHT_RIGHT_UP() {
            // given
            final Position source = Position.of(File.A, Rank.ONE);
            final Position target = Position.of(File.C, Rank.TWO);
            final Direction expected = RIGHT_RIGHT_UP;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (2, -1)로 이동시 RIGHT_RIGHT_DOWN을 반환")
        void findByPosition_Knight_RIGHT_RIGHT_DOWN() {
            // given
            final Position source = Position.of(File.A, Rank.TWO);
            final Position target = Position.of(File.C, Rank.ONE);
            final Direction expected = RIGHT_RIGHT_DOWN;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (1, -2)로 이동시 RIGHT_DOWN_DOWN을 반환")
        void findByPosition_Knight_RIGHT_DOWN_DOWN() {
            // given
            final Position source = Position.of(File.A, Rank.THREE);
            final Position target = Position.of(File.B, Rank.ONE);
            final Direction expected = RIGHT_DOWN_DOWN;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (-1, -2)로 이동시 LEFT_DOWN_DOWN을 반환")
        void findByPosition_Knight_LEFT_DOWN_DOWN() {
            // given
            final Position source = Position.of(File.B, Rank.THREE);
            final Position target = Position.of(File.A, Rank.ONE);
            final Direction expected = LEFT_DOWN_DOWN;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (-2, -1)로 이동시 LEFT_LEFT_DOWN을 반환")
        void findByPosition_Knight_LEFT_LEFT_DOWN() {
            // given
            final Position source = Position.of(File.C, Rank.TWO);
            final Position target = Position.of(File.A, Rank.ONE);
            final Direction expected = LEFT_LEFT_DOWN;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("(0, 0) → (-2, 1)로 이동시 LEFT_LEFT_UP을 반환")
        void findByPosition_Knight_LEFT_LEFT_UP() {
            // given
            final Position source = Position.of(File.C, Rank.ONE);
            final Position target = Position.of(File.A, Rank.TWO);
            final Direction expected = LEFT_LEFT_UP;

            // when
            final Direction actual = findByPosition(source, target);

            // then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Test
    void 방향을_구할_수_없는_경우_예외를_발생한다() {
        // given
        final Position source = Position.of(File.A, Rank.ONE);
        final Position target = Position.of(File.H, Rank.SEVEN);

        // then
        assertThatThrownBy(() -> findByPosition(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 방향입니다");
    }
}
