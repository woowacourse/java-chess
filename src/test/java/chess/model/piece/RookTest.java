package chess.model.piece;

import static chess.model.position.File.B;
import static chess.model.position.File.D;
import static chess.model.position.File.H;
import static chess.model.position.Rank.FIVE;
import static chess.model.position.Rank.FOUR;
import static chess.model.position.Rank.ONE;
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

class RookTest {

    @DisplayName("움직일 수 없는 방향이 입력되면 예외를 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"TWO, B", "THREE, F", "SEVEN, H"})
    void findRoute_exception(Rank rank, File file) {
        Piece rook = new Rook(Team.BLACK, "R");

        assertThatThrownBy(() -> rook.findRoute(Position.of(FOUR, D), Position.of(rank, file)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다.");
    }

    @DisplayName("북쪽으로 움직일 수 있으면 북쪽 방향의 Route를 반환한다.")
    @Test
    void findRoute_north() {
        Piece rook = new Rook(Team.BLACK, "R");
        Route route = rook.findRoute(Position.of(FOUR, D), Position.of(FIVE, D));

        assertThat(route).isEqualTo(new Route(-1, 0));
    }

    @DisplayName("남쪽으로 움직일 수 있으면 남쪽 방향의 Route를 반환한다.")
    @Test
    void findRoute_south() {
        Piece rook = new Rook(Team.BLACK, "R");
        Route route = rook.findRoute(Position.of(FOUR, D), Position.of(ONE, D));

        assertThat(route).isEqualTo(new Route(1, 0));
    }

    @DisplayName("동쪽으로 움직일 수 있으면 동쪽 방향의 Route를 반환한다.")
    @Test
    void findRoute_east() {
        Piece rook = new Rook(Team.BLACK, "R");
        Route route = rook.findRoute(Position.of(FOUR, D), Position.of(FOUR, H));

        assertThat(route).isEqualTo(new Route(0, 1));
    }

    @DisplayName("서쪽으로 움직일 수 있으면 서쪽 방향의 Route를 반환한다.")
    @Test
    void findRoute_west() {
        Piece rook = new Rook(Team.BLACK, "R");
        Route route = rook.findRoute(Position.of(FOUR, D), Position.of(FOUR, B));

        assertThat(route).isEqualTo(new Route(0, -1));
    }
}
