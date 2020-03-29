package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import chess.domain.chessboard.ChessBoard;
import chess.factory.BoardFactory;

public class StatusTest {

	@Test
	void getResultTest() {
		ChessBoard chessBoard = BoardFactory.createBoard();
		Status status = chessBoard.createStatus();

		assertThat(status.getResult().getScore()).isEqualTo(38);
		assertThat(status.getResult().getWinTeam()).isEqualTo("WHITE");
	}
}
