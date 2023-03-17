package chess.domain.piece;

import static chess.domain.Direction.NORTH_EAST;
import static chess.domain.Direction.NORTH_WEST;
import static chess.domain.Direction.SOUTH_EAST;
import static chess.domain.Direction.SOUTH_WEST;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.Position;
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
            Pawn pawn = new Pawn(Camp.WHITE);

            CheckablePaths checkablePaths = pawn.findCheckablePaths(Position.of(2, 2));

            assertThat(checkablePaths.positionsSize()).isEqualTo(5);
        }

        @Test
        @DisplayName("Pawn 은 첫 이동이 아닌 경우, 앞으로 1칸 또는 공격의 경우만 이동 가능하다.")
        void 이동_또는_공격() {
            Pawn pawn = new Pawn(Camp.WHITE);

            CheckablePaths checkablePaths = pawn.findCheckablePaths(Position.of(2, 3));

            assertThat(checkablePaths.positionsSize()).isEqualTo(3);
        }

        @Test
        @DisplayName("Pawn 이 하얀색 진영일 때는 북동, 북서 방향 대각선으로만 공격할 수 있다.")
        void 공격_방향확인() {
            Pawn pawn = new Pawn(Camp.WHITE);

            Position start = Position.of(3, 3);

            assertThat(pawn.isAttack(start, start.findNextPosition(NORTH_EAST))).isTrue();
            assertThat(pawn.isAttack(start, start.findNextPosition(NORTH_WEST))).isTrue();
            assertThat(pawn.isAttack(start, start.findNextPosition(SOUTH_EAST))).isFalse();
            assertThat(pawn.isAttack(start, start.findNextPosition(SOUTH_WEST))).isFalse();
        }

    }

    @Nested
    @DisplayName("검정색 Pawn 테스트")
    class BlackPawnTest {

        @Test
        @DisplayName("Pawn 은 처음에는 앞으로 1칸, 또는 2칸 이동 가능하다.")
        void 초기_이동() {
            Pawn pawn = new Pawn(Camp.BLACK);

            CheckablePaths checkablePaths = pawn.findCheckablePaths(Position.of(2, 7));

            assertThat(checkablePaths.positionsSize()).isEqualTo(5);
        }

        @Test
        @DisplayName("Pawn 은 첫 이동이 아닌 경우, 앞으로 1칸 또는 공격의 경우만 이동 가능하다.")
        void 이동_또는_공격() {
            Pawn pawn = new Pawn(Camp.BLACK);

            CheckablePaths checkablePaths = pawn.findCheckablePaths(Position.of(2, 3));

            assertThat(checkablePaths.positionsSize()).isEqualTo(3);
        }

        @Test
        @DisplayName("Pawn 이 검정색 진영일 때는 북동, 북서 방향 대각선으로만 공격할 수 있다.")
        void 공격_방향확인() {
            Pawn pawn = new Pawn(Camp.BLACK);

            Position start = Position.of(3, 3);

            assertThat(pawn.isAttack(start, start.findNextPosition(NORTH_EAST))).isFalse();
            assertThat(pawn.isAttack(start, start.findNextPosition(NORTH_WEST))).isFalse();
            assertThat(pawn.isAttack(start, start.findNextPosition(SOUTH_EAST))).isTrue();
            assertThat(pawn.isAttack(start, start.findNextPosition(SOUTH_WEST))).isTrue();
        }
    }

}
