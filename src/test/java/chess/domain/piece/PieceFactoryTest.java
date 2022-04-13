package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class PieceFactoryTest {

    @Test
    void isPawn() {
        Piece piece = PieceFactory.of("pawn", "흑색", "a1");

        assertThat(piece.getName()).isEqualTo("pawn");
    }

    @Test
    void isKnight() {
        Piece piece = PieceFactory.of("knight", "흑색", "a1");

        assertThat(piece.getName()).isEqualTo("knight");
    }

    @Test
    void isBishop() {
        Piece piece = PieceFactory.of("bishop", "흑색", "a1");

        assertThat(piece.getName()).isEqualTo("bishop");
    }

    @Test
    void isRook() {
        Piece piece = PieceFactory.of("rook", "흑색", "a1");

        assertThat(piece.getName()).isEqualTo("rook");
    }

    @Test
    void isQueen() {
        Piece piece = PieceFactory.of("queen", "흑색", "a1");

        assertThat(piece.getName()).isEqualTo("queen");
    }

    @Test
    void isKing() {
        Piece piece = PieceFactory.of("king", "흑색", "a1");

        assertThat(piece.getName()).isEqualTo("king");
    }

}
