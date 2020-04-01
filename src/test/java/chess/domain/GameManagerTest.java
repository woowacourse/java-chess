package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;

class GameManagerTest {
	private GameManager gameManager;

	@BeforeEach
	void setup() {
		gameManager = new GameManager(BoardFactory.createNewGame());
	}

	@Test
	void constructor() {
		assertThat(new GameManager(BoardFactory.createNewGame())).isInstanceOf(GameManager.class);
	}

	@Test
	void move() {
		gameManager.move(Coordinates.of("B2"), Coordinates.of("B3"));
		Board board = gameManager.getBoard();
		assertThat(board.findPieceBy(Coordinates.of("B3")).get()).isInstanceOf(Pawn.class);
	}

	@Test
	void isEndOfGame() {
		gameManager.move(Coordinates.of("C2"), Coordinates.of("C3"));
		gameManager.move(Coordinates.of("D7"), Coordinates.of("D6"));
		gameManager.move(Coordinates.of("D1"), Coordinates.of("A4"));
		gameManager.move(Coordinates.of("E8"), Coordinates.of("D7"));
		gameManager.move(Coordinates.of("A4"), Coordinates.of("D7"));
		assertThat(gameManager.isEndOfGame()).isTrue();
	}

	@Test
	void getEnemyColor() {
		assertThat(gameManager.getEnemyColor()).isEqualTo(Color.BLACK);
	}
}
