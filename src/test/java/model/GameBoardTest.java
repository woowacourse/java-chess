package model;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import model.piece.Piece;
import model.piece.Queen;
import model.position.Column;
import model.position.Moving;
import model.position.Position;
import model.position.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameBoardTest {

    @Test
    @DisplayName("초기에는 32개의 기물이 생성된다.")
    void initPieces() {
        //given
        GameBoard gameBoard = new GameBoard();

        //when
        gameBoard.setting();

        //then
        var board = gameBoard.getBoard();

        Assertions.assertThat(board.keySet()).hasSize(32);

    }

    @Test
    @DisplayName("기물들의 시작 위치를 확인한다.")
    void checkInitialPosition() {
        //given
        GameBoard gameBoard = new GameBoard();

        //when
        gameBoard.setting();

        Map<Position, Piece> board = gameBoard.getBoard();
        StringBuilder stringBuilder = new StringBuilder();

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

        String expected = String.format("[R, N, B, Q, K, B, N, R]%n"
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
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();

        Moving moving = new Moving(Position.from("e4"), Position.from("e5"));

        //when & then
        Assertions.assertThatThrownBy(() -> gameBoard.move(moving, Camp.BLACK))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 경로에 다른 기물이 있으면 예외를 발생시킨다.")
    @Test
    void routeContainPiece() {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();

        Map<Position, Piece> board = gameBoard.getBoard();
        board.put(Position.from("e6"), new Queen(Camp.BLACK));

        Moving moving = new Moving(Position.from("e7"), Position.from("e5"));

        Assertions.assertThatThrownBy(() -> gameBoard.move(moving, Camp.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 기물이 있습니다.");
    }

    @DisplayName("도착 지점에 같은 진영의 기물이 있으면 예외를 발생시킨다.")
    @Test
    void targetPositionIsEqualCamp() {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();

        Map<Position, Piece> board = gameBoard.getBoard();
        board.put(Position.from("e5"), new Queen(Camp.BLACK));

        Moving moving = new Moving(Position.from("e7"), Position.from("e5"));

        Assertions.assertThatThrownBy(() -> gameBoard.move(moving, Camp.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 지점에 같은 진영의 기물이 있습니다.");
    }

    @Test
    @DisplayName("상대방의 기물을 이동시키려 하면 예외가 발생한다.")
    void invalidTurn() {
        //given
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();

        //when
        Moving moving = new Moving(Position.from("a7"), Position.from("a6"));

        //then
        Assertions.assertThatThrownBy(() -> gameBoard.move(moving, Camp.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자신의 기물만 이동 가능합니다.");
    }
}
