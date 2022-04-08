package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidPieceTest {

    @Test
    @DisplayName("ValidPiece 를 생성 할때 Team 이 NONE 이면 에러 발생")
    void constructor() {
        assertThatThrownBy(() -> new King(Team.NONE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 기물의 Team 은 BLACK 혹은 WHITE 만 가능합니다.");
    }
}
