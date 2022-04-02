package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.attribute.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultPieceTest {

    @Test
    @DisplayName("체스 말을 생성할 수 있다.")
    void createPiece() {
        DefaultPiece blackDefaultPiece = new Pawn(Team.BLACK);
        DefaultPiece whiteDefaultPiece = new Pawn(Team.WHITE);

        assertAll(
                () -> assertThat(blackDefaultPiece.getTeam()).isEqualTo(Team.BLACK),
                () -> assertThat(whiteDefaultPiece.getTeam()).isEqualTo(Team.WHITE)
        );
    }

    @Test
    @DisplayName("체스 말중 폰을 생성할 수 있다.")
    void createPawn() {
        DefaultPiece pawn = new Pawn(Team.BLACK);

        assertThat(pawn).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("체스 말중 룩을 생성할 수 있다.")
    void createRook() {
        DefaultPiece rook = new Rook(Team.BLACK);

        assertThat(rook).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("체스 말중 비숍을 생성할 수 있다.")
    void createBishop() {
        DefaultPiece bishop = new Bishop(Team.BLACK);

        assertThat(bishop).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("체스 말중 킹을 생성할 수 있다.")
    void createKing() {
        DefaultPiece king = new King(Team.BLACK);

        assertThat(king).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("체스 말중 퀸을 생성할 수 있다.")
    void createQueen() {
        DefaultPiece queen = new Queen(Team.BLACK);

        assertThat(queen).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("체스 말중 나이트를 생성할 수 있다.")
    void createKnight() {
        DefaultPiece knight = new Knight(Team.BLACK);

        assertThat(knight).isInstanceOf(Knight.class);
    }
}
