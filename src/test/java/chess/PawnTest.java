package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @Test
    @DisplayName("폰의 좌표는 1번과 6번 행에서 생성된다.")
    void createPawn() {
        final List<Pawn> pawns = Pawn.init();

        assertThat(pawns).extracting(Pawn::getPosition)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(
                        new Position(1, 0),
                        new Position(1, 1),
                        new Position(1, 2),
                        new Position(1, 3),
                        new Position(1, 4),
                        new Position(1, 5),
                        new Position(1, 6),

                        new Position(6, 0),
                        new Position(6, 1),
                        new Position(6, 2),
                        new Position(6, 3),
                        new Position(6, 4),
                        new Position(6, 5),
                        new Position(6, 6)
                );
    }
}
