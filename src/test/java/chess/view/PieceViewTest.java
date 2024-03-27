package chess.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("기물 출력 값")
class PieceViewTest {

    @DisplayName("기물에 맞는 문자를 찾는다")
    @Test
    void findValue() {
        //given
        char expectedWhitePawn = 'p';
        char expectedBlackPawn = 'P';

        //when
        char whitePawnDisplay = PieceView.PAWN.changeToView("WHITE");
        char blackPawnDisplay = PieceView.PAWN.changeToView("BLACK");

        //then
        assertAll(
                () -> assertThat(whitePawnDisplay).isEqualTo(expectedWhitePawn),
                () -> assertThat(blackPawnDisplay).isEqualTo(expectedBlackPawn)
        );
    }
}
