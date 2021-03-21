package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardGeneratorTest {

    @DisplayName("8 * 8 크기의 체스 보드를 생성한다.")
    @Test
    void generate() {
        Map<Coordinate, Cell> cells = ChessBoardGenerator.generateEmptyBoard();

        assertThat(cells).hasSize(File.values().length * Rank.values().length);
    }

    @DisplayName("8 * 8 크기의 모든 체스 보드는 현재 기물이 없는 빈 상태이다.")
    @Test
    void isAllEmptyCells() {
        boolean isAllEmptyCells = ChessBoardGenerator.generateEmptyBoard()
                .values()
                .stream()
                .allMatch(Cell::isEmpty);

        assertThat(isAllEmptyCells).isTrue();
    }
}
