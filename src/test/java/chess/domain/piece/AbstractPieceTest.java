package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AbstractPieceTest {

    private final Position position = Position.of("a3");

    @Test
    @DisplayName("폰 생성 테스트")
    void piece() {
        Piece piece = AbstractPiece.of("p", position);
        assertThat(piece).isInstanceOf(Pawn.class);
        assertThat(piece.isSameColor(Color.WHITE)).isTrue();

        piece = AbstractPiece.of("P", position);
        assertThat(piece.isSameColor(Color.BLACK)).isTrue();
    }

    @Test
    @DisplayName("비숍 생성 테스트")
    void bishop() {
        Piece piece = AbstractPiece.of("b", position);
        assertThat(piece).isInstanceOf(Bishop.class);
        assertThat(piece.isSameColor(Color.WHITE)).isTrue();

        piece = AbstractPiece.of("B", position);
        assertThat(piece.isSameColor(Color.BLACK)).isTrue();
    }

    @Test
    @DisplayName("킹 생성 테스트")
    void king() {
        Piece piece = AbstractPiece.of("k", position);
        assertThat(piece).isInstanceOf(King.class);
        assertThat(piece.isSameColor(Color.WHITE)).isTrue();

        piece = AbstractPiece.of("K", position);
        assertThat(piece.isSameColor(Color.BLACK)).isTrue();
    }

    @Test
    @DisplayName("나이트 생성 테스트")
    void knight() {
        Piece piece = AbstractPiece.of("n", position);
        assertThat(piece).isInstanceOf(Knight.class);
        assertThat(piece.isSameColor(Color.WHITE)).isTrue();

        piece = AbstractPiece.of("N", position);
        assertThat(piece.isSameColor(Color.BLACK)).isTrue();
    }

    @Test
    @DisplayName("퀸 생성 테스트")
    void queen() {
        Piece piece = AbstractPiece.of("q", position);
        assertThat(piece).isInstanceOf(Queen.class);
        assertThat(piece.isSameColor(Color.WHITE)).isTrue();

        piece = AbstractPiece.of("Q", position);
        assertThat(piece.isSameColor(Color.BLACK)).isTrue();
    }

    @Test
    @DisplayName("룩 생성 테스트")
    void rook() {
        Piece piece = AbstractPiece.of("r", position);
        assertThat(piece).isInstanceOf(Rook.class);
        assertThat(piece.isSameColor(Color.WHITE)).isTrue();

        piece = AbstractPiece.of("R", position);
        assertThat(piece.isSameColor(Color.BLACK)).isTrue();
    }

    @Test
    @DisplayName("유효하지 않은 인자전달")
    void validate() {
        assertThatThrownBy(() -> AbstractPiece.of("t", position))
            .isInstanceOf(IllegalArgumentException.class);
    }
}