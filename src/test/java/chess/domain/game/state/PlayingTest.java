package chess.domain.game.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.game.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.exception.InvalidTurnException;
import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class PlayingTest {
    private State state;

    @BeforeEach
    void setUp() {
        state = new Playing(Board.create(), Turn.WHITE);
    }

    @Test
    @DisplayName("플레잉 생성")
    void constructor() {
        assertThat(new Playing(Board.create(), Turn.from(Color.WHITE))).isInstanceOf(Playing.class);
    }

    @Test
    @DisplayName("게임 진행중 시작시 예외 발생")
    void start() {
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
            () -> new Playing(Board.create(), Turn.from(Color.WHITE)).start());
    }

    @Test
    @DisplayName("게임 종료 상태 생성")
    void end() {
        assertThat(new Playing(Board.create(), Turn.from(Color.WHITE)).end()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("게임 중 체스 말 이동")
    void move() {
        assertThat(new Playing(Board.create(), Turn.from(Color.WHITE)).move(Position.from("a2"), Position
            .from("a3"))).isInstanceOf(State.class);
    }

    @Test
    @DisplayName("게임 중 체스판 확인")
    void board() {
        assertThat(new Playing(Board.create(), Turn.from(Color.WHITE)).board()).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("게임 중 상태는 종료 상태가 아니다")
    void isFinished() {
        assertThat(new Playing(Board.create(), Turn.from(Color.WHITE)).isFinished()).isFalse();
    }

    @Test
    @DisplayName("상대방 턴에 말을 움직일 경우 예외 발생")
    void move_invalid_turn() {
        Position source = Position.from("a7");
        Position target = Position.from("a6");

        assertThatExceptionOfType(InvalidTurnException.class).isThrownBy(
            () -> new ChessGame(state).move(source, target)
        );
    }

    @Test
    @DisplayName("체스판 초기화 상태의 점수 계산")
    void calculateScore() {
        assertThat(state.score(Color.WHITE).getValue()).isEqualTo(38);
        assertThat(state.score(Color.BLACK).getValue()).isEqualTo(38);
    }

    @Test
    @DisplayName("왕이 잡힌 경우 게임 종료")
    void isGameOver() {
        Board board = state.board();
        board.findPiece(Position.from("e2")).move(board.findPiece(Position.from("e3")));
        board.findPiece(Position.from("e3")).move(board.findPiece(Position.from("e4")));
        board.findPiece(Position.from("e4")).move(board.findPiece(Position.from("e5")));
        board.findPiece(Position.from("e5")).move(board.findPiece(Position.from("e6")));
        board.findPiece(Position.from("e6")).move(board.findPiece(Position.from("f7")));
        board.findPiece(Position.from("d7")).move(board.findPiece(Position.from("d6")));
        board.findPiece(Position.from("d8")).move(board.findPiece(Position.from("d7")));
        board.findPiece(Position.from("d7")).move(board.findPiece(Position.from("e6")));
        state = state.move(Position.from("a2"), Position.from("a3"));
        state = state.move(Position.from("e6"), Position.from("e1"));
        assertThat(state.isFinished()).isTrue();
    }

    @Test
    @DisplayName("같은 세로 줄에 같은 색의 폰이 있는 경우 0.5점으로 계산")
    void half_score_When_PawnIsOnSameFile() {
        Board board = state.board();
        board.findPiece(Position.from("a2")).move(board.findPiece(Position.from("a4")));
        board.findPiece(Position.from("b7")).move(board.findPiece(Position.from("b5")));
        state = state.move(Position.from("a4"), Position.from("b5"));
        assertThat(state.score(Color.WHITE).getValue()).isEqualTo(37);
        assertThat(state.score(Color.BLACK).getValue()).isEqualTo(37);
    }
}
