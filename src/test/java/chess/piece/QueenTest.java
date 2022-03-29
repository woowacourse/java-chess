package chess.piece;

import static chess.File.D;
import static chess.File.E;
import static chess.File.F;
import static chess.Rank.FIVE;
import static chess.Rank.FOUR;
import static chess.Rank.SIX;
import static org.assertj.core.api.Assertions.assertThat;

import chess.Player;
import chess.Position;
import chess.direction.route.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @DisplayName("서쪽으로 움직일 수 있으면 서쪽 방향의 Route를 반환한다.")
    @Test()
    void findRoute_west() {
        Piece queen = new Queen(Player.BLACK, "K");
        Route route = queen.findRoute(Position.of(FIVE, E), Position.of(FIVE, D));

        assertThat(route).isEqualTo(new Route(0, -1));
    }

    @DisplayName("동쪽으로 움직일 수 있으면 동쪽 방향의 Route를 반환한다.")
    @Test()
    void findRoute_east() {
        Piece queen = new Queen(Player.BLACK, "K");
        Route route = queen.findRoute(Position.of(FIVE, E), Position.of(FIVE, F));

        assertThat(route).isEqualTo(new Route(0, 1));
    }

    @DisplayName("북동쪽으로 움직일 수 있으면 북동쪽 방향의 Route를 반환한다.")
    @Test()
    void findRoute_northeast() {
        Piece queen = new Queen(Player.BLACK, "K");
        Route route = queen.findRoute(Position.of(FIVE, E), Position.of(SIX, F));

        assertThat(route).isEqualTo(new Route(-1, 1));
    }

    @DisplayName("남서쪽으로 움직일 수 있으면 남서쪽 방향의 Route를 반환한다.")
    @Test()
    void findRoute_southwest() {
        Piece queen = new Queen(Player.BLACK, "K");
        Route route = queen.findRoute(Position.of(FIVE, E), Position.of(FOUR, D));

        assertThat(route).isEqualTo(new Route(1, -1));
    }
}
