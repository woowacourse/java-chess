package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;

class ChessGameTest {
	private ChessGame chessGame;

	@BeforeEach
	void setup() {
		chessGame = new ChessGame(BoardFactory.createNewGame());
	}

	@Test
	void constructor() {
		assertThat(new ChessGame(BoardFactory.createNewGame())).isInstanceOf(ChessGame.class);
	}

	@Test
	void move() {
		chessGame.move(Coordinates.of("B2"), Coordinates.of("B3"));
		Board board = chessGame.getBoard();
		assertThat(board.findPieceBy(Coordinates.of("B3")).get()).isInstanceOf(Pawn.class);
	}

	@Test
	void isEndOfGame() {
		chessGame.move(Coordinates.of("C2"), Coordinates.of("C3"));
		chessGame.move(Coordinates.of("D7"), Coordinates.of("D6"));
		chessGame.move(Coordinates.of("D1"), Coordinates.of("A4"));
		chessGame.move(Coordinates.of("E8"), Coordinates.of("D7"));
		chessGame.move(Coordinates.of("A4"), Coordinates.of("D7"));
		assertThat(chessGame.isEndOfGame()).isTrue();
	}

	@Test
	void getEnemyColor() {
		assertThat(chessGame.getEnemyColor()).isEqualTo(Color.BLACK);
	}
}
