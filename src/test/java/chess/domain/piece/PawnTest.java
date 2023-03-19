package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.path.MovablePaths;
import chess.domain.position.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
