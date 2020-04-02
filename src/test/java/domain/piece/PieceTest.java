package domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.piece.position.Position;
import domain.piece.team.Team;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class PieceTest {
	@Test
	void showSymbolTest() {
		assertThat(new Queen(Position.of("a3"), Team.WHITE).showSymbol()).isEqualTo("q");
	}
}