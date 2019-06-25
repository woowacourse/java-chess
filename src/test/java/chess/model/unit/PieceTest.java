package chess.model.unit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @Test
    void 같은팀일때_같은팀으로_확인하는지_테스트() {
        Piece source = new Bishop(Side.BLACK);
        Piece target = new King(Side.BLACK);
        assertThat(source.isSameSide(target)).isTrue();
    }

    @Test
    void 다른팀일때_다른팀으로_확인하는지_테스트() {
        Piece source = new Bishop(Side.BLACK);
        Piece target = new King(Side.WHITE);
        assertThat(source.isSameSide(target)).isFalse();
    }

    @Test
    void NULL값일때_다른팀으로_확인하는지_테스트() {
        Piece source = new Bishop(Side.BLACK);
        Piece target = null;
        assertThat(source.isSameSide(target)).isFalse();
    }
}