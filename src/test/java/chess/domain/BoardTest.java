package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    @Test
    @DisplayName("보드 생성 실패")
    void constructFail() {
        LinkedHashMap<Position, Piece> positions = new LinkedHashMap<>();
        for (int i = 0; i < 63; i++) {
            positions.put(new Position("a", "b"), new Blank(PieceType.BLANK));
        }
        assertThatThrownBy(() -> {
            new Board(positions);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("옳바르지 않은 갯수입니다.");
    }
}
