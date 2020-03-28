package chess.factory;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import chess.domain.chessboard.Row;

public class ChessPieceFactoryTest {

	@Test
	void create() {
		List<Row> rows = BoardFactory.createBoard();
		assertThat(rows.size()).isEqualTo(8);
	}
}
