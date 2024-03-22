package model;

import static model.Fixtures.A6;
import static model.Fixtures.A7;
import static model.Fixtures.E4;
import static model.Fixtures.E5;
import static model.Fixtures.E6;
import static model.Fixtures.E7;
import static model.Fixtures.F6;
import static model.Fixtures.G8;

import exception.InvalidTurnException;
import exception.PieceDoesNotExistException;
import exception.PieceExistInRouteException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import model.piece.Piece;
import model.piece.Queen;
import model.position.Moving;
import model.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameBoardTest {

    @Test
    @DisplayName("초기에는 32개의 기물이 생성된다.")
    void initPieces() {
        //given
        final GameBoard gameBoard = new GameBoard();

        //when
        gameBoard.setting();

        //then
        final Map<Position, Piece> board = gameBoard.getBoard();
        Assertions.assertThat(board.keySet()).hasSize(32);
    }

    @Test
    @DisplayName("기물들의 시작 위치를 확인한다.")
    void checkInitialPosition() {
        //given
        final GameBoard gameBoard = new GameBoard();

        //when
        gameBoard.setting();

        final Map<Position, Piece> board = gameBoard.getBoard();
        final StringBuilder stringBuilder = new StringBuilder();

        String[][] res = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                res[i][j] = ".";
            }
        }

        for (Entry<Position, Piece> entry : board.entrySet()) {
            res[entry.getKey().getRowIndex()][entry.getKey().getColumnIndex()] = entry.getValue()
                    .toString();
        }

        for (String[] ans : res) {
            stringBuilder.append(Arrays.toString(ans));
            stringBuilder.append(System.lineSeparator());
        }

        final String expected = String.format("[R, N, B, Q, K, B, N, R]%n"
                + "[P, P, P, P, P, P, P, P]%n"
                + "[., ., ., ., ., ., ., .]%n"
                + "[., ., ., ., ., ., ., .]%n"
                + "[., ., ., ., ., ., ., .]%n"
                + "[., ., ., ., ., ., ., .]%n"
                + "[p, p, p, p, p, p, p, p]%n"
                + "[r, n, b, q, k, b, n, r]%n");

        //then
        Assertions.assertThat(stringBuilder.toString()).hasToString(expected);
    }

    @Test
    @DisplayName("기물이 없는 위치가 주어졌을 때 예외가 발생한다.")
    void blankPosition() {
        //given
        final GameBoard gameBoard = new GameBoard();
        gameBoard.setting();

        final Moving moving = new Moving(E4, E5);

        //when & then
        Assertions.assertThatThrownBy(() -> gameBoard.move(moving, Camp.BLACK))
                .isInstanceOf(PieceDoesNotExistException.class);
    }

    @DisplayName("이동 경로에 다른 기물이 있으면 예외를 발생시킨다.")
    @Test
    void routeContainPiece() {
        final GameBoard gameBoard = new GameBoard();
        gameBoard.setting();

        final Map<Position, Piece> board = gameBoard.getBoard();
        board.put(E6, new Queen(Camp.BLACK));

        final Moving moving = new Moving(E7, E5);

        Assertions.assertThatThrownBy(() -> gameBoard.move(moving, Camp.BLACK))
                .isInstanceOf(PieceExistInRouteException.class);
    }

    //TODO 폰 테스트 필히 추가

    @DisplayName("도착 지점에 같은 진영의 기물이 있으면 예외를 발생시킨다.")
    @Test
    void targetPositionIsEqualCamp() {
        final GameBoard gameBoard = new GameBoard();
        gameBoard.setting();

        final Map<Position, Piece> board = gameBoard.getBoard();
        board.put(F6, new Queen(Camp.BLACK));

        final Moving moving = new Moving(G8, F6);

        Assertions.assertThatThrownBy(() -> gameBoard.move(moving, Camp.BLACK))
                .isInstanceOf(PieceExistInRouteException.class);
    }

    @Test
    @DisplayName("상대방의 기물을 이동시키려 하면 예외가 발생한다.")
    void invalidTurn() {
        //given
        final GameBoard gameBoard = new GameBoard();
        gameBoard.setting();

        //when
        final Moving moving = new Moving(A7, A6);

        //then
        Assertions.assertThatThrownBy(() -> gameBoard.move(moving, Camp.WHITE))
                .isInstanceOf(InvalidTurnException.class);
    }
}
