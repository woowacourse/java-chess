package chess.domain.piece;

import chess.domain.direction.Route;
import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PawnTest {

    private Piece pawn;

    @BeforeEach
    void setUp() {
        pawn = new Pawn(Team.WHITE);
    }

    @Test
    void 위로_갈수_있는지_테스트() {
        assertThat(pawn.getRoute(Square.of(1, 7), Square.of(1, 6)))
                .isEqualTo(new Route(Arrays.asList(
                        Square.of(1, 7),
                        Square.of(1, 6)
                )));
    }

    @Test
    void 갈수_없는_경로_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> pawn.getRoute(Square.of(1, 7), Square.of(1, 4)))
                .withMessage("갈 수 없습니다.");
    }

    @Test
    void 폰_탐_반환_테스트() {
        assertThat(pawn.getTeam()).isEqualTo(Team.WHITE);
    }

    @Test
    void 폰_타입_반환_테스트() {
        assertThat(pawn.getType()).isEqualTo(Type.PAWN);
    }

    @Test
    void 대각선_캐치_테스트() {
        assertThat(pawn.getRoute(Square.of(3, 2), Square.of(2, 1)).isCatch()).isTrue();
    }

    @Test
    void 위_이동_캐치_낫_테스트() {
        assertThat(pawn.getRoute(Square.of(3, 2), Square.of(3, 1)).isCatch()).isFalse();
    }
}
