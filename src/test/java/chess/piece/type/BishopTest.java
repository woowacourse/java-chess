package chess.piece.type;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import chess.board.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.location.Location;
import chess.team.Team;

class BishopTest {

    @Test
    @DisplayName("갈 수 있는 곳 테스트")
    void canMove() {
        Bishop bishop = new Bishop(Team.BLACK);
        Location now = new Location(8, 'c');
        Location after = new Location(7, 'd');
        Route route = new Route(Collections.EMPTY_MAP, now, after);
        boolean actual = bishop.canMove(route);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("갈 수 없는 곳 확인")
    void canMove2() {
        Bishop bishop = new Bishop(Team.BLACK);
        Location now = new Location(8, 'c');
        Location cantAfter = new Location(2, 'c');
        Route route = new Route(Collections.EMPTY_MAP, now, cantAfter);

        boolean cantActual = bishop.canMove(route);

        assertThat(cantActual).isFalse();
    }

    @DisplayName("비숍의 목적지 중간에 장애물이 있는지 확인")
    @Test
    void name() {
        Map<Location, Piece> board = new HashMap<>();

        Bishop givenPiece = new Bishop(Team.BLACK);
        Location now = new Location(1, 'c');
        board.put(now, givenPiece);
        board.put(new Location(2, 'd'), new Bishop(Team.WHITE));
        Location after = new Location(3, 'e');
        board.put(after, new Bishop(Team.WHITE));

        Route route = new Route(board, now, after);
        boolean actual = givenPiece.canMove(route);
        assertThat(actual).isFalse();
    }

    @Test
    void isKing() {
        Bishop king = new Bishop(Team.BLACK);

        assertThat(king.isKing()).isFalse();
    }
}