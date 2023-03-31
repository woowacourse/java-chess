package chess.domain.result;

import static chess.domain.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Bishop;
import chess.domain.piece.InitialPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

class ScoreTest {

	@Nested
	@DisplayName("폰 점수 추가 테스트")
	class AddPawnScoreTest {

		@Test
		@DisplayName("각 팀의 폰이 하나 있다면 점수의 10배는 10이 된다.")
		void addPawnScoreTest() {
			Map<Position, Piece> board = Map.of(
				A1, new Pawn(Team.WHITE),
				B1, new Pawn(Team.BLACK)
			);
			Score whiteScore = Score.of(Team.WHITE, board);
			Score blackScore = Score.of(Team.BLACK, board);

			assertThat(whiteScore.getScoreMultipliedBy10()).isEqualTo(10);
			assertThat(blackScore.getScoreMultipliedBy10()).isEqualTo(10);
		}

		@Test
		@DisplayName("같은 열에 각 팀의 폰이 둘 있다면 점수의 10배는 10이 된다.")
		void add2PawnsScoreTest() {
			Map<Position, Piece> board = Map.of(
				B1, new Pawn(Team.WHITE),
				B2, new Pawn(Team.WHITE),
				C1, new Pawn(Team.BLACK),
				C2, new Pawn(Team.BLACK)
			);
			Score whiteScore = Score.of(Team.WHITE, board);
			Score blackScore = Score.of(Team.BLACK, board);

			assertThat(whiteScore.getScoreMultipliedBy10()).isEqualTo(10);
			assertThat(blackScore.getScoreMultipliedBy10()).isEqualTo(10);
		}

		@Test
		@DisplayName("같은 열에 각 팀의 폰이 셋 있다면 점수의 10배는 15가 된다.")
		void add3PawnsScoreTest() {
			Map<Position, Piece> board = Map.of(
				B1, new Pawn(Team.WHITE),
				B2, new Pawn(Team.WHITE),
				B3, new Pawn(Team.WHITE),
				C1, new Pawn(Team.BLACK),
				C2, new Pawn(Team.BLACK),
				C3, new Pawn(Team.BLACK)
			);
			Score whiteScore = Score.of(Team.WHITE, board);
			Score blackScore = Score.of(Team.BLACK, board);

			assertThat(whiteScore.getScoreMultipliedBy10()).isEqualTo(15);
			assertThat(blackScore.getScoreMultipliedBy10()).isEqualTo(15);
		}
	}

	@Test
	@DisplayName("백 팀에 킹(0), 퀸(9), 룩(5), 비숍(3), 나이트(2.5)와 다른 열의 폰이 두 개(2)라면 점수의 10배는 215다")
	void addMultiplePiecesScoreTest() {
		Map<Position, Piece> board = Map.of(
			A4, new King(Team.WHITE),
			B4, new Queen(Team.WHITE),
			C4, new Rook(Team.WHITE),
			D4, new Bishop(Team.WHITE),
			E4, new Knight(Team.WHITE),
			F4, new Pawn(Team.WHITE),
			G4, new InitialPawn(Team.WHITE)
		);
		Score whiteScore = Score.of(Team.WHITE, board);
		Score blackScore = Score.of(Team.BLACK, board);

		assertThat(whiteScore.getScoreMultipliedBy10()).isEqualTo(215);
		assertThat(blackScore.getScoreMultipliedBy10()).isEqualTo(0);
	}
}
