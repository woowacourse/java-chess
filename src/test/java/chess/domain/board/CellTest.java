package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CellTest {
    @DisplayName("Cell 생성 테스트")
    @Test
    void createCell() {
        Coordinate coordinate = new Coordinate(File.C, Rank.EIGHT);
        
        assertThatCode(() -> new Cell(coordinate))
            .doesNotThrowAnyException();
    }
}