package chess.domain.piece;

import chess.domain.direction.Route;
import chess.domain.direction.core.Direction;
import chess.domain.direction.Way;
import chess.domain.direction.core.Square;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PawnTest {

    @Test
    void 갈수_있을까_테스트() {
        Pawn pawn = new Pawn();
        assertThat(pawn.getRoute(Square.of(1,7), Square.of(1,6)))
                .isEqualTo(new Route(Arrays.asList(
                        Square.of(1, 7),
                        Square.of(1, 6)
                )));
    }

    @Test
    void 갈수_없는_경로_테스트() {
        Pawn pawn = new Pawn();
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            pawn.getRoute(Square.of(1,7), Square.of(1,4));
        }).withMessage("갈 수 없습니다.");
    }
}
