package chess.domain;

import static chess.domain.PieceFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("기물 생성 테스트")
    @Test
    void construct() {
        assertDoesNotThrow(() -> new Pawn(Team.BLACK, "p"));
    }

    @DisplayName("적인지 판별하는 메서드 테스트")
    @Test
    void isEnemy() {
        assertThat(WHITE_PAWN.isEnemy(WHITE_BISHOP)).isFalse();
    }
}
