import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Piece;
import domain.piece.Rook;
import domain.piece.info.Color;
import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import domain.piece.info.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {
    @Test
    @DisplayName("기물은 자신의 좌표 정보를 제공한다.")
    void pieceCoordinate() {
        Piece piece = new Rook(Color.WHITE, new Position(File.A, Rank.EIGHT));
        assertThat(piece.position()).isEqualTo(new Position(File.A, Rank.EIGHT));
    }

    @Test
    @DisplayName("기물은 자신의 색상 정보를 제공한다.")
    void pieceColor() {
        Piece piece = new Rook(Color.WHITE, new Position(File.A, Rank.EIGHT));
        assertThat(piece.color()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("기물은 자신의 모양 정보를 제공한다.")
    void pieceShape() {
        Piece piece = new Rook(Color.WHITE, new Position(File.A, Rank.EIGHT));
        assertThat(piece.shape()).isEqualTo(Type.ROOK);
    }
}
