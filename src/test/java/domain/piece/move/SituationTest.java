package domain.piece.move;

import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.square.Color;
import domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SituationTest {

    @Test
    @DisplayName("컬러가 다른 경우, ENEMY가 반환된다")
    void ofTestEnemy() {
        Square square = new Square(new BlackPawn(), Color.BLACK);
        Square otherSquare = new Square(new WhitePawn(), Color.WHITE);

        assertThat(Situation.of(square, otherSquare))
                .isEqualTo(Situation.ENEMY);
    }

    @Test
    @DisplayName("컬러가 같은 경우, COLLEAGUE가 반환된다")
    void ofTestColleague() {
        Square square = new Square(new BlackPawn(), Color.WHITE);
        Square otherSquare = new Square(new WhitePawn(), Color.WHITE);

        assertThat(Situation.of(square, otherSquare))
                .isEqualTo(Situation.COLLEAGUE);
    }

    @Test
    @DisplayName("상대가 색이 없는 경우, NEUTRAL가 반환된다")
    void ofTestNeutral() {
        Square square = new Square(new BlackPawn(), Color.BLACK);
        Square otherSquare = new Square(new WhitePawn(), Color.NEUTRAL);

        assertThat(Situation.of(square, otherSquare))
                .isEqualTo(Situation.NEUTRAL);
    }
}
