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

class RookTest {
    private Piece rook;

    @BeforeEach
    void setUp() {
        rook = new Rook(Team.WHITE);
    }

    @Test
    void 왼쪽_대각선으로_갈수_있는지_테스트() {
        assertThat(rook.getRoute(Square.of(3, 2), Square.of(0, 2)))
                .isEqualTo(new Route(Arrays.asList(
                        Square.of(3, 2),
                        Square.of(2, 2),
                        Square.of(1, 2),
                        Square.of(0, 2)
                )));
    }

    @Test
    void 갈수_없는_경로_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> rook.getRoute(Square.of(3, 2), Square.of(1, 1)))
                .withMessage("갈 수 없습니다.");
    }

    @Test
    void 폰_탐_반환_테스트() {
        assertThat(rook.getTeam()).isEqualTo(Team.WHITE);
    }

    @Test
    void 폰_타입_반환_테스트() {
        assertThat(rook.getType()).isEqualTo(Type.ROOK);
    }
}