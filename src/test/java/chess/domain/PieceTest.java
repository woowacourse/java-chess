package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.King;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new King(Team.BLACK))
                .doesNotThrowAnyException();
    }
}
