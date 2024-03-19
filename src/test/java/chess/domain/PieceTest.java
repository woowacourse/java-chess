package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.PieceType.KING;
import static chess.domain.Team.BLACK;

class PieceTest {

    @Test
    @DisplayName("생성 테스트")
    void create() {
        Assertions.assertThatCode(() -> new Piece(KING, BLACK))
                .doesNotThrowAnyException();
    }
}
