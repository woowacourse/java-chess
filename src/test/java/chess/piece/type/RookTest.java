package chess.piece.type;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import chess.board.ChessBoard;
import chess.board.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.location.Location;
import chess.team.Team;

class RookTest {
    @Test
    void canMove() {
        Rook rook = new Rook(Team.BLACK);
        Location now = new Location(8, 'a');
        Location after = new Location(8, 'h');

        Route route = new Route(Collections.EMPTY_MAP, now, after);

        boolean actual = rook.canMove(route);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("갈 수 없는 곳 테스트")
    void cantMove() {
        Rook rook = new Rook(Team.BLACK);
        Location now = new Location(8, 'a');
        Location cantAfter = new Location(7, 'b');

        Route route = new Route(Collections.EMPTY_MAP, now, cantAfter);

        boolean cantActual = rook.canMove(route);

        assertThat(cantActual).isFalse();
    }

    @DisplayName("룩 세로 목적지 중간에 장애물이 있는지 확인")
    @Test
    void name() {
        Map<Location, Piece> board = new HashMap<>();

        Rook givenPiece = new Rook(Team.BLACK);
        Location now = new Location(1, 'c');
        board.put(now, givenPiece);
        board.put(new Location(2, 'c'), new Bishop(Team.WHITE));
        Location after = new Location(3, 'c');
        board.put(after, new Bishop(Team.WHITE));

        Route route = new Route(board, now, after);

        boolean actual = givenPiece.canMove(route);
        assertThat(actual).isFalse();
    }

    @DisplayName("룩 가로 목적지 중간에 장애물이 있는지 확인")
    @Test
    void name2() {
        Map<Location, Piece> board = new HashMap<>();

        Rook givenPiece = new Rook(Team.BLACK);
        Location now = new Location(1, 'a');
        board.put(now, givenPiece);
        board.put(new Location(1, 'c'), null);
        board.put(new Location(1, 'b'), new Bishop(Team.WHITE));
        Location after = new Location(1, 'd');
        board.put(after, new Bishop(Team.WHITE));

        Route route = new Route(board, now, after);

        boolean actual = givenPiece.canMove(route);
        assertThat(actual).isFalse();
    }
}