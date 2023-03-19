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

            MovablePaths paths = pawn.findMovablePaths(Position.of(2, 2));

            assertThat(paths.getTotalPositionCount()).isEqualTo(5);
        }

        @Test
        @DisplayName("Pawn 은 첫 이동이 아닌 경우, 앞으로 1칸 또는 공격의 경우만 이동 가능하다.")
        void 이동_또는_공격() {
            Pawn pawn = new Pawn(TeamColor.WHITE);

            MovablePaths paths = pawn.findMovablePaths(Position.of(2, 3));

            assertThat(paths.getTotalPositionCount()).isEqualTo(3);
        }

        @ParameterizedTest
        @CsvSource(value = {"1:3:true", "2:3:false"}, delimiter = ':')
        @DisplayName("Pawn 은 앞으로 직진할 때만 이동 가능하다.")
        void 앞으로_직진이면_이동_가능(int file, int rank, boolean isAble) {
            Pawn pawn = new Pawn(TeamColor.WHITE);

            assertThat(pawn.canMoveToEmptySquare(Position.of(1, 2), Position.of(file, rank)))
                .isEqualTo(isAble);
        }

        @ParameterizedTest
        @CsvSource(value = {"1:3:false", "2:3:true"}, delimiter = ':')
        @DisplayName("Pawn 은 대각선 한 칸 이동일 때만 공격 가능하다.")
        void 대각선_공격_가능(int file, int rank, boolean isAble) {
            Pawn pawn = new Pawn(TeamColor.WHITE);

            assertThat(pawn.canAttack(new Bishop(TeamColor.BLACK), Position.of(1, 2),
                Position.of(file, rank))).isEqualTo(isAble);
        }

        @Test
        @DisplayName("Pawn 은 상대 말만 공격 가능하다.")
        void 상대말_공격_가능() {
            Pawn pawn = new Pawn(TeamColor.WHITE);

            assertThat(pawn.canAttack(new Bishop(TeamColor.WHITE), Position.of(1, 2),
                Position.of(2, 3))).isFalse();
        }

    }

    @Nested
    @DisplayName("검정색 Pawn 테스트")
    class BlackPawnTest {

        @Test
        @DisplayName("Pawn 은 처음에는 앞으로 1칸, 또는 2칸 이동 가능하다.")
        void 초기_이동() {
            Pawn pawn = new Pawn(TeamColor.BLACK);

            MovablePaths paths = pawn.findMovablePaths(Position.of(2, 7));

            assertThat(paths.getTotalPositionCount()).isEqualTo(5);
        }

        @Test
        @DisplayName("Pawn 은 첫 이동이 아닌 경우, 앞으로 1칸 또는 공격의 경우만 이동 가능하다.")
        void 이동_또는_공격() {
            Pawn pawn = new Pawn(TeamColor.BLACK);

            MovablePaths paths = pawn.findMovablePaths(Position.of(2, 6));

            assertThat(paths.getTotalPositionCount()).isEqualTo(3);
        }

        @ParameterizedTest
        @CsvSource(value = {"2:6:true", "3:6:false"}, delimiter = ':')
        @DisplayName("Pawn 은 앞으로 직진할 때만 이동 가능하다.")
        void 앞으로_직진이면_이동_가능(int file, int rank, boolean isAble) {
            Pawn pawn = new Pawn(TeamColor.BLACK);

            assertThat(pawn.canMoveToEmptySquare(Position.of(2, 7), Position.of(file, rank)))
                .isEqualTo(isAble);
        }

        @ParameterizedTest
        @CsvSource(value = {"2:6:false", "1:4:true"}, delimiter = ':')
        @DisplayName("Pawn 은 대각선 한 칸 이동일 때만 공격 가능하다.")
        void 대각선_공격_가능(int file, int rank, boolean isAble) {
            Pawn pawn = new Pawn(TeamColor.BLACK);

            assertThat(pawn.canAttack(new Bishop(TeamColor.WHITE), Position.of(2, 5),
                Position.of(file, rank))).isEqualTo(isAble);
        }

        @Test
        @DisplayName("Pawn 은 상대 말만 공격 가능하다.")
        void 상대말_공격_가능() {
            Pawn pawn = new Pawn(TeamColor.BLACK);

            assertThat(pawn.canAttack(new Bishop(TeamColor.BLACK), Position.of(2, 7),
                Position.of(3, 6))).isFalse();
        }

    }

}
