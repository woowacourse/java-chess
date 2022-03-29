package chess.model.piece;

import static chess.model.position.File.D;
import static chess.model.position.Rank.FIVE;
import static chess.model.position.Rank.FOUR;
import static chess.model.position.Rank.THREE;
import static chess.model.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.position.File;
import chess.model.Team;
import chess.model.position.Position;
import chess.model.position.Rank;
import chess.model.direction.route.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @DisplayName("움직일 수 없는 방향이 입력되면 예외를 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"TWO, D", "THREE, H", "EIGHT, D", "ONE, D"})
    void findRoute_exception(Rank rank, File file) {
        Piece pawn = new Pawn(Team.BLACK, "R");

        assertThatThrownBy(() -> pawn.findRoute(Position.of(FOUR, D), Position.of(rank, file)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다.");
    }

    @DisplayName("움직일 수 없는 방향이 입력되면 예외를 발생한다.")
    @Test
    void findRoute_exception_white() {
        Piece pawn = new Pawn(Team.WHITE, "R");

        assertThatThrownBy(() -> pawn.findRoute(Position.of(FOUR, D), Position.of(THREE, D)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다.");
    }

    @DisplayName("북쪽으로 움직일 수 있으면 북쪽 방향의 Route를 반환한다.")
    @Test
    void findRoute_north() {
        Piece pawn = new Pawn(Team.WHITE, "R");
        Route route = pawn.findRoute(Position.of(FOUR, D), Position.of(FIVE, D));

        assertThat(route).isEqualTo(new Route(-1, 0));
    }

    @DisplayName("북쪽으로 움직일 수 있을 때 시작위치에 있고 북쪽 방향으로 두칸을 입력하면 Route를 반환한다.")
    @Test
    void findRoute_north_two() {
        Piece pawn = new Pawn(Team.WHITE, "R");
        Route route = pawn.findRoute(Position.of(TWO, D), Position.of(FOUR, D));

        assertThat(route).isEqualTo(new Route(-1, 0));
    }

    @DisplayName("남쪽으로 움직일 수 있으면 남쪽 방향의 Route를 반환한다.")
    @Test
    void findRoute_south() {
        Piece pawn = new Pawn(Team.BLACK, "R");
        Route route = pawn.findRoute(Position.of(FOUR, D), Position.of(THREE, D));

        assertThat(route).isEqualTo(new Route(1, 0));
    }
}
