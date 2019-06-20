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

class QueenTest {
    private Piece queen;

    @BeforeEach
    void setUp() {
        queen = new Queen(Team.WHITE);
    }

    @Test
    void 위_왼쪽_대각선으로_갈수_있는지_테스트() {
        assertThat(queen.getRoute(Square.of(3, 2), Square.of(1, 0)))
                .isEqualTo(new Route(Arrays.asList(
                        Square.of(3, 2),
                        Square.of(2, 1),
                        Square.of(1, 0)
                )));
    }

    @Test
    void 갈수_없는_경로_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> queen.getRoute(Square.of(3, 2), Square.of(0, 0)))
                .withMessage("갈 수 없습니다.");
    }

    @Test
    void 폰_탐_반환_테스트() {
        assertThat(queen.getTeam()).isEqualTo(Team.WHITE);
    }

    @Test
    void 폰_타입_반환_테스트() {
        assertThat(queen.getType()).isEqualTo(Type.QUEEN);
    }
}