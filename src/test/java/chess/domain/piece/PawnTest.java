package chess.domain.piece;

import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("폰")
class PawnTest {

    @ParameterizedTest
    @ValueSource(strings = {"c6", "c5"})
    @DisplayName("검은색 기물일 경우 처음에 아래로 최대 두 칸까지 이동할 수 있다.")
    void moveUpToTwoStepWhenBlackPawnFirstMovement(String targetInput) {
        //given
        Square source = Square.from("c7");
        Square target = Square.from(targetInput);
        Pawn pawn = new Pawn(PieceColor.BLACK, source);

        // when
        pawn.move(target);

        assertThat(pawn.getSquare()).isEqualTo(target);
    }

    @ParameterizedTest
    @ValueSource(strings = {"c3", "c4"})
    @DisplayName("흰색 기물일 경우 처음에 위로 최대 두 칸까지 이동할 수 있다.")
    void moveUpToTwoStepWhenWhitePawnFirstMovement(String targetInput) {
        //given
        Square source = Square.from("c2");
        Square target = Square.from(targetInput);
        Pawn pawn = new Pawn(PieceColor.WHITE, source);

        // when
        pawn.move(target);

        assertThat(pawn.getSquare()).isEqualTo(target);
    }
    @Test
    @DisplayName("검은색 기물일 경우 두번째 이동부터 아래로 한 칸만 이동할 수 있다.")
    void moveOnlyOneStepWhenBlackPawnAfterFirstMovement() {
        //given
        Square source = Square.from("c7");
        Square stopover = Square.from("c6");
        Square target = Square.from("c5");
        Pawn pawn = new Pawn(PieceColor.BLACK, source);

        // when
        pawn.move(stopover);
        pawn.move(target);

        assertThat(pawn.getSquare()).isEqualTo(target);
    }

    @Test
    @DisplayName("흰색 기물일 경우 두번째 이동부터 위로 한 칸만 이동할 수 있다.")
    void moveOnlyOneStepWhenWhitePawnAfterFirstMovement() {
        //given
        Square source = Square.from("c2");
        Square stopover = Square.from("c3");
        Square target = Square.from("c4");
        Pawn pawn = new Pawn(PieceColor.WHITE, source);

        // when
        pawn.move(stopover);
        pawn.move(target);

        assertThat(pawn.getSquare()).isEqualTo(target);
    }
}
