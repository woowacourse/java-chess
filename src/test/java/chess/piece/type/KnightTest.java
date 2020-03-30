package chess.piece.type;

import chess.board.ChessBoard;
import chess.location.Location;
import chess.team.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @Test
    void canMove() {
        ChessBoard chessBoard = new ChessBoard();

        Knight knight = new Knight(Team.BLACK);
        Location now = new Location(8, 'g');

        Location leftAfter = new Location(7, 'e');
        boolean leftAfterActual = knight.canMove(chessBoard.getBoard(), now, leftAfter);
        assertThat(leftAfterActual).isTrue();

        Location rightAfter = new Location(6, 'h');
        boolean rightAfterActual = knight.canMove(chessBoard.getBoard(), now, rightAfter);
        assertThat(rightAfterActual).isTrue();

        Location cantAfter = new Location(2, 'c');
        boolean cantActual = knight.canMove(chessBoard.getBoard(), now, cantAfter);
        assertThat(cantActual).isFalse();
    }

}