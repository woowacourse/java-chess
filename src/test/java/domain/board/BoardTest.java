package domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domain.board.fixture.PawnBoard;
import domain.board.fixture.RookBoard;
import domain.command.MoveCommand;
import domain.piece.position.InvalidPositionException;
import domain.piece.team.Team;

public class BoardTest {
	private Board board;

	@BeforeEach
	void setUp() {
		board = BoardFactory.create();
	}
	@DisplayName("King이 살았을 때 isKingAlive를 호출하면 true반환")
	@Test
	void isKingAlive_KingIsAlive_ReturnTrue() {
		assertThat(board.isKingAlive()).isTrue();
	}

	@DisplayName("King이 죽었을 때 isKingAlive를 호출하면 false반환")
	@Test
	void isKingAlive_KingIsDead_ReturnFalse() {
		BoardGame boardWithoutKing = new BoardGame(new PawnBoard().create());
		assertThat(boardWithoutKing.isKingAlive()).isFalse();
	}
}
