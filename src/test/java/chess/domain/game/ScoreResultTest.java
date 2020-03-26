package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.*;
import chess.domain.piece.pieces.Pieces;
import chess.domain.piece.pieces.TestPiecesFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreResultTest {
	@DisplayName("getScoreBy 체스판에 따른 점수 반환 테스트")
	@Test
	void getScoreBy_normal_test() {
		Pieces pieces = TestPiecesFactory.createBy(Arrays.asList(
				new Queen(Board.of("a1"), "q", Color.WHITE),
				new Rook(Board.of("b1"), "q", Color.WHITE),
				new Knight(Board.of("c1"), "q", Color.WHITE),
				new Bishop(Board.of("d1"), "q", Color.WHITE),
				new Queen(Board.of("a8"), "q", Color.BLACK),
				new Rook(Board.of("b8"), "q", Color.BLACK),
				new Knight(Board.of("c8"), "q", Color.BLACK),
				new King(Board.of("d8"), "k", Color.BLACK)
		));
		ScoreResult scoreResult = new ScoreResult(pieces.getPieces());

		assertThat(scoreResult.getScoreBy(Color.WHITE)).isEqualTo(19.5);
		assertThat(scoreResult.getScoreBy(Color.BLACK)).isEqualTo(16.5);
	}
}
