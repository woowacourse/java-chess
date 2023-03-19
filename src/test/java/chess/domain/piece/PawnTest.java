package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.TeamColor;
import chess.domain.path.MovablePaths;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class PawnTest {

    @Nested
    @DisplayName("Pawn 공통 기능 테스트")
    class PawnCommonTest {

        @Test
        @DisplayName("Pawn 이 가려는 방향이 대각선이면 공격 방향이다.")
        void 대각선_이동_공격() {
            Pawn pawn = new Pawn(TeamColor.WHITE);

            assertThat(pawn.isAttack(Position.of(1, 2), Position.of(2, 3))).isTrue();
        }

        @Test
        @DisplayName("Pawn 이 가려는 방향이 대각선이 아니면 공격이 아니다.")
        void 대각선_외의_경로_이동() {
            Pawn pawn = new Pawn(TeamColor.WHITE);

            assertThat(pawn.isNotAttack(Position.of(1, 2), Position.of(1, 3))).isTrue();
        }

    }

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

            MovablePaths paths = pawn.findMovablePaths(Position.of(2, 3));

            assertThat(paths.getTotalPositionCount()).isEqualTo(3);
        }
    }

}
