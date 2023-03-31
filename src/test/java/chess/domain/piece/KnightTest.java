package chess.domain.piece;

import static chess.domain.piece.PieceFixture.BLACK_BISHOP;
import static chess.domain.piece.PieceFixture.WHITE_KNIGHT;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    private static final Position START_POSITION = Position.of(2, 2);
    private static final Position DESTINATION_POSITION = Position.of(4, 3);

    @DisplayName("Knight 은 앞뒤양옆 1칸 이동 후 대각선으로 1칸 이동 가능하다.")
    @Test
    void 이동_범위_확인() {
        Knight knight = new Knight(Camp.WHITE);

        CheckablePaths checkablePaths = knight.findCheckablePaths(START_POSITION);

        assertThat(checkablePaths.positionsSize()).isEqualTo(4);
    }

    @DisplayName("Knight 은 항상 빈 위치로 이동 가능하다.")
    @Test
    void 빈위치_이동_가능_확인() {
        assertThat(WHITE_KNIGHT.canMoveToEmpty(START_POSITION, DESTINATION_POSITION)).isTrue();
    }

    @DisplayName("대상 말에 대한 공격 가능 여부를 반환한다.")
    @Test
    void 공격_가능_확인_상대말() {
        assertThat(WHITE_KNIGHT.canAttack(START_POSITION, DESTINATION_POSITION, BLACK_BISHOP)).isTrue();
    }

}
