package chess.piece.type;

import chess.board.ChessBoard;
import chess.board.ChessBoardCreater;
import chess.board.Route;
import chess.location.Location;
import chess.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @Test
    @DisplayName("갈 수 있는 곳 테스트")
    void canMove() {
        ChessBoard chessBoard = ChessBoardCreater.create();

        King king = new King(Team.BLACK);
        Location now = new Location(5, 'e');

        int[] dx = {-1, 0, 1};
        int[] dy = {-1, 0, 1};

        for (int i = 0; i < dx.length; i++) {
            for (int j = 0; j < dy.length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                Location after = new Location(now.getRowValue() + dx[i], (char) (now.getColValue() + dy[j]));
                Route route = new Route(Collections.EMPTY_MAP, now, after);

                boolean afterActual = king.canMove(route);

                assertThat(afterActual).isTrue();
            }
        }
    }

    @Test
    @DisplayName("갈 수 없는 곳 테스트")
    void canMove2() {
        ChessBoard chessBoard = ChessBoardCreater.create();

        King king = new King(Team.BLACK);
        Location now = new Location(8, 'e');
        Location cantAfter = new Location(6, 'c');

        Route route = new Route(Collections.EMPTY_MAP, now, cantAfter);
        boolean cantActual = king.canMove(route);
        assertThat(cantActual).isFalse();
    }

    @Test
    void isKing() {
        King king = new King(Team.BLACK);

        assertThat(king.isKing()).isTrue();
    }
}