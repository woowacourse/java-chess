package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.pawn.WhitePawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TurnTest {

    @Test
    @DisplayName("첫 턴이 WHITE라면, 다음 턴은 BLACK이다")
    void invertTestWhenWhiteFirst() {
        Turn turn = new Turn(Color.WHITE);
        Piece whitePawn = new WhitePawn(Color.WHITE);

        turn.invert();

        assertThat(turn.isNotFor(whitePawn)).isTrue();
    }

    @Test
    @DisplayName("두번째 턴이 BLACK이라면, 다음 턴은 WHITE이다")
    void invertTestWhenWhiteSecond() {
        Turn turn = new Turn(Color.WHITE);
        Piece whitePiece = new WhitePawn(Color.WHITE);

        turn.invert();
        turn.invert();

        assertThat(turn.isNotFor(whitePiece)).isFalse();
    }
}
