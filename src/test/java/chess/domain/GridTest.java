package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTest {
    @Test
    @DisplayName("같은 색깔의 기물을 갖고 있으면 true 를 반환하는지")
    void isBlankGrid() {
        Grid grid = new Grid(new Pawn(Color.BLACK));
        assertThat(grid.hasPieceOf(Color.BLACK)).isTrue();
    }

    @Test
    @DisplayName("다른 색깔의 기물을 갖고 있으면 false 를 반환하는지")
    void hasPieceOnGrid() {
        Grid grid = new Grid(new King(Color.BLACK));
        assertThat(grid.hasPieceOf(Color.WHITE)).isFalse();
    }
}
