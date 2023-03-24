package domain.piece.move;

import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.Color;
import domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SituationTest {

    @Test
    @DisplayName("컬러가 다른 경우, ENEMY가 반환된다")
    void ofTestEnemy() {
        Piece piece = new BlackPawn(Color.BLACK);
        Piece otherPiece = new WhitePawn(Color.WHITE);

        assertThat(Situation.of(piece, otherPiece))
                .isEqualTo(Situation.ENEMY);
    }

    @Test
    @DisplayName("컬러가 같은 경우, COLLEAGUE가 반환된다")
    void ofTestColleague() {
        Piece piece = new BlackPawn(Color.BLACK);
        Piece otherPiece = new BlackPawn(Color.BLACK);

        assertThat(Situation.of(piece, otherPiece))
                .isEqualTo(Situation.COLLEAGUE);
    }

    @Test
    @DisplayName("상대가 색이 없는 경우, NEUTRAL가 반환된다")
    void ofTestNeutral() {
        Piece piece = new BlackPawn(Color.BLACK);
        Piece otherPiece = new WhitePawn(Color.NEUTRAL);

        assertThat(Situation.of(piece, otherPiece))
                .isEqualTo(Situation.NEUTRAL);
    }
}
