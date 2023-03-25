package chess.domain.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class TurnTest {

    @Test
    void 초기는_흰색_턴이다() {
        //given
        Turn turn = new Turn();

        //when
        Color currentTurn = turn.getCurrentTurn();

        //then
        assertEquals(Color.WHITE, currentTurn);
    }

    @Test
    void 흰색_턴이_끝나면_검은색_턴이_된다() {
        //given
        Turn turn = new Turn();

        //when
        turn = turn.changeTurn();

        //then
        assertEquals(Color.BLACK, turn.getCurrentTurn());
    }

    @Test
    void 검은색_턴이_끝나면_흰색_턴이_된다() {
        //given
        Turn turn = new Turn();

        //when
        turn = turn.changeTurn();
        turn = turn.changeTurn();

        //then
        assertEquals(Color.WHITE, turn.getCurrentTurn());
    }

    @Test
    void getTurn을_통해_현재_턴을_구할_수_있다() {
        //given
        Turn turn = new Turn();

        //when
        int currentTurn = turn.getValue();

        //then
        assertEquals(1, currentTurn);
    }

    @Test
    void getTurn을_통해_현재_턴을_구할_수_있다2() {
        //given
        Turn turn = new Turn();

        //when
        turn = turn.changeTurn();
        int currentTurn = turn.getValue();

        //then
        assertEquals(2, currentTurn);
    }
}
