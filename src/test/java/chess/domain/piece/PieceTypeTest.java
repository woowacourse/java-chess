package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTypeTest {

    @Test
    @DisplayName("기물의 타입을 알 수 있다.")
    void findTypeTest() {
        King king = new King(Team.BLACK);
        Queen queen = new Queen(Team.WHITE);
        Rook rook = new Rook(Team.BLACK);
        Bishop bishop = new Bishop(Team.WHITE);
        Knight knight = new Knight(Team.WHITE);
        Pawn pawn = new Pawn(Team.BLACK);

        assertAll(
                () -> assertThat(PieceType.from(king)).isEqualTo(PieceType.KING),
                () -> assertThat(PieceType.from(queen)).isEqualTo(PieceType.QUEEN),
                () -> assertThat(PieceType.from(rook)).isEqualTo(PieceType.ROOK),
                () -> assertThat(PieceType.from(bishop)).isEqualTo(PieceType.BISHOP),
                () -> assertThat(PieceType.from(knight)).isEqualTo(PieceType.KNIGHT),
                () -> assertThat(PieceType.from(pawn)).isEqualTo(PieceType.PAWN)
        );
    }

    @Test
    @DisplayName("기물의 점수를 알 수 있다.")
    void findScoreTest() {
        King king = new King(Team.BLACK);
        Queen queen = new Queen(Team.WHITE);
        Rook rook = new Rook(Team.BLACK);
        Bishop bishop = new Bishop(Team.WHITE);
        Knight knight = new Knight(Team.WHITE);
        Pawn pawn = new Pawn(Team.BLACK);

        assertAll(
                () -> assertThat(PieceType.scoreOf(king)).isEqualTo(0),
                () -> assertThat(PieceType.scoreOf(queen)).isEqualTo(9),
                () -> assertThat(PieceType.scoreOf(rook)).isEqualTo(5),
                () -> assertThat(PieceType.scoreOf(bishop)).isEqualTo(3),
                () -> assertThat(PieceType.scoreOf(knight)).isEqualTo(2.5),
                () -> assertThat(PieceType.scoreOf(pawn)).isEqualTo(1)
        );
    }
}
