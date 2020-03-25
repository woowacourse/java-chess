package chess;

import static chess.position.File.*;
import static chess.position.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Team;
import chess.position.Position;

public class BoardTest {
	private Board board;
	private Pawn pawn;

	@BeforeEach
	void setup() {
		Map<Position, Piece> pieces = new HashMap<>();
		pawn = new Pawn(Team.BLACK);
		pieces.put(Position.of(A, ONE), pawn);
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
		assertThat(board.getPiece(to)).isEqualTo(pawn);
	}
}
