package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("체스 말을 생성할 수 있다.")
    void createPiece() {
        Piece blackPiece = new Pawn(Color.BLACK);
        Piece whitePiece = new Pawn(Color.WHITE);

        assertAll(
                () -> assertThat(blackPiece.getColor()).isEqualTo(Color.BLACK),
                () -> assertThat(whitePiece.getColor()).isEqualTo(Color.WHITE)
        );
    }

    @Test
    @DisplayName("체스 말중 폰을 생성할 수 있다.")
    void createPawn() {
        Piece pawn = new Pawn(Color.BLACK);

        assertThat(pawn).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("체스 말중 룩을 생성할 수 있다.")
    void createRook() {
        Piece rook = new Rook(Color.BLACK);

        assertThat(rook).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("체스 말중 비숍을 생성할 수 있다.")
    void createBishop() {
        Piece bishop = new Bishop(Color.BLACK);

        assertThat(bishop).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("체스 말중 킹을 생성할 수 있다.")
    void createKing() {
        Piece king = new King(Color.BLACK);

        assertThat(king).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("체스 말중 퀸을 생성할 수 있다.")
    void createQueen() {
        Piece queen = new Queen(Color.BLACK);

        assertThat(queen).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("체스 말중 나이트를 생성할 수 있다.")
    void createKnight() {
        Piece knight = new Knight(Color.BLACK);

        assertThat(knight).isInstanceOf(Knight.class);
    }
}
