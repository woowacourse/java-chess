package chess.piece.type;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import chess.board.ChessBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.location.Location;
import chess.team.Team;

class BishopTest {

    @Test
    void canMove() {
        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(Team.BLACK);
        Location now = new Location(8, 'c');
        Location after = new Location(7, 'd');
        boolean actual = bishop.canMove(chessBoard.getBoard(), now, after);

        assertThat(actual).isTrue();

        Location cantAfter = new Location(2, 'c');
        boolean cantActual = bishop.canMove(chessBoard.getBoard(), now, cantAfter);

        assertThat(cantActual).isFalse();
    }

    @DisplayName("비숍의 목적지 중간에 장애물이 있는지 확인")
    @Test
    void name() {
        Map<Location, Piece> board = new HashMap<>();
        Bishop givenPiece = new Bishop(Team.BLACK);
        board.put(new Location(1, 'c'), givenPiece);
        board.put(new Location(2, 'd'), new Bishop(Team.WHITE));
        board.put(new Location(3, 'e'), new Bishop(Team.WHITE));

        boolean actual = givenPiece.canMove(board, new Location(1, 'c'), new Location(3, 'e'));
        assertThat(actual).isFalse();
    }

    @Test
    void isKing() {
        Bishop king = new Bishop(Team.BLACK);

        assertThat(king.isKing()).isFalse();
    }
}