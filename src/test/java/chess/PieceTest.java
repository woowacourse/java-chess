package chess;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.PieceType.KING;
import static chess.Team.BLACK;

class PieceTest {

    @Test
    @DisplayName("생성 테스트")
    void create() {
        Assertions.assertThatCode(() -> new Piece(KING, BLACK))
                .doesNotThrowAnyException();
    }
}
