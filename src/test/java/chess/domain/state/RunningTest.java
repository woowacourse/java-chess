package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardFactory;
import chess.domain.Side;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RunningTest {
	@Test
	@DisplayName("게임이 종료되지 않았으면 계속 진행되는 지 테스트")
	void move() {
		State running = new Running(ChessBoardFactory.create());
		running = running.move(new Position("a2"), new Position("a4"));
		assertThat(running).isInstanceOf(Running.class);
		assertThat(running.getTurn()).isEqualTo(Side.BLACK);
	}

	@Test
	@DisplayName("게임이 종료될 조건이면 종료")
	void moveAndEnd() {
		List<Piece> pieces = new ArrayList<>(Arrays.asList(
				new King(Side.WHITE, new Position("d4")),
				new King(Side.BLACK, new Position("d5"))));
		State running = new Running(new ChessBoard(pieces));
		running = running.move(new Position("d4"), new Position("d5"));
		assertThat(running).isInstanceOf(End.class);
	}

	@Test
	@DisplayName("자기 턴이 아닌 경우에 상대 말을 움직이면 에러 처리")
	void moveWithOtherSidePiece() {
		State running = new Running(ChessBoardFactory.create());
		assertThatThrownBy(() -> running.move(new Position("a7"), new Position("a6")))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("본인의 말만 움직일 수 있습니다.");
	}

	@Test
	@DisplayName("종료된 상태를 반환하는지 테스트")
	void end() {
		State running = new Running(ChessBoardFactory.create());
		assertThat(running.end()).isInstanceOf(End.class);
	}

	@Test
	@DisplayName("상태가 종료된 상태인지 확인하는 테스트")
	void isEnd() {
		State running = new Running(ChessBoardFactory.create());
		assertThat(running.isEnd()).isFalse();
	}
}