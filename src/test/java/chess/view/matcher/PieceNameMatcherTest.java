package chess.view.matcher;

import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceNameMatcherTest {

    @DisplayName("PieceNameMatcher는 모든 PieceType에 대한 Name 매칭 정보를 포함한다")
    @Test
    void matchPieceTypes() {
        for (PieceType value : PieceType.values()) {
            assertThat(PieceNameMatcher.isPresentType(value)).isTrue();
        }
    }
}
