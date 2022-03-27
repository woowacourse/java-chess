package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

public class BlackTurnTest {
	@Test
	void start() {
		State blackTurn = new BlackTurn(Board.create());
		assertThatThrownBy(blackTurn::start)
			.isInstanceOf(IllegalStateException.class);
	}

	@Test
	void end() {
		State blackTurn = new BlackTurn(Board.create());
		State end = blackTurn.end();
		assertThat(end).isInstanceOf(End.class);
	}

	@Test
	void move_black() {
		State blackTurn = new BlackTurn(Board.create());
		State whiteTurn = blackTurn.move(Coordinate.of("a7"), Coordinate.of("a6"));
		Piece piece = whiteTurn.getValue().get(Coordinate.of("a6"));
		assertThat(piece).isInstanceOf(Pawn.class);
	}

	@Test
	void move_white() {
		State blackTurn = new BlackTurn(Board.create());
		assertThatThrownBy(() -> blackTurn.move(Coordinate.of("a2"), Coordinate.of("a4")))
			.isInstanceOf(IllegalStateException.class);
	}
}
