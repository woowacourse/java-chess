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

class BishopTest {
	Map<Position, Piece> originPiece;
	Pieces pieces;
	GameManager gameManager;

	@BeforeEach
	void setUp() {
		originPiece = new HashMap<>();
		PiecesFactory.createBlankPieces(originPiece);
		pieces = new Pieces(originPiece);
		gameManager = new GameManager(pieces);
	}

	@DisplayName("Bishop 클래스가 PieceScore에 있는 Enum Bishop과 동일한지 테스트")
	@Test
	void isSameNameTest() {
		Bishop bishop = new Bishop(Color.WHITE, "b");
		assertThat(bishop.isSameName(PieceScore.BISHOP)).isTrue();
	}

	@DisplayName("비숍이 대각선으로 가는지 테스트")
	@Test
	void goTest() {
		Bishop bishop = new Bishop(Color.WHITE, "b");
		pieces.addPiece(Position.of("b2"), bishop);

		gameManager.moveFromTo(Position.of("b2"), Position.of("h8"));

		assertThat(pieces.getPieceByPosition(Position.of("h8"))).isEqualTo(bishop);
	}

	@DisplayName("비숍이 못 가는 곳 테스트")
	@Test
	void goExceptionTest() {
		Bishop bishop = new Bishop(Color.WHITE, "b");
		pieces.addPiece(Position.of("b2"), bishop);

		assertThatThrownBy(() -> {
			gameManager.moveFromTo(Position.of("b2"), Position.of("h2"));
		}).isInstanceOf(IllegalArgumentException.class);
	}
}