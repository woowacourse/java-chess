package chess.domain.piece;

import chess.domain.RelativePosition;
import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {

    @Nested
    @DisplayName("퀸은 팀과 관계 없이 모든 방향으로 ")
    class QueenMove {
        private final Queen whiteQueen = new Queen(Team.WHITE);
        private final Queen blackQueen = new Queen(Team.BLACK);

        @ParameterizedTest
        @DisplayName("1칸 이동할 수 있다.")
        @CsvSource({"0,1", "1,1", "1,0", "1,-1", "0,-1", "-1,-1", "-1,0", "-1,1"})
        void oneBlockTest(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(whiteQueen.isMobile(relativePosition));
            assertTrue(blackQueen.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("2칸 이동할 수 있다.")
        @CsvSource({"0,2", "2,2", "2,0", "2,-2", "0,-2", "-2,-2", "-2,0", "-2,2"})
        void twoBlockTest(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(whiteQueen.isMobile(relativePosition));
            assertTrue(blackQueen.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("3칸 이동할 수 있다.")
        @CsvSource({"0,3", "3,3", "3,0", "3,-3", "0,-3", "-3,-3", "-3,0", "-3,3"})
        void threeBlockTest(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(whiteQueen.isMobile(relativePosition));
            assertTrue(blackQueen.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("4칸 이동할 수 있다.")
        @CsvSource({"0,4", "4,4", "4,0", "4,-4", "0,-4", "-4,-4", "-4,0", "-4,4"})
        void fourBlockTest(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(whiteQueen.isMobile(relativePosition));
            assertTrue(blackQueen.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("5칸 이동할 수 있다.")
        @CsvSource({"0,5", "5,5", "5,0", "5,-5", "0,-5", "-5,-5", "-5,0", "-5,5"})
        void fiveBlockTest(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(whiteQueen.isMobile(relativePosition));
            assertTrue(blackQueen.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("6칸 이동할 수 있다.")
        @CsvSource({"0,6", "6,6", "6,0", "6,-6", "0,-6", "-6,-6", "-6,0", "-6,6"})
        void sixBlockTest(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(whiteQueen.isMobile(relativePosition));
            assertTrue(blackQueen.isMobile(relativePosition));
        }

        @ParameterizedTest
        @DisplayName("7칸 이동할 수 있다.")
        @CsvSource({"0,7", "7,7", "7,0", "7,-7", "0,-7", "-7,-7", "-7,0", "-7,7"})
        void sevenBlockTest(int x, int y) {
            RelativePosition relativePosition = new RelativePosition(x, y);

            assertTrue(whiteQueen.isMobile(relativePosition));
            assertTrue(blackQueen.isMobile(relativePosition));
        }
    }
}
