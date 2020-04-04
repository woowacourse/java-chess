package chess.domain.state;

import static chess.domain.piece.Team.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

class KingCatchFinishedTest {
	@DisplayName("게임보드 위에 왕이 하나가 아닌 경우, Finish 객체 생성시 예외발생")
	@Test
	void constructException_without_any_king_Test() {
		assertThatThrownBy(() -> new KingCatchFinished(new Board(), BLACK))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("보드위에 남아있는 킹의 팀과 승리한 팀이 일치하지 않는 경우 예외발생")
	@Test
	void constructException_with_wrong_team_Test() {
		Map<Position, Piece> map = new HashMap<>();
		map.put(Position.of("a1"), new King(WHITE));
		assertThatThrownBy(() -> new KingCatchFinished(new Board(map), BLACK))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("왕을 잡힌 시점에 게임을 했던 플레이어(팀) 승리")
	@Test
	void getWinnerTest() {
		Map<Position, Piece> map = new HashMap<>();
		map.put(Position.of("a1"), new King(BLACK));
		GameState state = new KingCatchFinished(new Board(map), BLACK);
		Team actual = state.getWinner();
		assertThat(actual).isEqualTo(BLACK);
	}
}