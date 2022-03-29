package chess.util;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class PositionUtilTest {

    @DisplayName("positionsStraightBetween 메서드는 대상 위치 사이에 존재하는 위치들의 리스트를 반환")
    @Nested
    class PositionsStraightBetweenTest {

        @Test
        void 상하() {
            Position from = Position.of(4, 4);
            Position to = Position.of(4, 0);

            List<Position> actual = PositionUtil.positionsStraightBetween(from, to);
            List<Position> expected = List.of(
                    Position.of(4, 3),
                    Position.of(4, 2),
                    Position.of(4, 1));

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 좌우() {
            Position from = Position.of(4, 4);
            Position to = Position.of(0, 4);

            List<Position> actual = PositionUtil.positionsStraightBetween(from, to);
            List<Position> expected = List.of(
                    Position.of(3, 4),
                    Position.of(2, 4),
                    Position.of(1, 4));

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 대각선() {
            Position from = Position.of(0, 0);
            Position to = Position.of(4, 4);

            List<Position> actual = PositionUtil.positionsStraightBetween(from, to);
            List<Position> expected = List.of(
                    Position.of(1, 1),
                    Position.of(2, 2),
                    Position.of(3, 3));

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 인접하면_빈_리스트() {
            Position from = Position.of(4, 4);
            Position to = Position.of(4, 3);

            List<Position> actual = PositionUtil.positionsStraightBetween(from, to);
            List<Position> expected = List.of();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 일직선이_아니면_빈_리스트() {
            Position from = Position.of(0, 0);
            Position to = Position.of(1, 2);

            List<Position> actual = PositionUtil.positionsStraightBetween(from, to);
            List<Position> expected = List.of();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class IsStraightPathTest {

        @Test
        void 일직선이면_참() {
            Position from = Position.of(0, 0);
            Position to = Position.of(5, 5);

            boolean actual = PositionUtil.isStraightPath(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 일직선이_아니면_거짓() {
            Position from = Position.of(0, 0);
            Position to = Position.of(2, 3);

            boolean actual = PositionUtil.isStraightPath(from, to);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class IsDiagonalTest {

        @Test
        void 대각선이면_참() {
            Position from = Position.of(0, 0);
            Position to = Position.of(5, 5);

            boolean actual = PositionUtil.isDiagonal(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 대각선이_아니면_거짓() {
            Position from = Position.of(0, 0);
            Position to = Position.of(1, 0);

            boolean actual = PositionUtil.isDiagonal(from, to);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class IsHorizontalOrVerticalTest {

        @Test
        void 수직이면_참() {
            Position from = Position.of(0, 0);
            Position to = Position.of(5, 0);

            boolean actual = PositionUtil.isHorizontalOrVertical(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 수직이_아니면_거짓() {
            Position from = Position.of(0, 0);
            Position to = Position.of(1, 1);

            boolean actual = PositionUtil.isHorizontalOrVertical(from, to);

            assertThat(actual).isFalse();
        }
    }
}