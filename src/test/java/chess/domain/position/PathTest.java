package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PathTest {

    @Nested
    @DisplayName("시작 포인트와 도착 포인트 사이의 포인트들을 구할 수 있다.")
    class GetBetweenPositionsTest {
        @Test
        @DisplayName("A1 -> C3")
        void A1ToC3PointsTest() {
            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.C, Rank.THREE);

            Path path = new Path();
            List<Position> betweenPositions = path.getBetweenPositions(from, to);

            assertThat(betweenPositions).contains(Position.of(File.B, Rank.TWO));
        }

        @Test
        @DisplayName("C1 -> A3")
        void C1ToA3PointsTest() {
            Position from = Position.of(File.C, Rank.ONE);
            Position to = Position.of(File.A, Rank.THREE);

            Path path = new Path();
            List<Position> betweenPositions = path.getBetweenPositions(from, to);

            assertThat(betweenPositions).contains(Position.of(File.B, Rank.TWO));
        }

        @Test
        @DisplayName("D4 -> A1")
        void D4ToA1PointsTest() {
            Position from = Position.of(File.D, Rank.FOUR);
            Position to = Position.of(File.A, Rank.ONE);

            Path path = new Path();
            List<Position> betweenPositions = path.getBetweenPositions(from, to);

            assertThat(betweenPositions).contains(Position.of(File.B, Rank.TWO), Position.of(File.C, Rank.THREE));
        }

        @Test
        @DisplayName("A1 -> A4")
        void A1ToA4PointsTest() {
            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.A, Rank.FOUR);

            Path path = new Path();
            List<Position> betweenPositions = path.getBetweenPositions(from, to);

            assertThat(betweenPositions).contains(Position.of(File.A, Rank.TWO), Position.of(File.A, Rank.THREE));
        }

        @Test
        @DisplayName("A1 -> D1")
        void A1ToD1PointsTest() {
            Position from = Position.of(File.A, Rank.ONE);
            Position to = Position.of(File.D, Rank.ONE);

            Path path = new Path();
            List<Position> betweenPositions = path.getBetweenPositions(from, to);

            assertThat(betweenPositions).contains(Position.of(File.B, Rank.ONE), Position.of(File.C, Rank.ONE));
        }
    }
}