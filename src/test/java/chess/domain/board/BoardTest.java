package chess.domain.board;

import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class BoardTest {
	private Board board;
	private Rook rook;

	@BeforeEach
	void setup() {
		Map<Position, Piece> pieces = new HashMap<>();
		rook = new Rook(Team.WHITE);
		pieces.put(Position.of(A, ONE), rook);
		board = new Board(pieces);
	}

	@DisplayName("Board객체 생성 테스트")
	@Test
	void constructTest() {
		assertThat(board).isInstanceOf(Board.class);
	}

	@DisplayName("move메서드를 실행하면 Map이 수정되는지 테스트")
	@Test
	void moveTest() {
		Position from = Position.of(A, ONE);
		Position to = Position.of(A, TWO);
		board.move(from, to);
		assertThat(board.getPiece(to)).isEqualTo(rook);
	}

	@DisplayName("점수 계산 테스트")
	@Test
	void calculateScoreTest() {
		board.start();
		Map<Team, Double> status = board.status();
		assertThat(status).containsOnly(entry(Team.BLACK, 38.0), entry(Team.WHITE, 38.0));
	}
}
