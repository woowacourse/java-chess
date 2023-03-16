package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Path;
import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.List;
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

            List<Path> movePaths = pawn.findAllPaths(Position.of(2, 2));

            assertThat(movePaths.size()).isEqualTo(4);
        }

        @Test
        @DisplayName("Pawn 은 첫 이동이 아닌 경우, 앞으로 1칸 또는 공격의 경우만 이동 가능하다.")
        void 이동_또는_공격() {
            Pawn pawn = new Pawn(TeamColor.WHITE);

            List<Path> movePaths = pawn.findAllPaths(Position.of(2, 3));

            assertThat(movePaths.size()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("검정색 Pawn 테스트")
    class BlackPawnTest {

        @Test
        @DisplayName("Pawn 은 처음에는 앞으로 1칸, 또는 2칸 이동 가능하다.")
        void 초기_이동() {
            Pawn pawn = new Pawn(TeamColor.BLACK);

            List<Path> movePaths = pawn.findAllPaths(Position.of(2, 7));

            assertThat(movePaths.size()).isEqualTo(4);
        }

        @Test
        @DisplayName("Pawn 은 첫 이동이 아닌 경우, 앞으로 1칸 또는 공격의 경우만 이동 가능하다.")
        void 이동_또는_공격() {
            Pawn pawn = new Pawn(TeamColor.BLACK);

            List<Path> movePaths = pawn.findAllPaths(Position.of(2, 3));

            assertThat(movePaths.size()).isEqualTo(3);
        }
    }

}
