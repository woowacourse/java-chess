package domain;

import chess.domain.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    void 입력받은_위치에_말이_있는_경우_말_반환_확인() {
        assertThat(Board.pieceValueOf("a1").toString()).isEqualTo("r");
    }

    @Test
    void 입력받은_위치에_말이_없는_경우() {
        assertThat(Board.pieceValueOf("c3")).isEqualTo(null);
    }
}
