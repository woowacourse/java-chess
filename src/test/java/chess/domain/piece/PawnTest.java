package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.PieceColor;
import chess.domain.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("폰")
class PawnTest {

    @ParameterizedTest
    @CsvSource({"c6, true", "c5, true", "c4, false"})
    @DisplayName("의 색이 검정일 경우 최초 이동 시 아래로 최대 두 칸 이동할 수 있다.")
    void canMoveUpToTwoStepWhenBlackPawnFirstMovement(String target, boolean expected) {
        Pawn pawn = new Pawn(PieceColor.BLACK);

        boolean actual = pawn.canMove(Square.from("c7"), Square.from(target));

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"c3, true", "c4, true", "c5, false"})
    @DisplayName("의 색이 흰색일 경우 최초 이동 시 위로 최대 두 칸 이동할 수 있다.")
    void canMoveUpToTwoStepWhenWhitePawnFirstMovement(String target, boolean expected) {
        Pawn pawn = new Pawn(PieceColor.WHITE);

        boolean actual = pawn.canMove(Square.from("c2"), Square.from(target));

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"c5, true", "c4, false"})
    @DisplayName("의 색이 검정일 경우 최초 이동 이후에는 한 칸씩만 아래로 이동할 수 있다.")
    void canMoveOnlyOneStepBlackPawnAfterFirstMovement(String target, boolean expected) {
        Pawn pawn = new Pawn(PieceColor.BLACK);

        boolean actual = pawn.canMove(Square.from("c6"), Square.from(target));

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"c4, true", "c5, false"})
    @DisplayName("의 색이 흰색일 경우 최초 이동 이후에는 한 칸씩만 위로 이동할 수 있다.")
    void canMoveOnlyOneStepWhitePawnAfterFirstMovement(String target, boolean expected) {
        Pawn pawn = new Pawn(PieceColor.WHITE);

        boolean actual = pawn.canMove(Square.from("c3"), Square.from(target));

        assertThat(actual).isEqualTo(expected);
    }
}
