package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.King;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTest {
    @Test
    @DisplayName("비어있는 그리드이면 true를 반환하는지")
    void isBlankGrid() {
        Grid grid = new Grid(new Blank());
        assertThat(grid.isBlank()).isTrue();
    }

    @Test
    @DisplayName("기물이 있는 그리드이면 false를 반환하는지")
    void hasPieceOnGrid() {
        Grid grid = new Grid(new King(Color.BLACK));
        assertThat(grid.isBlank()).isFalse();
    }
}
