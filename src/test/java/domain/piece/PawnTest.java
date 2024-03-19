package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.File;
import domain.Position;
import domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("폰은 1칸 전진할 수 있다.")
    void canMove_ForwardOne_NoInitPosition() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(3));
        Position position2 = new Position(new File(1), new Rank(4));
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("초기 위치에 있는 폰은 1칸 전진할 수 있다.")
    void canMove_ForwardOne_InitPosition() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(2));
        Position position2 = new Position(new File(1), new Rank(3));
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("초기 위치에 있는 폰은 2칸 전진할 수 있다.")
    void canMove_ForwardTwo_InitPosition() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(2));
        Position position2 = new Position(new File(1), new Rank(4));
        assertThat(pawn.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("폰은 후진할 수 없다.")
    void cannotMove_Backward() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(3));
        Position position2 = new Position(new File(1), new Rank(2));
        assertThat(pawn.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("초기 위치에 있는 폰은 2칸 전진할 수 있다.")
    void cannotMove_ForwardTwo_NoInitPosition() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position1 = new Position(new File(1), new Rank(3));
        Position position2 = new Position(new File(1), new Rank(5));
        assertThat(pawn.canMove(position1, position2)).isFalse();
    }
}
