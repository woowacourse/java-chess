package chess.model.piece;

import static chess.model.position.File.D;
import static chess.model.position.File.E;
import static chess.model.position.File.F;
import static chess.model.position.Rank.FIVE;
import static chess.model.position.Rank.FOUR;
import static chess.model.position.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.Team;
import chess.model.position.Position;
import chess.model.direction.route.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    @DisplayName("움직일 수 없는 방향이 입력되면 예외를 발생한다.")
    @Test()
    void findRoute_exception() {
        Piece king = new King(Team.BLACK, "K");

        assertThatThrownBy(() -> king.findRoute(Position.of(FOUR, D), Position.of(SIX, F)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다.");

    }

    @DisplayName("서쪽으로 움직일 수 있으면 서쪽 방향의 Route를 반환한다.")
    @Test()
    void findRoute_west() {
        Piece king = new King(Team.BLACK, "K");
        Route route = king.findRoute(Position.of(FIVE, E), Position.of(FIVE, D));

        assertThat(route).isEqualTo(new Route(0, -1));
    }

    @DisplayName("동쪽으로 움직일 수 있으면 동쪽 방향의 Route를 반환한다.")
    @Test()
    void findRoute_east() {
        Piece king = new King(Team.BLACK, "K");
        Route route = king.findRoute(Position.of(FIVE, E), Position.of(FIVE, F));

        assertThat(route).isEqualTo(new Route(0, 1));
    }

    @DisplayName("북동쪽으로 움직일 수 있으면 북동쪽 방향의 Route를 반환한다.")
    @Test()
    void findRoute_northeast() {
        Piece king = new King(Team.BLACK, "K");
        Route route = king.findRoute(Position.of(FIVE, E), Position.of(SIX, F));

        assertThat(route).isEqualTo(new Route(-1, 1));
    }

    @DisplayName("남서쪽으로 움직일 수 있으면 남서쪽 방향의 Route를 반환한다.")
    @Test()
    void findRoute_southwest() {
        Piece king = new King(Team.BLACK, "K");
        Route route = king.findRoute(Position.of(FIVE, E), Position.of(FOUR, D));

        assertThat(route).isEqualTo(new Route(1, -1));
    }
}
