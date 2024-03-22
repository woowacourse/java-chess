package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("폰")
class PawnTest {

    @ParameterizedTest
    @CsvSource({"c6, true", "c5, true", "c4, false"})
    @DisplayName("의 색이 검정일 경우 최초 이동 시 아래로 최대 두 칸 이동할 수 있다.")
    void canMoveUpToTwoStepWhenBlackPawnFirstMovement(String target, boolean expected) {
        Pawn pawn = new Pawn(PieceColor.BLACK);

        boolean actual = pawn.canMove(Position.from("c7"), Position.from(target));

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"c3, true", "c4, true", "c5, false"})
    @DisplayName("의 색이 흰색일 경우 최초 이동 시 위로 최대 두 칸 이동할 수 있다.")
    void canMoveUpToTwoStepWhenWhitePawnFirstMovement(String target, boolean expected) {
        Pawn pawn = new Pawn(PieceColor.WHITE);

        boolean actual = pawn.canMove(Position.from("c2"), Position.from(target));

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"c5, true", "c4, false"})
    @DisplayName("의 색이 검정일 경우 최초 이동 이후에는 한 칸씩만 아래로 이동할 수 있다.")
    void canMoveOnlyOneStepBlackPawnAfterFirstMovement(String target, boolean expected) {
        Pawn pawn = new Pawn(PieceColor.BLACK);

        boolean actual = pawn.canMove(Position.from("c6"), Position.from(target));

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"c4, true", "c5, false"})
    @DisplayName("의 색이 흰색일 경우 최초 이동 이후에는 한 칸씩만 위로 이동할 수 있다.")
    void canMoveOnlyOneStepWhitePawnAfterFirstMovement(String target, boolean expected) {
        Pawn pawn = new Pawn(PieceColor.WHITE);

        boolean actual = pawn.canMove(Position.from("c3"), Position.from(target));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("은 후진할 수 없다.")
    void cannotMoveBackward() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        Position source = Position.from("b3");
        Position target = Position.from("b2");

        assertThat(pawn.canMove(source, target)).isFalse();
    }
}
