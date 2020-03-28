package chess.domain.game.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.game.Board;
import chess.domain.game.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class PlayingTest {
	private State state;

	@BeforeEach
	void setup() {
		state = new Playing(Board.create(), Turn.WHITE);
	}

	@Test
	@DisplayName("플레잉 생성")
	void constructor() {
		assertThat(state).isInstanceOf(Playing.class);
	}

	@Test
	@DisplayName("게임 진행중 시작시 예외 발생")
	void start() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> state.start());
	}

	@Test
	@DisplayName("게임 종료 상태 생성")
	void end() {
		assertThat(state.end()).isInstanceOf(Finished.class);
	}

	@Test
	@DisplayName("게임 중 체스 말 이동")
	void move() {
		assertThat(state.move(Position.from("a2"), Position.from("a3"))).isInstanceOf(State.class);
	}

	@Test
	@DisplayName("게임 중 체스판 확인")
	void board() {
		assertThat(state.board()).isInstanceOf(Board.class);
	}

	@Test
	@DisplayName("게임 중 상태는 종료 상태가 아니다")
	void isFinished() {
		assertThat(state.isFinished()).isFalse();
	}

	@Test
	@DisplayName("초기 게임 점수 확인")
	void score() {
		assertThat(state.score(Color.WHITE).getValue()).isEqualTo(38);
		assertThat(state.score(Color.BLACK).getValue()).isEqualTo(38);
	}

	@Test
	void end_king_dead() {
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
}
