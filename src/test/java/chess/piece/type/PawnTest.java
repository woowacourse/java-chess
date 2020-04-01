package chess.piece.type;

import chess.board.ChessBoard;
import chess.board.ChessBoardCreater;
import chess.location.Location;
import chess.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @Test
    @DisplayName("초기 위치의 폰의 2단 이동 테스트")
    void canMove1() {
        ChessBoard chessBoard = ChessBoardCreater.create();

        Pawn pawn = new Pawn(Team.BLACK);
        Location now = new Location(7, 'a');

        Location moveTwiceForward = new Location(5, 'a');
        boolean moveTwiceForwardActual = pawn.canMove(chessBoard.getBoard(), now, moveTwiceForward);
        assertThat(moveTwiceForwardActual).isTrue();
    }

    @Test
    @DisplayName("초기 위치의 폰의 1단 이동 테스트")
    void canMove2() {
        ChessBoard chessBoard = ChessBoardCreater.create();

        Pawn pawn = new Pawn(Team.BLACK);
        Location now = new Location(7, 'a');

        Location moveOnceForward = new Location(6, 'a');
        boolean moveOnceForwardActual = pawn.canMove(chessBoard.getBoard(), now, moveOnceForward);
        assertThat(moveOnceForwardActual).isTrue();
    }

    @Test
    @DisplayName("초기 위치의 폰의 대각선 이동 테스트")
    void canMove3() {
        ChessBoard chessBoard = ChessBoardCreater.create();

        Pawn pawn = new Pawn(Team.BLACK);
        Location now = new Location(7, 'a');

        Location moveDiagonal = new Location(6, 'b');
        boolean moveDiagonalActual = pawn.canMove(chessBoard.getBoard(), now, moveDiagonal);
        assertThat(moveDiagonalActual).isTrue();
    }

    @Test
    @DisplayName("초기 위치의 폰의 이동 테스트")
    void cantMove() {
        ChessBoard chessBoard = ChessBoardCreater.create();

        Pawn pawn = new Pawn(Team.BLACK);
        Location now = new Location(7, 'a');

        Location cantAfter = new Location(4, 'a');
        boolean cantActual = pawn.canMove(chessBoard.getBoard(), now, cantAfter);
        assertThat(cantActual).isFalse();
    }

    @Test
    @DisplayName("초기 위치가 아닌 일반적인 폰의 1단 이동")
    void canMove4() {
        ChessBoard chessBoard = ChessBoardCreater.create();

        Pawn pawn = new Pawn(Team.BLACK);
        Location now = new Location(6, 'a');

        Location moveOnceForward = new Location(5, 'a');
        boolean moveOnceForwardActual = pawn.canMove(chessBoard.getBoard(), now, moveOnceForward);
        assertThat(moveOnceForwardActual).isTrue();
    }

    @Test
    @DisplayName("초기 위치가 아닌 일반적인 폰의 대각선 이동")
    void canMove5() {
        ChessBoard chessBoard = ChessBoardCreater.create();

        Pawn pawn = new Pawn(Team.BLACK);
        Location now = new Location(6, 'a');

        Location moveDiagonal = new Location(5, 'b');
        boolean moveDiagonalAcutal = pawn.canMove(chessBoard.getBoard(), now, moveDiagonal);
        assertThat(moveDiagonalAcutal).isTrue();
    }

    @Test
    @DisplayName("초기 위치가 아닌 일반적인 폰의 2단 이동")
    void cantMove2() {
        ChessBoard chessBoard = ChessBoardCreater.create();

        Pawn pawn = new Pawn(Team.BLACK);
        Location now = new Location(6, 'a');

        Location moveTwiceForward = new Location(4, 'a');
        boolean moveTwiceForwardActual = pawn.canMove(chessBoard.getBoard(), now, moveTwiceForward);
        assertThat(moveTwiceForwardActual).isFalse();
    }

    @DisplayName("폰의 대각선 위치에 적이 있는 경우")
    @Test
    void name() {
        Map<Location, Piece> board = new HashMap<>();
        Pawn givenPiece = new Pawn(Team.BLACK);
        board.put(new Location(7, 'a'), givenPiece);
        board.put(new Location(6, 'b'), new Bishop(Team.WHITE));

        boolean actual = givenPiece.canMove(board, new Location(7, 'a'), new Location(6, 'b'));
        assertThat(actual).isTrue();
    }

    @DisplayName("폰의 대각선 위치에 적이 없는 경우")
    @Test
    void name2() {
        Map<Location, Piece> board = new HashMap<>();
        Pawn givenPiece = new Pawn(Team.BLACK);
        board.put(new Location(7, 'a'), givenPiece);

        boolean actual = givenPiece.canMove(board, new Location(7, 'a'), new Location(6, 'b'));
        assertThat(actual).isTrue();
    }

    @DisplayName("폰의 두 칸의 직선 위치로 가는 중 적이 있는 경우")
    @Test
    void name3() {
        Map<Location, Piece> board = new HashMap<>();
        Pawn givenPiece = new Pawn(Team.BLACK);
        Pawn counterPiece = new Pawn(Team.WHITE);
        Pawn destinaionPiece = new Pawn(Team.WHITE);

        board.put(new Location(7, 'a'), givenPiece);
        board.put(new Location(6, 'a'), counterPiece);
        board.put(new Location(5, 'a'), destinaionPiece);

        boolean actual = givenPiece.canMove(board, new Location(7, 'a'), new Location(5, 'a'));
        assertThat(actual).isFalse();
    }

    @DisplayName("폰의 직선 위치에 적이 있는 경우")
    @Test
    void name4() {
        Map<Location, Piece> board = new HashMap<>();
        Pawn givenPiece = new Pawn(Team.BLACK);
        Pawn counterPiece = new Pawn(Team.WHITE);
        board.put(new Location(7, 'a'), givenPiece);
        board.put(new Location(6, 'a'), counterPiece);

        boolean actual = givenPiece.canMove(board, new Location(7, 'a'), new Location(6, 'a'));
        assertThat(actual).isFalse();
    }

    @DisplayName("폰의 직선 위치에 적이 있는 경우")
    @Test
    void name5() {
        Map<Location, Piece> board = new HashMap<>();
        Pawn givenPiece = new Pawn(Team.BLACK);
        Pawn destinaionPiece = new Pawn(Team.WHITE);
        board.put(new Location(7, 'a'), givenPiece);
        board.put(new Location(5, 'a'), destinaionPiece);

        boolean actual = givenPiece.canMove(board, new Location(7, 'a'), new Location(5, 'a'));
        assertThat(actual).isFalse();
    }
}