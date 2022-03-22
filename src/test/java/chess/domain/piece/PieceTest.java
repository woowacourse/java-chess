package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("체스 말을 생성할 수 있다.")
    void createPiece() {
        Piece blackPiece = new Pawn(Color.Black);
        Piece whitePiece = new Pawn(Color.White);

        assertAll(
                () -> assertThat(blackPiece.getColor()).isEqualTo(Color.Black),
                () -> assertThat(whitePiece.getColor()).isEqualTo(Color.White)
        );
    }

    @Test
    @DisplayName("체스 말중 폰을 생성할 수 있다.")
    void createPawn() {
        Piece pawn = new Pawn(Color.Black);

        assertThat(pawn).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("체스 말중 룩을 생성할 수 있다.")
    void createRook() {
        Piece rook = new Rook(Color.Black);

        assertThat(rook).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("체스 말중 비숍을 생성할 수 있다.")
    void createBishop() {
        Piece bishop = new Bishop(Color.Black);

        assertThat(bishop).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("체스 말중 킹을 생성할 수 있다.")
    void createKing() {
        Piece king = new King(Color.Black);

        assertThat(king).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("체스 말중 퀸을 생성할 수 있다.")
    void createQueen() {
        Piece queen = new Queen(Color.Black);

        assertThat(queen).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("체스 말중 나이트를 생성할 수 있다.")
    void createKnight() {
        Piece knight = new Knight(Color.Black);

        assertThat(knight).isInstanceOf(Knight.class);
    }
}
