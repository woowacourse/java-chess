package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("Pawn 은 처음에는 앞으로 1칸, 또는 2칸 이동 가능하다.")
    void 초기_이동() {
        Pawn pawn = new Pawn();

        List<Position> movablePositions = pawn.findMovablePositions(new Position(2, 2));

        assertThat(movablePositions).contains(new Position(3, 2), new Position(4, 2));
        assertThat(movablePositions.size()).isEqualTo(2);
    }
    
    @Test
    @DisplayName("Pawn 은 첫 이동이 아닌 경우, 앞으로 1칸 또는 공격의 경우만 이동 가능하다.")
    void 이동_또는_공격() {
        Pawn pawn = new Pawn();

        List<Position> movablePositions = pawn.findMovablePositions(new Position(3, 2));

        assertThat(movablePositions).contains(new Position(4, 2), new Position(4, 1), new Position(4, 3));
        assertThat(movablePositions.size()).isEqualTo(3);
    }

}
