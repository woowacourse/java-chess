package chess.piece.type;

import chess.board.ChessBoard;
import chess.location.Location;
import chess.team.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @Test
    void canMove() {
        ChessBoard chessBoard = new ChessBoard();

        King king = new King(Team.BLACK);
        Location now = new Location(8, 'e');

        Location after = new Location(7, 'e');
        boolean actual = king.canMove(chessBoard.getBoard(), now, after);
        assertThat(actual).isTrue();

        Location cantAfter = new Location(6, 'c');
        boolean cantActual = king.canMove(chessBoard.getBoard(), now, cantAfter);
        assertThat(cantActual).isFalse();
    }

    @Test
    void isKing() {
        King king = new King(Team.BLACK);

        assertThat(king.isKing()).isTrue();
    }
}