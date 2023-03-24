package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.TeamColor;
import chess.domain.path.MovablePaths;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
public class PawnTest {

    @Nested
    @DisplayName("하얀색 Pawn 테스트")
    class WhitePawnTest {

        @Test
        @DisplayName("Pawn 은 처음에는 앞으로 1칸, 또는 2칸 이동 가능하다.")
        void 초기_이동() {
            Pawn pawn = new Pawn(TeamColor.WHITE);

            MovablePaths paths = pawn.findMovablePaths(Position.from("B2"));

            assertThat(paths.getTotalPositionCount()).isEqualTo(5);
        }

        @Test
        @DisplayName("Pawn 은 첫 이동이 아닌 경우, 앞으로 1칸 또는 공격의 경우만 이동 가능하다.")
        void 이동_또는_공격() {
            Pawn pawn = new Pawn(TeamColor.WHITE);

            MovablePaths paths = pawn.findMovablePaths(Position.from("B3"));

            assertThat(paths.getTotalPositionCount()).isEqualTo(3);
        }

        @ParameterizedTest
        @CsvSource(value = {"A3:true", "B3:false"}, delimiter = ':')
        @DisplayName("Pawn 은 앞으로 직진할 때만 이동 가능하다.")
        void 앞으로_직진이면_이동_가능(String dest, boolean isAble) {
            Pawn pawn = new Pawn(TeamColor.WHITE);

            assertThat(pawn.canMoveToEmptySquare(Position.from("A2"), Position.from(dest)))
                .isEqualTo(isAble);
        }

        @ParameterizedTest
        @CsvSource(value = {"A3:false", "B3:true"}, delimiter = ':')
        @DisplayName("Pawn 은 대각선 한 칸 이동일 때만 공격 가능하다.")
        void 대각선_공격_가능(String dest, boolean isAble) {
            Pawn pawn = new Pawn(TeamColor.WHITE);

            assertThat(pawn.canAttack(new Bishop(TeamColor.BLACK), Position.from("A2"),
                Position.from(dest))).isEqualTo(isAble);
        }

        @Test
        @DisplayName("Pawn 은 상대 말만 공격 가능하다.")
        void 상대말_공격_가능() {
            Pawn pawn = new Pawn(TeamColor.WHITE);

            assertThat(pawn.canAttack(new Bishop(TeamColor.WHITE), Position.from("A2"),
                Position.from("B3"))).isFalse();
        }

    }

    @Nested
    @DisplayName("검정색 Pawn 테스트")
    class BlackPawnTest {

        @Test
        @DisplayName("Pawn 은 처음에는 앞으로 1칸, 또는 2칸 이동 가능하다.")
        void 초기_이동() {
            Pawn pawn = new Pawn(TeamColor.BLACK);

            MovablePaths paths = pawn.findMovablePaths(Position.from("B7"));

            assertThat(paths.getTotalPositionCount()).isEqualTo(5);
        }

        @Test
        @DisplayName("Pawn 은 첫 이동이 아닌 경우, 앞으로 1칸 또는 공격의 경우만 이동 가능하다.")
        void 이동_또는_공격() {
            Pawn pawn = new Pawn(TeamColor.BLACK);

            MovablePaths paths = pawn.findMovablePaths(Position.from("B6"));

            assertThat(paths.getTotalPositionCount()).isEqualTo(3);
        }

        @ParameterizedTest
        @CsvSource(value = {"B6:true", "C6:false"}, delimiter = ':')
        @DisplayName("Pawn 은 앞으로 직진할 때만 이동 가능하다.")
        void 앞으로_직진이면_이동_가능(String dest, boolean isAble) {
            Pawn pawn = new Pawn(TeamColor.BLACK);

            assertThat(pawn.canMoveToEmptySquare(Position.from("B7"), Position.from(dest)))
                .isEqualTo(isAble);
        }

        @ParameterizedTest
        @CsvSource(value = {"B6:false", "A4:true"}, delimiter = ':')
        @DisplayName("Pawn 은 대각선 한 칸 이동일 때만 공격 가능하다.")
        void 대각선_공격_가능(String dest, boolean isAble) {
            Pawn pawn = new Pawn(TeamColor.BLACK);

            assertThat(pawn.canAttack(new Bishop(TeamColor.WHITE), Position.from("B5"),
                Position.from(dest))).isEqualTo(isAble);
        }

        @Test
        @DisplayName("Pawn 은 상대 말만 공격 가능하다.")
        void 상대말_공격_가능() {
            Pawn pawn = new Pawn(TeamColor.BLACK);

            assertThat(pawn.canAttack(new Bishop(TeamColor.BLACK), Position.from("B7"),
                Position.from("C6"))).isFalse();
        }

    }

}
