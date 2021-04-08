package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("캐싱된 Piece 컬렉션에서 심볼에 매칭되는 Piece를 반환하는지 테스트")
    void testGetMatchedPiece() {
        assertThat(Piece.of("p")).isEqualTo(Pawn.createWhite());
        assertThat(Piece.of("P")).isEqualTo(Pawn.createBlack());

        assertThat(Piece.of("r")).isEqualTo(Rook.createWhite());
        assertThat(Piece.of("R")).isEqualTo(Rook.createBlack());

        assertThat(Piece.of("n")).isEqualTo(Knight.createWhite());
        assertThat(Piece.of("N")).isEqualTo(Knight.createBlack());

        assertThat(Piece.of("b")).isEqualTo(Bishop.createWhite());
        assertThat(Piece.of("B")).isEqualTo(Bishop.createBlack());

        assertThat(Piece.of("q")).isEqualTo(Queen.createWhite());
        assertThat(Piece.of("Q")).isEqualTo(Queen.createBlack());

        assertThat(Piece.of("k")).isEqualTo(King.createWhite());
        assertThat(Piece.of("K")).isEqualTo(King.createBlack());

        assertThat(Piece.of(".")).isEqualTo(Empty.create());
    }

    @Test
    @DisplayName("캐싱된 Piece 컬렉션에서 심볼에 매칭되는 체스 말이 없을 경우 예외처리")
    void testGetMatchedPieceException() {
        assertThatThrownBy(() -> Piece.of("dd")).isInstanceOf(IllegalArgumentException.class);
    }
}