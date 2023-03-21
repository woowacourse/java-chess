package chess.domain.piece;

import static chess.domain.piece.PieceTexture.BLACK_BISHOP;
import static chess.domain.piece.PieceTexture.WHITE_KING;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KingTest {

    private static final Position START_POSITION = Position.of(1, 1);
    private static final Position DESTINATION_POSITION = Position.of(2, 2);

    @Test
    @DisplayName("King 은 앞뒤양옆 1칸 이동 후 대각선으로 1칸 이동 가능하다.")
    void 이동_범위_확인() {
        King king = new King(Camp.WHITE);

        CheckablePaths checkablePaths = king.findCheckablePaths(START_POSITION);

        assertThat(checkablePaths.positionsSize()).isEqualTo(3);
    }

    @Test
    @DisplayName("King 은 항상 빈 위치로 이동 가능하다.")
    void 빈위치_이동_가능_확인() {
        assertThat(WHITE_KING.canMoveToEmpty(START_POSITION, DESTINATION_POSITION)).isTrue();
    }

    @Test
    @DisplayName("대상 말에 대한 공격 가능 여부를 반환한다.")
    void 공격_가능_확인_상대말() {
        assertThat(WHITE_KING.canAttack(START_POSITION, DESTINATION_POSITION, BLACK_BISHOP)).isTrue();
    }

}
