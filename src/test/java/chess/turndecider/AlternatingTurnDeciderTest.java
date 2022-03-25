package chess.turndecider;

import static chess.PieceColor.BLACK;
import static chess.PieceColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.PieceColor;
import chess.piece.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AlternatingTurnDeciderTest {

    private AlternatingTurnDecider turnDecider;

    @BeforeEach
    void setUp() {
        turnDecider = new AlternatingTurnDecider();
    }

    @DisplayName("처음 턴은 백이므로 해당 턴에는 흑은 선택될 수 없다")
    @ParameterizedTest
    @CsvSource(value = {"WHITE:true", "BLACK:false"}, delimiter = ':')
    void when_first_turn_white_is_ok_but_black_is_not_allowed(PieceColor pieceColor, boolean expected) {

        assertThat(turnDecider.isCorrectTurn(new Pawn(pieceColor))).isEqualTo(expected);
    }


    @DisplayName("처음 턴은 백이고 다음 턴은 블랙이다, 세번째 턴은 백이다")
    @Test
    void when_first_turn_white_is_ok_then_next_turn_black_is_black() {

        assertThat(turnDecider.isCorrectTurn(new Pawn(WHITE))).isTrue();
        assertThat(turnDecider.isCorrectTurn(new Pawn(BLACK))).isTrue();
        assertThat(turnDecider.isCorrectTurn(new Pawn(WHITE))).isTrue();
    }
}
