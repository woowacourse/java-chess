package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.GameManager;
import chess.domain.board.Position;

class PawnTest {
	Map<Position, Piece> originPiece;
	Pieces pieces;
	GameManager gameManager;

	@BeforeEach
	void setUp() {
		originPiece = new HashMap<>();
		BlankPieceFactory.create(originPiece);
		pieces = new Pieces(originPiece);
		gameManager = new GameManager(pieces);
	}

	@DisplayName("폰이 처음으로 두 칸 갈 수 있는지 하는지 테스트")
	@Test
	void goTwoBlockTest() {
		Pawn pawn = new Pawn(Color.WHITE, "p");
		pieces.addPiece(Position.of("b2"), pawn);

		gameManager.moveFromTo(Position.of("b2"), Position.of("b4"));

		assertThat(pieces.getPieceByPosition(Position.of("b4"))).isEqualTo(pawn);
	}

	@DisplayName("폰이 한 칸을 갈 수 있는지 테스트")
	@Test
	void goOneBlockTest() {
		Pawn pawn = new Pawn(Color.WHITE, "p");
		pieces.addPiece(Position.of("b2"), pawn);

		gameManager.moveFromTo(Position.of("b2"), Position.of("b3"));

		assertThat(pieces.getPieceByPosition(Position.of("b3"))).isEqualTo(pawn);
	}

	@DisplayName("폰이 대각선으로 적군이 있을 때 먹을 수 있는지 테스트")
	@Test
	void eatOneBlockTest() {
		Pawn whitePawn = new Pawn(Color.WHITE, "p");
		Pawn blackPawn = new Pawn(Color.BLACK, "p");

		pieces.addPiece(Position.of("b2"), whitePawn);
		pieces.addPiece(Position.of("c3"), blackPawn);
		pieces.addPiece(Position.of("d2"), blackPawn);

		gameManager.moveFromTo(Position.of("b2"), Position.of("c3"));
		assertThat(pieces.getPieceByPosition(Position.of("c3"))).isEqualTo(whitePawn);
	}

	@DisplayName("이외 폰이 갈 수 없는 방향 테스트")
	@Test
	void goExceptionTest() {
		Pawn pawn = new Pawn(Color.WHITE, "p");
		pieces.addPiece(Position.of("b2"), pawn);

		assertThatThrownBy(() -> {
			gameManager.moveFromTo(Position.of("b2"), Position.of("b5"));
		}).isInstanceOf(IllegalArgumentException.class);
	}
}