package chess.util;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
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
            Position from = Position.of(File.E,  Rank.FIVE);
            Position to = Position.of(File.E, Rank.ONE);

            List<Position> actual = PositionUtil.positionsStraightBetween(from, to);
            List<Position> expected = List.of(
                    Position.of(File.E, Rank.FOUR),
                    Position.of(File.E, Rank.THREE),
                    Position.of(File.E, Rank.TWO));

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 좌우() {
            Position from = Position.of(File.E,  Rank.FIVE);
            Position to = Position.of(File.A,  Rank.FIVE);

            List<Position> actual = PositionUtil.positionsStraightBetween(from, to);
            List<Position> expected = List.of(
                    Position.of(File.D,  Rank.FIVE),
                    Position.of(File.C,  Rank.FIVE),
                    Position.of(File.B,  Rank.FIVE));

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 대각선() {
            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.E,  Rank.FIVE);

            List<Position> actual = PositionUtil.positionsStraightBetween(from, to);
            List<Position> expected = List.of(
                    Position.of(File.B, Rank.TWO),
                    Position.of(File.C, Rank.THREE),
                    Position.of(File.D, Rank.FOUR));

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 인접하면_빈_리스트() {

            Position from = Position.of(File.E,  Rank.FIVE);
            Position to = Position.of(File.E, Rank.FOUR);

            List<Position> actual = PositionUtil.positionsStraightBetween(from, to);
            List<Position> expected = List.of();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void 일직선이_아니면_빈_리스트() {
            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.B, Rank.THREE);

            List<Position> actual = PositionUtil.positionsStraightBetween(from, to);
            List<Position> expected = List.of();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class IsStraightPathTest {

        @Test
        void 일직선이면_참() {
            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.F, Rank.SIX);

            boolean actual = PositionUtil.isStraightPath(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 일직선이_아니면_거짓() {
            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.C, Rank.FOUR);

            boolean actual = PositionUtil.isStraightPath(from, to);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class IsDiagonalTest {

        @Test
        void 대각선이면_참() {
            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.F, Rank.SIX);

            boolean actual = PositionUtil.isDiagonal(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 대각선이_아니면_거짓() {
            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.B, Rank.ONE);

            boolean actual = PositionUtil.isDiagonal(from, to);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class IsHorizontalOrVerticalTest {

        @Test
        void 수직이면_참() {
            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.F, Rank.ONE);

            boolean actual = PositionUtil.isHorizontalOrVertical(from, to);

            assertThat(actual).isTrue();
        }

        @Test
        void 수직이_아니면_거짓() {
            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.B, Rank.TWO);

            boolean actual = PositionUtil.isHorizontalOrVertical(from, to);

            assertThat(actual).isFalse();
        }
    }
}