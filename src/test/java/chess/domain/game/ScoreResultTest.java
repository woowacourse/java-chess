package chess.domain.game;

import chess.domain.position.PositionFactory;
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
				TestPieceFactory.createQueen(PositionFactory.of("a1"), Color.WHITE),
				TestPieceFactory.createRook(PositionFactory.of("b1"),Color.WHITE),
				TestPieceFactory.createKnight(PositionFactory.of("c1"), Color.WHITE),
				TestPieceFactory.createBishop(PositionFactory.of("d1"), Color.WHITE),
				TestPieceFactory.createQueen(PositionFactory.of("a8"), Color.BLACK),
				TestPieceFactory.createRook(PositionFactory.of("b8"), Color.BLACK),
				TestPieceFactory.createKnight(PositionFactory.of("c8"), Color.BLACK),
				TestPieceFactory.createKing(PositionFactory.of("d8"), Color.BLACK)
		));
		ScoreResult scoreResult = new ScoreResult(pieces.getPieces());

		assertThat(scoreResult.getScoreBy(Color.WHITE)).isEqualTo(19.5);
		assertThat(scoreResult.getScoreBy(Color.BLACK)).isEqualTo(16.5);
	}
}
