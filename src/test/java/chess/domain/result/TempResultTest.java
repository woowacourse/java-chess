package chess.domain.result;

import static chess.domain.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Board;
import chess.domain.Team;

class TempResultTest {

	@Test
	@DisplayName("초기 상황에서의 승패 결과 테스트")
	void initBoardResultTest() {
		Board initialBoard = Board.create();
		Result result = TempResult.from(initialBoard.getBoard());

		assertThat(result.getWinner()).isEqualTo(Team.EMPTY);
		assertThat(result.getWhiteScoreMultipliedBy10()).isEqualTo(38 * 10);
		assertThat(result.getBlackScoreMultipliedBy10()).isEqualTo(38 * 10);
	}

	/*
	RNBQKBNR
	PPpPPPPP
	........
	........
	........
	........
	p.pppppp
	rnbqkbnr
	 */
	@Test
	@DisplayName("백팀이 흑팀 폰을 하나 잡았을 때의  결과 테스트")
	void DrawResultTest() {
		Board board = Board.create();
		board.movePiece(Team.WHITE, B2, B4);
		board.movePiece(Team.WHITE, B4, B5);
		board.movePiece(Team.WHITE, B5, B6);
		board.movePiece(Team.WHITE, B6, C7);

		Result result = TempResult.from(board.getBoard());

		assertThat(result.getWinner()).isEqualTo(Team.EMPTY);
		assertThat(result.getWhiteScoreMultipliedBy10()).isEqualTo(370);
		assertThat(result.getBlackScoreMultipliedBy10()).isEqualTo(370);
	}

	/*
	RpBQKBNR
	PP.PPPPP
	........
	........
	........
	........
	p.pppppp
	rnbqkbnr
	 */
	@Test
	@DisplayName("백팀이 흑팀 폰과 나이트를 하나 잡았을 때의 승패 결과 테스트")
	void whiteWinResultTest() {
		Board board = Board.create();
		board.movePiece(Team.WHITE, B2, B4);
		board.movePiece(Team.WHITE, B4, B5);
		board.movePiece(Team.WHITE, B5, B6);
		board.movePiece(Team.WHITE, B6, C7);
		board.movePiece(Team.WHITE, C7, B8);

		Result result = TempResult.from(board.getBoard());

		assertThat(result.getWinner()).isEqualTo(Team.WHITE);
		assertThat(result.getWhiteScoreMultipliedBy10()).isEqualTo(380);
		assertThat(result.getBlackScoreMultipliedBy10()).isEqualTo(345);
	}
}
