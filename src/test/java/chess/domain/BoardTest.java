package chess.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Empty;
import chess.domain.piece.Knight;
import chess.domain.position.Position;

class BoardTest {

	Board board;

	@BeforeEach
	void setUp() {
		board = Board.create();
	}

	@Test
	@DisplayName("체스판은 64개의 칸으로 이루어져 있다")
	void board64Size() {
		// then
		assertThat(board.board().size()).isEqualTo(64);
	}

	@Test
	@DisplayName("체스의 시작은 검은 말이다")
	void throwExcpetionWhenIllegalTurn() {
		// given
		final Position source = Position.from("b7");
		final Position target = Position.from("b6");


		// then
		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("상대팀의 순서입니다");
	}

	@Test
	@DisplayName("출발지와 도착지가 같으면 예외가 발생한다")
	void throwExcpetionWhenSamePosition() {
		// given
		final Position source = Position.from("a2");
		final Position target = Position.from("a2");

		// then
		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("출발지와 도착지는 같을 수 없습니다");
	}

	@Test
	@DisplayName("출발점에 체스말이 존재하지 않으면 예외가 발생한다")
	void throwExceptionWhenSourceNotEmpty() {
		// given
		final Position source = Position.from("b6");
		final Position target = Position.from("b5");

		// then
		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("출발점에 체스말이 존재하지 않습니다");
	}

	@Test
	@DisplayName("체스말이 같은 팀을 공격할 경우 예외가 발생한다")
	void throwExceptionWhenAttackSameTeam() {
		// given
		final Position source = Position.from("a1");
		final Position target = Position.from("a2");

		// then
		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("같은 팀은 공격할 수 없습니다");
	}

	@Test
	@DisplayName("체스말이 이동할 수 있는 방향이 아니면 예외가 발생한다")
	void throwExceptionWhenNotMovable() {
		// given
		final Position source = Position.from("b2");
		final Position target = Position.from("c3");

		// then
		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스말이 이동할 수 없는 위치입니다");
	}

	@Test
	@DisplayName("이동 경로에 체스말이 존재하면 예외가 발생한다")
	void pieceExistInPath() {
		// given
		final Position source = Position.from("a1");
		final Position target = Position.from("a3");

		// then
		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이동할 경로에 체스말이 존재합니다.");
	}

	@Test
	@DisplayName("한 칸만 움직일 수 있는 체스말이 여러 칸을 움직이려고 할 경우, 예외가 발생한다")
	void throwExceptionWhenNotMovableByCount() {
		// given
		board.move(Position.from("e2"), Position.from("e4"));
		board.move(Position.from("a7"), Position.from("a5"));

		final Position source = Position.from("e4");
		final Position target = Position.from("e6");

		// then
		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("한 칸만 움직일 수 있는 체스말입니다");
	}

	@Test
	@DisplayName("체스말이 성공적으로 이동하는지 확인하는 테스트")
	void movePiece() {
		// given
		final Position source = Position.from("b1");
		final Position target = Position.from("c3");

		// when
		board.move(source, target);

		// then
		assertSoftly(softly -> {
			softly.assertThat(board.board().get(source).getClass()).isEqualTo(Empty.class);
			softly.assertThat(board.board().get(target).getClass()).isEqualTo(Knight.class);
		});
	}
}
