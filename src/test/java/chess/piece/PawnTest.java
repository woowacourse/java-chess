package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

import chess.Fixtures;
import chess.Movement;

class PawnTest {

    @Test
    void 폰은_앞으로_한칸_움직인다() {
        Pawn pawn = new Pawn(Fixtures.A2);

        assertThat(pawn.move(Movement.UP)).isEqualTo(new Pawn(Fixtures.A3));
    }

    @Test
    void 처음_움직이는_폰은_앞으로_두칸_움직일_수_있다() {
        Pawn pawn = new Pawn(Fixtures.A2);

        assertThat(pawn.move(Movement.UP_UP)).isEqualTo(new Pawn(Fixtures.A4));
    }

    @Test
    void 이미_움직인_폰은_앞으로_두칸_갈_수_없다() {
        Pawn pawn = new Pawn(Fixtures.A2);
        Pawn movedPawn = pawn.move(Movement.UP_UP);

        assertThatThrownBy(() -> movedPawn.move(Movement.UP_UP))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @EnumSource(
            value = Movement.class,
            names = {"UP", "UP_UP"},
            mode = Mode.EXCLUDE
    )
    @ParameterizedTest
    void 폰이_움직일_수_없다(Movement movement) {
        Pawn pawn = new Pawn(Fixtures.A2);

        assertThatThrownBy(() -> pawn.move(movement))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
