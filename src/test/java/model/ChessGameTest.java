package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import model.piece.Piece;
import model.piece.Queen;
import model.position.Moving;
import model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @DisplayName("초기에는 32개의 기물이 생성된다.")
    @Test
    void initPieces() {
        final ChessGame chessGame = new ChessGame();

        final Map<Position, Piece> board = chessGame.getBoard();
        assertThat(board.keySet()).hasSize(32);
    }

    @DisplayName("기물들의 시작 위치를 확인한다.")
    @Test
    void checkInitialPosition() {
        final ChessGame chessGame = new ChessGame();

        final Map<Position, Piece> board = chessGame.getBoard();
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

        assertThat(stringBuilder.toString()).hasToString(expected);
    }

    @DisplayName("기물이 없는 위치가 주어졌을 때 예외가 발생한다.")
    @Test
    void blankPosition() {
        final ChessGame chessGame = new ChessGame();

        final Moving moving = new Moving(Position.from("e4"), Position.from("e5"));

        assertThatThrownBy(() -> chessGame.move(moving))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 없습니다.");
    }

    @DisplayName("이동 경로에 다른 기물이 있으면 예외를 발생시킨다.")
    @Test
    void routeContainPiece() {
        final ChessGame chessGame = new ChessGame();

        final Map<Position, Piece> board = chessGame.getBoard();
        board.put(Position.from("e3"), new Queen(Camp.WHITE));

        final Moving moving = new Moving(Position.from("e2"), Position.from("e4"));

        assertThatThrownBy(() -> chessGame.move(moving))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 기물이 있습니다.");
    }

    @DisplayName("도착 지점에 같은 진영의 기물이 있으면 예외를 발생시킨다.")
    @Test
    void targetPositionIsEqualCamp() {
        final ChessGame chessGame = new ChessGame();

        final Map<Position, Piece> board = chessGame.getBoard();
        board.put(Position.from("f3"), new Queen(Camp.WHITE));

        final Moving moving = new Moving(Position.from("g1"), Position.from("f3"));

        assertThatThrownBy(() -> chessGame.move(moving))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 지점에 같은 진영의 기물이 있습니다.");
    }

    @DisplayName("상대방의 기물을 이동시키려 하면 예외가 발생한다.")
    @Test
    void invalidTurn() {
        final ChessGame chessGame = new ChessGame();

        final Moving moving = new Moving(Position.from("a7"), Position.from("a6"));

        assertThatThrownBy(() -> chessGame.move(moving))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자신의 기물만 이동 가능합니다.");
    }
}
