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
		assertThat(board.getBoard().size()).isEqualTo(64);
	}

	@Test
	@DisplayName("체스의 시작은 검은 말이다")
	void throwExcpetionWhenIllegalTurn() {
		final Position source = Position.from("b2");
		final Position target = Position.from("b3");

		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("상대팀의 순서입니다");
	}

	@Test
	@DisplayName("출발지와 도착지가 같으면 예외가 발생한다")
	void throwExcpetionWhenSamePosition() {
		final Position source = Position.from("a7");
		final Position target = Position.from("a7");

		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("출발지와 도착지는 같을 수 없습니다");
	}

	@Test
	@DisplayName("출발점에 체스말이 존재하지 않으면 예외가 발생한다")
	void throwExceptionWhenSourceNotEmpty() {
		final Position source = Position.from("b3");
		final Position target = Position.from("b4");

		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("출발점에 체스말이 존재하지 않습니다");
	}

	@Test
	@DisplayName("체스말이 같은 팀을 공격할 경우 예외가 발생한다")
	void throwExceptionWhenAttackSameTeam() {
		final Position source = Position.from("a8");
		final Position target = Position.from("a7");

		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("같은 팀은 공격할 수 없습니다");
	}

	@Test
	@DisplayName("체스말이 이동할 수 있는 방향이 아니면 예외가 발생한다")
	void throwExceptionWhenNotMovable() {
		final Position source = Position.from("b7");
		final Position target = Position.from("c6");

		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스말이 이동할 수 없는 위치입니다.");
	}

	@Test
	@DisplayName("이동 경로에 체스말이 존재하면 예외가 발생한다")
	void pieceExistInPath() {
		final Position source = Position.from("a8");
		final Position target = Position.from("a6");

		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이동할 경로에 체스말이 존재합니다.");
	}

	@Test
	@DisplayName("한 칸만 움직일 수 있는 체스말이 여러 칸을 움직이려고 할 경우, 예외가 발생한다")
	void throwExceptionWhenNotMovableByCount() {
		board.move(Position.from("e7"), Position.from("e5"));
		board.move(Position.from("a2"), Position.from("a4"));

		final Position source = Position.from("e5");
		final Position target = Position.from("e3");

		assertThatThrownBy(() -> board.move(source, target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("한 칸만 움직일 수 있는 체스말입니다.");
	}

	@Test
	@DisplayName("체스말이 성공적으로 이동하는지 확인하는 테스트")
	void movePiece() {
		final Position source = Position.from("b8");
		final Position target = Position.from("c6");

		board.move(source, target);

		assertSoftly(softly -> {
			softly.assertThat(board.getBoard().get(source).getClass()).isEqualTo(Empty.class);
			softly.assertThat(board.getBoard().get(target).getClass()).isEqualTo(Knight.class);
		});
	}
}
