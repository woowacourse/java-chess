package chess.domain.piece;

import static chess.domain.piece.PieceTexture.BLACK_BISHOP;
import static chess.domain.piece.PieceTexture.WHITE_ROOK;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    private static final Position START_POSITION = Position.of(1, 1);
    private static final Position DESTINATION_POSITION = Position.of(1, 4);

    @Test
    @DisplayName("Rook 은 앞뒤양옆으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Rook rook = new Rook(Camp.WHITE);

        CheckablePaths checkablePaths = rook.findCheckablePaths(START_POSITION);

        assertThat(checkablePaths.positionsSize()).isEqualTo(14);
    }

    @Test
    @DisplayName("Queen 은 항상 빈 위치로 이동 가능하다.")
    void 빈위치_이동_가능_확인() {
        assertThat(WHITE_ROOK.canMoveToEmpty(START_POSITION, DESTINATION_POSITION)).isTrue();
    }

    @Test
    @DisplayName("대상 말에 대한 공격 가능 여부를 반환한다.")
    void 공격_가능_확인_상대말() {
        assertThat(WHITE_ROOK.canAttack(START_POSITION, DESTINATION_POSITION, BLACK_BISHOP)).isTrue();
    }
}
