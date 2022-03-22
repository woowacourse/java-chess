package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CellTest {

    @DisplayName("칸을 생성할 수 있다.")
    @Test
    void create() {
        Cell cell = Cell.from(new Rook());
        assertThat(cell).isNotNull();
    }
}