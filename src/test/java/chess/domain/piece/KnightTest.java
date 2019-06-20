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

class KnightTest {
    private Piece knight;

    @BeforeEach
    void setUp() {
        knight = new Knight(Team.WHITE);
    }

    @Test
    void 위_위_왼쪽_대각선으로_갈수_있는지_테스트() {
        assertThat(knight.getRoute(Square.of(3, 2), Square.of(2, 0)))
                .isEqualTo(new Route(Arrays.asList(
                        Square.of(3, 2),
                        Square.of(2, 0)
                )));
    }

    @Test
    void 갈수_없는_경로_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> knight.getRoute(Square.of(3, 2), Square.of(1, 0)))
                .withMessage("갈 수 없습니다.");
    }

    @Test
    void 폰_탐_반환_테스트() {
        assertThat(knight.getTeam()).isEqualTo(Team.WHITE);
    }

    @Test
    void 폰_타입_반환_테스트() {
        assertThat(knight.getType()).isEqualTo(Type.KNIGHT);
    }
}