package chess.domain.piece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceConverterTest {

    @Test
    void queenGenerateTest() {
        Piece piece = PieceMapper.convert("black_queen");

        assertThat(piece).isInstanceOf(Queen.class);
    }

    @Test
    void kingGenerateTest() {
        Piece piece = PieceMapper.convert("black_king");

        assertThat(piece).isInstanceOf(King.class);
    }

    @Test
    void bishopGenerateTest() {
        Piece piece = PieceMapper.convert("black_bishop");

        assertThat(piece).isInstanceOf(Bishop.class);
    }

    @Test
    void knightGenerateTest() {
        Piece piece = PieceMapper.convert("black_knight");

        assertThat(piece).isInstanceOf(Knight.class);
    }

    @Test
    void pawnGenerateTest() {
        Piece piece = PieceMapper.convert("black_pawn");

        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @Test
    void rookGenerateTest() {
        Piece piece = PieceMapper.convert("black_rook");

        assertThat(piece).isInstanceOf(Rook.class);
    }
}
