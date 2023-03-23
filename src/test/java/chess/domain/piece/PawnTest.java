package chess.domain.piece;

import static chess.domain.Direction.NORTH_EAST;
import static chess.domain.Direction.NORTH_WEST;
import static chess.domain.Direction.SOUTH_EAST;
import static chess.domain.Direction.SOUTH_WEST;
import static chess.domain.piece.PieceFixture.BLACK_BISHOP;
import static chess.domain.piece.PieceFixture.BLACK_PAWN;
import static chess.domain.piece.PieceFixture.WHITE_PAWN;
import static chess.domain.piece.PieceFixture.WHITE_ROOK;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.CheckablePaths;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {

    private static final Position WHITE_START_POSITION = Position.of(2, 2);
    private static final Position BLACK_START_POSITION = Position.of(2, 7);

    @DisplayName("폰은 대각선이 아닌 이동 방향으로만 빈 위치로 이동 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"0:1", "0:2"}, delimiter = ':')
    void 빈위치_이동_가능_확인_참(int fileDisplacement, int rankDisplacement) {
        assertThat(WHITE_PAWN.canMoveToEmpty(WHITE_START_POSITION,
                Position.of(2 + fileDisplacement, 2 + rankDisplacement))).isTrue();
    }

    @DisplayName("대상 말에 대한 공격 가능 여부를 반환한다.")
    @Test
    void 공격_가능_확인_상대말() {
        assertThat(WHITE_ROOK.canAttack(WHITE_START_POSITION, WHITE_START_POSITION.findNextPosition(NORTH_EAST),
                BLACK_BISHOP)).isTrue();
    }

    @DisplayName("폰은 대각선이 아닌 이동 방향으로만 빈 위치로 이동 가능하다.")
    @Test
    void 빈위치_이동_가능_확인_거짓() {
        assertThat(WHITE_PAWN.canMoveToEmpty(WHITE_START_POSITION, Position.of(3, 3))).isFalse();
    }

    @DisplayName("하얀색 Pawn 테스트")
    @Nested
    class WhitePawnTest {

        @DisplayName("Pawn 은 처음에는 앞으로 1칸, 또는 2칸 이동 가능하다.")
        @Test
        void 초기_이동() {
            CheckablePaths checkablePaths = WHITE_PAWN.findCheckablePaths(WHITE_START_POSITION);

            assertThat(checkablePaths.positionsSize()).isEqualTo(5);
        }

        @DisplayName("Pawn 은 첫 이동이 아닌 경우, 앞으로 1칸 또는 공격의 경우만 이동 가능하다.")
        @Test
        void 이동_또는_공격() {
            CheckablePaths checkablePaths = WHITE_PAWN.findCheckablePaths(Position.of(2, 3));

            assertThat(checkablePaths.positionsSize()).isEqualTo(3);
        }

        @DisplayName("Pawn 이 하얀색 진영일 때는 북쪽 방향 대각선으로만 공격할 수 있다.")
        @Test
        void 공격_방향확인() {
            assertThat(WHITE_PAWN.canAttack(WHITE_START_POSITION, WHITE_START_POSITION.findNextPosition(NORTH_EAST),
                    BLACK_PAWN)).isTrue();
            assertThat(WHITE_PAWN.canAttack(WHITE_START_POSITION, WHITE_START_POSITION.findNextPosition(NORTH_WEST),
                    BLACK_PAWN)).isTrue();
            assertThat(WHITE_PAWN.canAttack(WHITE_START_POSITION, WHITE_START_POSITION.findNextPosition(SOUTH_EAST),
                    BLACK_PAWN)).isFalse();
            assertThat(WHITE_PAWN.canAttack(WHITE_START_POSITION, WHITE_START_POSITION.findNextPosition(SOUTH_WEST),
                    BLACK_PAWN)).isFalse();
        }

    }

    @DisplayName("검정색 Pawn 테스트")
    @Nested
    class BlackPawnTest {

        @DisplayName("Pawn 은 처음에는 앞으로 1칸, 또는 2칸 이동 가능하다.")
        @Test
        void 초기_이동() {
            CheckablePaths checkablePaths = BLACK_PAWN.findCheckablePaths(BLACK_START_POSITION);

            assertThat(checkablePaths.positionsSize()).isEqualTo(5);
        }

        @DisplayName("Pawn 은 첫 이동이 아닌 경우, 앞으로 1칸 또는 공격의 경우만 이동 가능하다.")
        @Test
        void 이동_또는_공격() {
            CheckablePaths checkablePaths = BLACK_PAWN.findCheckablePaths(Position.of(2, 3));

            assertThat(checkablePaths.positionsSize()).isEqualTo(3);
        }

        @DisplayName("Pawn 이 검정색 진영일 때는 남쪽 방향 대각선으로만 공격할 수 있다.")
        @Test
        void 공격_가능_확인_방향() {
            assertThat(BLACK_PAWN.canAttack(BLACK_START_POSITION, BLACK_START_POSITION.findNextPosition(NORTH_EAST),
                    WHITE_PAWN)).isFalse();
            assertThat(BLACK_PAWN.canAttack(BLACK_START_POSITION, BLACK_START_POSITION.findNextPosition(NORTH_WEST),
                    WHITE_PAWN)).isFalse();
            assertThat(BLACK_PAWN.canAttack(BLACK_START_POSITION, BLACK_START_POSITION.findNextPosition(SOUTH_EAST),
                    WHITE_PAWN)).isTrue();
            assertThat(BLACK_PAWN.canAttack(BLACK_START_POSITION, BLACK_START_POSITION.findNextPosition(SOUTH_WEST),
                    WHITE_PAWN)).isTrue();
        }
    }

}
