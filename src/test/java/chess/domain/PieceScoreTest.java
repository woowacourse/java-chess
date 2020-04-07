package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Pieces;

public class PieceScoreTest {
	@DisplayName("최종적으로 각 팀의 점수를 계산해 제대로 나오는지 확인하는 테스트")
	@Test
	void calculateTeamTest() {
		Pieces pieces = new Pieces(Pieces.initPieces());

		pieces.addPiece(Position.of("a3"), new Pawn(Color.WHITE, "p"));
		pieces.addPiece(Position.of("a4"), new Pawn(Color.WHITE, "p"));
		pieces.addPiece(Position.of("b6"), new Pawn(Color.BLACK, "P"));
		pieces.addPiece(Position.of("f6"), new Pawn(Color.BLACK, "P"));
		pieces.addPiece(Position.of("f5"), new Pawn(Color.BLACK, "P"));

		GameManager gameManager = new GameManager(pieces, Color.WHITE);

		double whiteScore = PieceScore.calculateByColor(gameManager, Color.WHITE);
		double blackScore = PieceScore.calculateByColor(gameManager, Color.BLACK);

		assertThat(whiteScore).isEqualTo(38.5);
		assertThat(blackScore).isEqualTo(38.5);
	}
}
