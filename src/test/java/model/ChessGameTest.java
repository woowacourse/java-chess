package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import model.piece.Knight;
import model.piece.Piece;
import model.piece.Queen;
import model.position.Moving;
import model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @DisplayName("처음에 start를 입력하면 32개의 기물이 생성된다.")
    @Test
    void start() {
        final ChessGame chessGame = new ChessGame();
        chessGame.start();

        final Map<Position, Piece> board = chessGame.getBoard();
        assertThat(board.keySet()).hasSize(32);
    }

    @DisplayName("INIT 상태가 아닐 때 start를 입력하면 예외가 발생한다.")
    @Test
    void invalidStart() {
        final ChessGame chessGame = new ChessGame();
        chessGame.start();

        assertThatThrownBy(chessGame::start)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 게임이 진행중입니다.");
    }

    @DisplayName("기물이 없는 위치가 주어졌을 때 예외가 발생한다.")
    @Test
    void blankPosition() {
        final ChessGame chessGame = new ChessGame();
        chessGame.start();

        final Moving moving = new Moving(Position.from("e4"), Position.from("e5"));

        assertThatThrownBy(() -> chessGame.move(moving))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 없습니다.");
    }

    @DisplayName("이동 경로에 다른 기물이 있으면 예외를 발생시킨다.")
    @Test
    void routeContainPiece() {
        final ChessGame chessGame = new ChessGame();
        chessGame.start();

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
        chessGame.start();

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
        chessGame.start();

        final Moving moving = new Moving(Position.from("a7"), Position.from("a6"));

        assertThatThrownBy(() -> chessGame.move(moving))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자신의 기물만 이동 가능합니다.");
    }

    @DisplayName("RUNNING 상태가 아닐 때 move 명령어를 사용하면 예외가 발생한다.")
    @Test
    void invalidMove() {
        final ChessGame chessGame = new ChessGame();

        final Moving moving = new Moving(Position.from("a7"), Position.from("a6"));

        assertThatThrownBy(() -> chessGame.move(moving))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("start를 입력해야 게임이 시작됩니다.");
    }

    @DisplayName("상태가 END일 때 true값을 반환한다.")
    @Test
    void end() {
        final ChessGame chessGame = new ChessGame();
        chessGame.end();

        assertThat(chessGame.isNotEnd()).isFalse();
    }

    @DisplayName("상태가 END가 아닐 때 false값을 반환한다.")
    @Test
    void notEnd() {
        final ChessGame chessGame = new ChessGame();
        chessGame.start();

        assertThat(chessGame.isNotEnd()).isTrue();
    }

    @DisplayName("King이 잡히면 게임을 종료한다.")
    @Test
    void checkKing() {
        final ChessGame chessGame = new ChessGame();
        chessGame.start();

        Map<Position, Piece> board = chessGame.getBoard();
        board.put(Position.from("d6"), new Knight(Camp.WHITE));

        Moving moving = new Moving(Position.from("d6"), Position.from("e8"));
        chessGame.move(moving);

        assertThat(chessGame.isNotEnd()).isFalse();
    }
}
