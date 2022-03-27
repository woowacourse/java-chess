package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

public class WhiteTurnTest {
	@Test
	void start() {
		State whiteTurn = new WhiteTurn(Board.create());
		assertThatThrownBy(() -> whiteTurn.start())
			.isInstanceOf(IllegalStateException.class);
	}

	@Test
	void end() {
		State whiteTurn = new WhiteTurn(Board.create());
		State end = whiteTurn.end();
		assertThat(end).isInstanceOf(End.class);
	}

	@Test
	void move_white() {
		State whiteTurn = new WhiteTurn(Board.create());
		State blackTurn = whiteTurn.move(Coordinate.of("a2"), Coordinate.of("a4"));
		Piece piece = blackTurn.getValue().get(Coordinate.of("a4"));
		assertThat(piece).isInstanceOf(Pawn.class);
	}

	@Test
	void move_black() {
		State whiteTurn = new WhiteTurn(Board.create());
		assertThatThrownBy(() -> whiteTurn.move(Coordinate.of("a7"), Coordinate.of("a6")))
			.isInstanceOf(IllegalStateException.class);
	}
}
