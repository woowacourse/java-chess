package chess.domain;

import chess.domain.RuleImpl.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreCalculatorTest {

	ScoreCalculator scoreCalculator;

	@BeforeEach
	public void setUp() {
		scoreCalculator = ScoreCalculator.getInstance();
	}

	@Test
	public void 초기화_점수_총합() {
		Board board = new Board(BoardGenerator.generate());
		List<Square> squares = board.values();
		ScoreCalculator scoreCalculator = ScoreCalculator.getInstance();

		assertThat(scoreCalculator.scoreOfColor(squares, Piece.Color.WHITE))
				.isEqualTo(scoreCalculator.scoreOfColor(squares, Piece.Color.BLACK));
	}

	@Test
	public void 폰이_세로로_겹칠때_점수() {
		Map<Position, Square> map = new HashMap<>();
		Position position = Position.of("2", "a");
		map.put(position, Square.of(position, Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM)));
		position = Position.of("3", "a");
		map.put(position, Square.of(position, Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM)));
		position = Position.of("4", "a");
		map.put(position, Square.of(position, Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM)));
		position = Position.of("2", "b");
		map.put(position, Square.of(position, Piece.of(Piece.Color.WHITE, Pawn.FIRST_BOTTOM)));

		Board board = new Board(map);
		List<Square> sqaures = board.values();

		assertThat(scoreCalculator.scoreOfColor(sqaures, Piece.Color.WHITE)).isEqualTo(2.5);
	}

	@Test
	public void 화이트_말의_점수_총합() {
		Board board = new Board(BoardGenerator.generate());
		List<Square> sqaures = board.values();

		assertThat(scoreCalculator.scoreOfColor(sqaures, Piece.Color.WHITE)).isEqualTo(38);
	}

	@Test
	public void 블랙_말의_점수_총합() {
		Board board = new Board(BoardGenerator.generate());
		List<Square> sqaures = board.values();

		assertThat(scoreCalculator.scoreOfColor(sqaures, Piece.Color.BLACK)).isEqualTo(38);
	}
}
