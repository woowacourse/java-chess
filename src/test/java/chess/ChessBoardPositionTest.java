package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardPositionTest {

    @DisplayName("a1을 수평 반전 하면 h1이 된다.")
    @Test
    void flip_horizontal_a1_to_h1() {
        //given
        ChessBoardPosition a1 = new ChessBoardPosition(ChessBoardColumn.A, ChessBoardRow.ONE);
        ChessBoardPosition expected = new ChessBoardPosition(ChessBoardColumn.H, ChessBoardRow.ONE);

        //when
        ChessBoardPosition actual = a1.flipHorizontally();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("a1을 수직 반전 하면 a8이 된다.")
    @Test
    void flip_horizontal_a1_to_a8() {
        //given
        ChessBoardPosition a1 = new ChessBoardPosition(ChessBoardColumn.A, ChessBoardRow.ONE);
        ChessBoardPosition expected = new ChessBoardPosition(ChessBoardColumn.A, ChessBoardRow.EIGHT);

        //when
        ChessBoardPosition actual = a1.flipVertically();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("a1을 대각 반전 하면 h8이 된다.")
    @Test
    void flip_diagonally_a1_to_h8() {
        //given
        ChessBoardPosition a1 = new ChessBoardPosition(ChessBoardColumn.A, ChessBoardRow.ONE);
        ChessBoardPosition expected = new ChessBoardPosition(ChessBoardColumn.H, ChessBoardRow.EIGHT);

        //when
        ChessBoardPosition actual = a1.flipDiagonally();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
