package chess.piece.type;

import chess.board.ChessBoard;
import chess.board.ChessBoardCreater;
import chess.location.Location;
import chess.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @Test
    @DisplayName("갈 수 있는 곳 테스트")
    void canMove1() {
        ChessBoard chessBoard = ChessBoardCreater.create();

        Knight knight = new Knight(Team.BLACK);
        Location now = new Location(8, 'g');

        Location leftAfter = new Location(7, 'e');
        boolean leftAfterActual = knight.canMove(chessBoard.getBoard(), now, leftAfter);
        assertThat(leftAfterActual).isTrue();
    }

    @Test
    @DisplayName("갈 수 있는 곳 테스트")
    void canMove2() {
        ChessBoard chessBoard = ChessBoardCreater.create();

        Knight knight = new Knight(Team.BLACK);
        Location now = new Location(8, 'g');

        Location rightAfter = new Location(6, 'h');
        boolean rightAfterActual = knight.canMove(chessBoard.getBoard(), now, rightAfter);
        assertThat(rightAfterActual).isTrue();
    }

    @Test
    @DisplayName("갈 수 없는 곳 테스트")
    void canMove() {
        ChessBoard chessBoard = ChessBoardCreater.create();

        Knight knight = new Knight(Team.BLACK);
        Location now = new Location(8, 'g');

        Location cantAfter = new Location(2, 'c');
        boolean cantActual = knight.canMove(chessBoard.getBoard(), now, cantAfter);
        assertThat(cantActual).isFalse();
    }

}