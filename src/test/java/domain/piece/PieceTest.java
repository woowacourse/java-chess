package domain.piece;


import static domain.PositionFixture.A_ONE;
import static domain.PositionFixture.A_TWO;
import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.info.Color;
import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import domain.piece.info.Type;
import domain.piece.info.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {
    @Test
    @DisplayName("피스의 색이 흰색인지 확인한다")
    void color() {
        final Piece rook = new Rook(Color.WHITE);

        assertThat(rook.isWhite()).isTrue();
    }

    @Test
    @DisplayName("기물은 자신의 색상 정보를 제공한다.")
    void pieceColor() {
        final Piece piece = new Rook(Color.WHITE);

        assertThat(piece.color()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("이동하려는 위치에 같은 색의 기물이 있으면 이동할 수 없다")
    void sameColor() {
        final Piece piece = new Rook(Color.WHITE);
        final Piece otherPiece = new Rook(Color.WHITE);

        assertThat(piece.isReachable(new Vector(A_ONE, A_TWO), otherPiece)).isFalse();
    }
}
