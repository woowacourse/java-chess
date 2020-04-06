package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import chess.domain.Board;
import chess.domain.piece.Piece;

class ScoreTest {
	@Test
	void isBiggerThanTest() {
		assertThat(Score.of(15).isBiggerThan(Score.of(14))).isTrue();
	}

	@Test
	void calculateScoreTest() {
		Board board = BoardRepository.create();
		Collection<Piece> pieces = board.getBoard().values();
		assertThat(Score.calculate(new ArrayList<>(pieces)).getScore()).isEqualTo(69);
	}
}