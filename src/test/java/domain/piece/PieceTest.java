package domain.piece;


import static domain.PositionFixture.A_ONE;
import static domain.PositionFixture.A_TWO;
import static domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

import domain.board.position.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {
    @Test
    @DisplayName("피스의 색이 흰색인지 확인한다")
    void color() {
        final Piece rook = new Rook(WHITE);

        assertThat(rook.isWhite()).isTrue();
    }

    @Test
    @DisplayName("기물은 자신의 색상 정보를 제공한다.")
    void pieceColor() {
        final Piece piece = new Rook(WHITE);

        assertThat(piece.getColor()).isEqualTo(WHITE);
    }

    @Test
    @DisplayName("이동하려는 위치에 같은 색의 기물이 있으면 이동할 수 없다")
    void sameColor() {
        final Piece piece = new Rook(WHITE);
        final Piece otherPiece = new Rook(WHITE);

        assertThat(piece.isReachable(new Vector(A_ONE, A_TWO), otherPiece)).isFalse();
    }
}
