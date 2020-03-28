package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import chess.factory.BoardFactory;

public class StatusTest {

	@Test
	void getResultTest() {
		Status status = new Status(BoardFactory.createBoard());

		assertThat(status.getResult().getScore()).isEqualTo(38);
		assertThat(status.getResult().getWinTeam()).isEqualTo("WHITE");
	}
}
