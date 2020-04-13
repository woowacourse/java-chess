package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.GameManager;
import chess.domain.PieceScore;
import chess.domain.board.Position;

class QueenTest {
	Map<Position, Piece> originPiece;
	Pieces pieces;
	GameManager gameManager;

	@BeforeEach
	void setUp() {
		originPiece = new HashMap<>();
		PiecesFactory.createBlankPieces(originPiece);
		pieces = new Pieces(originPiece);
		gameManager = new GameManager(pieces, Color.WHITE);
	}

	@DisplayName("Queen 클래스가 PieceScore에 있는 Enum Queen 동일한지 테스트")
	@Test
	void isSameNameTest() {
		Queen queen = new Queen(Color.WHITE, "q");
		assertThat(queen.isSameName(PieceScore.QUEEN)).isTrue();
	}

	@DisplayName("Queen이 대각선으로 가는지 테스트")
	@Test
	void goDiagonalTest() {
		Queen queen = new Queen(Color.WHITE, "q");
		pieces.addPiece(Position.of("b2"), queen);

		gameManager.moveFromTo(Position.of("b2"), Position.of("h8"));

		assertThat(pieces.getPieceByPosition(Position.of("h8"))).isEqualTo(queen);
	}

	@DisplayName("Queen이 선으로 가는지 테스트")
	@Test
	void goDirectTest() {
		Queen queen = new Queen(Color.WHITE, "q");
		pieces.addPiece(Position.of("b2"), queen);

		gameManager.moveFromTo(Position.of("b2"), Position.of("h2"));

		assertThat(pieces.getPieceByPosition(Position.of("h2"))).isEqualTo(queen);
	}

	@DisplayName("Queen이 못 가는 곳 테스트")
	@Test
	void goExceptionTest() {
		Queen queen = new Queen(Color.WHITE, "q");
		pieces.addPiece(Position.of("b2"), queen);

		assertThatThrownBy(() -> {
			gameManager.moveFromTo(Position.of("b2"), Position.of("d3"));
		}).isInstanceOf(IllegalArgumentException.class);
	}
}