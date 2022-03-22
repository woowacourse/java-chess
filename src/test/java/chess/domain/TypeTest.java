package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TypeTest {

    @Test
    @DisplayName("Type에 킹, 퀸, 비숍, 나이트, 룩, 폰이 있는지 확인한다.")
    void contain() {
        assertThat(Type.values())
            .containsOnly(Type.KING, Type.QUEEN, Type.BISHOP, Type.KNIGHT, Type.ROOK, Type.PAWN);
    }

}
