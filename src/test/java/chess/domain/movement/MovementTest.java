package chess.domain.movement;

import chess.domain.piece.movement.Movement;
import chess.domain.position.RelativePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovementTest {

    @Nested
    @DisplayName("킹 isMobile 테스트")
    class kingTest {
        private final Movement movement = Movement.KING;

        @ParameterizedTest
        @DisplayName("십자가, 대각선 방향으로 한 칸만 움직임이 가능하다.")
        @CsvSource({"0,1", "1,1", "1,0", "1,-1", "0,-1", "-1,-1", "-1,0", "-1,1"})
        void kingValidMobilityTest(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(movement.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("두 칸 이상의 움직임에 대해선 거짓을 반환한다.")
        @CsvSource({"0,2", "2,2", "2,0", "2,-2", "0,-2", "-2,-2", "-2,0", "-2,2"})
        void kingInvalidMobilityTest1(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertFalse(movement.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("L자 모양의 방향으로 움직임일 수 없다.")
        @CsvSource({"1,2", "2,1", "2,-1", "1,-2", "-1,-2", "-2,-1", "-2,1", "-1,2"})
        void kingInvalidMobilityTest2(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertFalse(movement.isMobile(relativePosition));
        }
    }

    @Nested
    @DisplayName("폰 isMobile 테스트")
    class pawnTest {
        private final Movement movement = Movement.PAWN;


        @Test
        @DisplayName("위로 한 칸 전진할 수 있다.")
        void pawnValidMobilityTest() {
            RelativePosition relativePosition = new RelativePosition(0, 1);

            assertTrue(movement.isMobile(relativePosition));
        }

        @Test
        @DisplayName("위로 한 칸이 아닌 움직임에 대해선 거짓을 반환한다.")
        void pawnInvalidMobilityTest() {
            RelativePosition relativePosition = new RelativePosition(0, 2);

            assertFalse(movement.isMobile(relativePosition));
        }


    }

    @Nested
    @DisplayName("퀸 isMobile 테스트")
    class QueenTest {
        private final Movement movement = Movement.QUEEN;

        @ParameterizedTest
        @DisplayName("대각선, 십자가 방향으로 1칸 움직임일 수 있다.")
        @CsvSource({"0,1", "1,1", "1,0", "1,-1", "0,-1", "-1,-1", "-1,0", "-1,1"})
        void queenValidMobilityTest1(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(movement.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("대각선, 십자가 방향으로 7칸 움직임일 수 있다.")
        @CsvSource({"0,7", "7,7", "7,0", "7,-7", "0,-7", "-7,-7", "-7,0", "-7,7"})
        void queenValidMobilityTest2(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(movement.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("L자 모양의 방향으로 움직임일 수 없다.")
        @CsvSource({"1,2", "2,1", "2,-1", "1,-2", "-1,-2", "-2,-1", "-2,1", "-1,2"})
        void queenInvalidMobilityTest(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertFalse(movement.isMobile(relativePosition));
        }
    }

    @Nested
    @DisplayName("비숍 isMobile 테스트")
    class BishopTest {
        private final Movement movement = Movement.BISHOP;

        @ParameterizedTest
        @DisplayName("대각선 방향으로 1칸 움직임일 수 있다.")
        @CsvSource({"1,1", "1,-1", "-1,-1", "-1,1"})
        void bishopValidMobilityTest1(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(movement.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("대각선 방향으로 얼마든지 움직임일 수 있다.")
        @CsvSource({"7,7", "7,-7", "-7,-7", "-7,7"})
        void bishopValidMobilityTest2(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(movement.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("십자가 방향으로 움직임일 수 없다.")
        @CsvSource({"0,1", "1,0", "0,-1", "-1,0"})
        void bishopInvalidMobilityTest1(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertFalse(movement.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("L자 모양의 방향으로 움직임일 수 없다.")
        @CsvSource({"1,2", "2,1", "2,-1", "1,-2", "-1,-2", "-2,-1", "-2,1", "-1,2"})
        void bishopInvalidMobilityTest2(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertFalse(movement.isMobile(relativePosition));
        }
    }

    @Nested
    @DisplayName("나이트 isMobile 테스트")
    class KnightTest {
        private final Movement movement = Movement.KNIGHT;

        @ParameterizedTest
        @DisplayName("L자 모양의 방향으로 움직임일 수 있다.")
        @CsvSource({"1,2", "2,1", "2,-1", "1,-2", "-1,-2", "-2,-1", "-2,1", "-1,2"})
        void knightValidMobilityTest(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(movement.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("대각선 방향으로 움직임일 수 없다.")
        @CsvSource({"1,1", "1,-1", "-1,-1", "-1,1"})
        void knightInvalidMobilityTest1(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertFalse(movement.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("십자가 방향으로 움직임일 수 없다.")
        @CsvSource({"0,1", "1,0", "0,-1", "-1,0"})
        void knightInvalidMobilityTest2(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertFalse(movement.isMobile(relativePosition));
        }
    }

    @Nested
    @DisplayName("룩 isMobile 테스트")
    class RookTest {
        private final Movement movement = Movement.ROOK;

        @ParameterizedTest
        @DisplayName("십자가 방향으로 움직임일 수 있다.")
        @CsvSource({"0,1", "1,0", "0,-1", "-1,0", "0,7", "7,0", "0,-7", "-7,0"})
        void bishopInvalidMobilityTest(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(movement.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("대각선 방향으로 움직임일 수 없다.")
        @CsvSource({"1,1", "1,-1", "-1,-1", "-1,1"})
        void bishopValidMobilityTest2(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertFalse(movement.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("L자 모양의 방향으로 움직임일 수 없다.")
        @CsvSource({"1,2", "2,1", "2,-1", "1,-2", "-1,-2", "-2,-1", "-2,1", "-1,2"})
        void rookInvalidMobilityTest2(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertFalse(movement.isMobile(relativePosition));
        }
    }
}
