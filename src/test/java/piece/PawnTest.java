package piece;

import model.Camp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import point.Column;
import point.Position;
import point.Row;

class PawnTest {

    @DisplayName("폰은 처음 위치에서만 앞으로 2칸 이동할 수 있다.")
    @Test
    void moveTwoPoint() {
        Pawn pawn = new Pawn(Camp.BLACK);

        boolean canMovable = pawn.canMovable(new Position(Row.SEVENTH, Column.FIRST), new Position(Row.FIFTH, Column.FIRST));

        Assertions.assertThat(canMovable).isTrue();
    }

    @DisplayName("폰은 한 번 움직인 뒤에는 앞으로 2칸 이동할 수 없다.")
    @Test
    void name() {
        Pawn pawn = new Pawn(Camp.BLACK);

        boolean canMovable = pawn.canMovable(new Position(Row.SIXTH, Column.FIRST), new Position(Row.FOURTH, Column.FIRST));

        Assertions.assertThat(canMovable).isFalse();
    }

    @DisplayName("폰은 앞으로 1칸 이동할 수 있다.")
    @Test
    void name1() {
        Pawn pawn = new Pawn(Camp.BLACK);

        boolean canMovable = pawn.canMovable(new Position(Row.SIXTH, Column.FIRST), new Position(Row.FIFTH, Column.FIRST));

        Assertions.assertThat(canMovable).isTrue();
    }

    @DisplayName("폰은 앞으로 3칸 이상 움직일 수 없다.")
    @Test
    void name2() {
        Pawn pawn = new Pawn(Camp.BLACK);

        boolean canMovable = pawn.canMovable(new Position(Row.SEVENTH, Column.FIRST), new Position(Row.FOURTH, Column.FIRST));

        Assertions.assertThat(canMovable).isFalse();
    }

    @DisplayName("검정색 폰은 위로 움직일 수 없다.")
    @Test
    void name3() {
        Pawn pawn = new Pawn(Camp.BLACK);

        boolean canMovable = pawn.canMovable(new Position(Row.SEVENTH, Column.FIRST), new Position(Row.EIGHTH, Column.FIRST));

        Assertions.assertThat(canMovable).isFalse();
    }
}
