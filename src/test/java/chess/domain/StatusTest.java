package chess.domain;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;

class StatusTest {

	private Board board;

	@Test
	void result() {
		Map<Position, Piece> setter = new LinkedHashMap<>();
		setter.put(A2, new Rook(A2, Team.BLACK));

		board = Board.of(setter);

		Map<Team, Double> expected = new HashMap<>();
		expected.put(Team.BLACK, 5.0);
		expected.put(Team.WHITE, 0.0);

		assertThat(Status.of(board).toMap()).isEqualTo(expected);
	}

	@Test
	void winner_Return_White() {
		Map<Position, Piece> setter = new LinkedHashMap<>();
		setter.put(A2, new Rook(A2, Team.WHITE));

		board = Board.of(setter);

		assertThat(Status.of(board).getWinner()).isEqualTo(Team.WHITE);
	}

	@Test
	void winner_Return_Black() {
		Map<Position, Piece> setter = new LinkedHashMap<>();
		setter.put(A2, new Rook(A2, Team.BLACK));

		board = Board.of(setter);

		assertThat(Status.of(board).getWinner()).isEqualTo(Team.BLACK);
	}

	@Test
	void winner_Return_Draw() {
		Map<Position, Piece> setter = new LinkedHashMap<>();
		setter.put(A2, new Rook(A2, Team.BLACK));
		setter.put(A3, new Rook(A3, Team.WHITE));

		board = Board.of(setter);

		assertThat(Status.of(board).getWinner()).isEqualTo(Team.NONE);
	}
}