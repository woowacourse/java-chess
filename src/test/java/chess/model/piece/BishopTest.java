package chess.model.piece;

import static chess.model.Team.BLACK;
import static chess.model.position.File.A;
import static chess.model.position.File.C;
import static chess.model.position.File.D;
import static chess.model.position.File.E;
import static chess.model.position.File.F;
import static chess.model.position.Rank.FIVE;
import static chess.model.position.Rank.FOUR;
import static chess.model.position.Rank.ONE;
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

class BishopTest {

    @DisplayName("움직일 수 없는 방향이 입력되면 예외를 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"TWO, C", "THREE, D", "SEVEN, H"})
    void findRoute_exception(Rank rank, File file) {
        Piece bishop = new Bishop(BLACK);

        assertThatThrownBy(() -> bishop.findRoute(Position.of(FOUR, D), Position.of(rank, file)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 선택한 기물을 이동 할 수 없는 위치가 입력됬습니다.");
    }

    @DisplayName("북동쪽으로 움직일 수 있으면 북동쪽 방향의 Route를 반환한다.")
    @Test
    void findRoute_northeast() {
        Piece bishop = new Bishop(BLACK);
        Route route = bishop.findRoute(Position.of(FOUR, D), Position.of(FIVE, E));

        assertThat(route).isEqualTo(new Route(-1, 1));
    }

    @DisplayName("남서쪽으로 움직일 수 있으면 남서쪽 방향의 Route를 반환한다.")
    @Test
    void findRoute_southwest() {
        Piece bishop = new Bishop(BLACK);
        Route route = bishop.findRoute(Position.of(FOUR, D), Position.of(ONE, A));

        assertThat(route).isEqualTo(new Route(1, -1));
    }

    @DisplayName("남동쪽으로 움직일 수 있으면 남동쪽 방향의 Route를 반환한다.")
    @Test
    void findRoute_southeast() {
        Piece bishop = new Bishop(BLACK);
        Route route = bishop.findRoute(Position.of(FOUR, D), Position.of(TWO, F));

        assertThat(route).isEqualTo(new Route(1, 1));
    }

    @DisplayName("북서쪽으로 움직일 수 있으면 북서쪽 방향의 Route를 반환한다.")
    @Test
    void findRoute_northwest() {
        Piece bishop = new Bishop(BLACK);
        Route route = bishop.findRoute(Position.of(FOUR, D), Position.of(FIVE, C));

        assertThat(route).isEqualTo(new Route(-1, -1));
    }
}
