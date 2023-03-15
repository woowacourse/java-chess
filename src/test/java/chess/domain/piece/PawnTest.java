package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.File.B;
import static chess.domain.File.C;
import static chess.domain.File.E;
import static chess.domain.Rank.FIVE;
import static chess.domain.Rank.FOUR;
import static chess.domain.Rank.SEVEN;
import static chess.domain.Rank.SIX;
import static chess.domain.Rank.THREE;
import static chess.domain.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    @Nested
    @DisplayName("Black Pawn 테스트")
    class BlackPawnTest {

        @Test
        @DisplayName("초기 위치에서 두 칸을 전진할 경우, 지나갈 경로를 얻는다.")
        void getPassingPathTest_initial_2_moving() {
            final Piece pawn = new Pawn(B, SEVEN, Color.BLACK);

            final List<Position> path = pawn.getPassingPositions(new Position(B, Rank.FIVE));

            assertThat(path).containsExactly(new Position(B, SIX));
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 한 칸을 전진할 경우, 지나갈 경로를 얻는다.")
        void getPassingPathTest_nonInitial_1_moving() {
            final Piece pawn = new Pawn(B, SIX, Color.BLACK);

            final List<Position> path = pawn.getPassingPositions(new Position(B, Rank.FIVE));

            assertThat(path).isEmpty();
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 대각선 한 칸을 이동할 경우, 지나갈 경로를 얻는다.")
        void getPassingPathTest_nonInitial_diagonal_1_moving() {
            final Piece pawn = new Pawn(B, SIX, Color.BLACK);

            final List<Position> path = pawn.getPassingPositions(new Position(C, Rank.FIVE));

            assertThat(path).isEmpty();
        }


        @Test
        @DisplayName("초기 위치에서 이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
        void getPassingPathFailTest_initial() {
            final Piece pawn = new Pawn(B, SEVEN, Color.BLACK);

            assertThatThrownBy(() -> pawn.getPassingPositions(new Position(E, SIX)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치로 이동할 수 없습니다.");
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
        void getPassingPathFailTest_nonInitial() {
            final Piece pawn = new Pawn(B, SIX, Color.BLACK);

            assertThatThrownBy(() -> pawn.getPassingPositions(new Position(B, FOUR)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치로 이동할 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("White Pawn 테스트")
    class WhitePawnTest {

        @Test
        @DisplayName("초기 위치에서 두 칸을 전진할 경우, 지나갈 경로를 얻는다.")
        void getPassingPathTest_initial_2_moving() {
            final Piece pawn = new Pawn(B, TWO, Color.WHITE);

            final List<Position> path = pawn.getPassingPositions(new Position(B, FOUR));

            assertThat(path).containsExactly(new Position(B, THREE));
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 한 칸을 전진할 경우, 지나갈 경로를 얻는다.")
        void getPassingPathTest_nonInitial_1_moving() {
            final Piece pawn = new Pawn(B, THREE, Color.WHITE);

            final List<Position> path = pawn.getPassingPositions(new Position(B, FOUR));

            assertThat(path).isEmpty();
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 대각선 한 칸을 이동할 경우, 지나갈 경로를 얻는다.")
        void getPassingPathTest_nonInitial_diagonal_1_moving() {
            final Piece pawn = new Pawn(B, THREE, Color.WHITE);

            final List<Position> path = pawn.getPassingPositions(new Position(C, FOUR));

            assertThat(path).isEmpty();
        }

        @Test
        @DisplayName("초기 위치에서 이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
        void getPassingPathFailTest_initial() {
            final Piece pawn = new Pawn(B, TWO, Color.WHITE);

            assertThatThrownBy(() -> pawn.getPassingPositions(new Position(E, SIX)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치로 이동할 수 없습니다.");
        }

        @Test
        @DisplayName("초기 위치가 아닐 때 이동할 수 없는 위치가 입력되면, 예외가 발생한다.")
        void getPassingPathFailTest_nonInitial() {
            final Piece pawn = new Pawn(B, THREE, Color.WHITE);

            assertThatThrownBy(() -> pawn.getPassingPositions(new Position(B, FIVE)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 위치로 이동할 수 없습니다.");
        }
    }
}
