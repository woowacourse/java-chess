package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChessStatusTest {
	ChessStatus chessStatus;

	@Test
	@DisplayName("체스판 점수 계산")
	void calculateScore() {
		chessStatus = ChessBoardFactory.create().createStatus();
		assertThat(chessStatus.calculateScore(Side.WHITE)).isEqualTo(38);
	}

	@Test
	@DisplayName("체스판 점수 계산 - 폰이 같은 줄에 있을 때")
	void calculateScoreWithPawnsInSameColumn() {
		List<Piece> pieces = Arrays.asList(new Pawn(Side.WHITE, Position.of("c2")),
				new Pawn(Side.WHITE, Position.of("c3")),
				new Pawn(Side.WHITE, Position.of("c4")));
		chessStatus = new ChessStatus(pieces);
		assertThat(chessStatus.calculateScore(Side.WHITE)).isEqualTo(1.5);
	}
}