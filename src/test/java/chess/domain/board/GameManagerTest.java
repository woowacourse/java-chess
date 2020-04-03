package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.GameManager;
import chess.domain.piece.Bishop;
import chess.domain.piece.BlankPieceFactory;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class GameManagerTest {
	private GameManager gameManager;
	private Pieces pieces;

	@BeforeEach
	void setUp() {
		pieces = new Pieces(Pieces.initPieces());
		gameManager = new GameManager(pieces);
	}

	@DisplayName("체스판을 생성하면 초기화 상태로 들어가있는지 테스트")
	@Test
	void initializeBoardTest() {

		assertThat(gameManager.getPiece("e1")).isInstanceOf(King.class);
		assertThat(gameManager.getPiece("d1")).isInstanceOf(Queen.class);
		assertThat(gameManager.getPiece("b1")).isInstanceOf(Knight.class);
		assertThat(gameManager.getPiece("g1")).isInstanceOf(Knight.class);
		assertThat(gameManager.getPiece("c1")).isInstanceOf(Bishop.class);
		assertThat(gameManager.getPiece("f1")).isInstanceOf(Bishop.class);
		assertThat(gameManager.getPiece("a1")).isInstanceOf(Rook.class);
		assertThat(gameManager.getPiece("h1")).isInstanceOf(Rook.class);

		for (File file : File.values()) {
			assertThat(gameManager.getPiece(file.getFile() + "2")).isInstanceOf(Pawn.class);
		}

		assertThat(gameManager.getPiece("e8")).isInstanceOf(King.class);
		assertThat(gameManager.getPiece("d8")).isInstanceOf(Queen.class);
		assertThat(gameManager.getPiece("b8")).isInstanceOf(Knight.class);
		assertThat(gameManager.getPiece("g8")).isInstanceOf(Knight.class);
		assertThat(gameManager.getPiece("c8")).isInstanceOf(Bishop.class);
		assertThat(gameManager.getPiece("f8")).isInstanceOf(Bishop.class);
		assertThat(gameManager.getPiece("a8")).isInstanceOf(Rook.class);
		assertThat(gameManager.getPiece("h8")).isInstanceOf(Rook.class);

		for (File file : File.values()) {
			assertThat(gameManager.getPiece(file.getFile() + "7")).isInstanceOf(Pawn.class);
		}
	}

	@DisplayName("폰이 대각선으로 상대방 말이 있을 경우, 먹으면서 이동하는 경우 테스트")
	@Test
	void pawnCatchDiagonalTest() {
		GameManager gameManager;
		Map<Position, Piece> pieces = new HashMap<>();
		BlankPieceFactory.create(pieces);
		Pieces board = new Pieces(pieces);

		Pawn whitePawn = new Pawn(Color.WHITE, "p");
		Pawn blackPawn = new Pawn(Color.BLACK, "P");

		board.addPiece(Position.of("d4"), whitePawn);
		board.addPiece(Position.of("e5"), blackPawn);

		gameManager = new GameManager(board);

		gameManager.moveFromTo(Position.of("d4"), Position.of("e5"));
		assertThat(gameManager.getPiece("e5")).isEqualTo(whitePawn);
		assertThat(gameManager.getPiece("d4").isBlank()).isTrue();
	}

	@DisplayName("해당 위치에 있는 piece가 원하는 target으로 갈 수 있는 List를 반환하는 것을 테스트")
	@Test
	void moveFromToTest() {
		gameManager.moveFromTo(Position.of("b1"), Position.of("a3"));
		assertThat(gameManager.getPiece("a3")).isInstanceOf(Knight.class);
		assertThat(gameManager.getPiece("b1").isBlank()).isTrue();
	}

	@DisplayName("source position 을 빈 칸으로 선택했 경우")
	@Test
	void validateEmptySourcePositionTest() {
		assertThatThrownBy(() -> {
			gameManager.moveFromTo(Position.of("b3"), Position.of("a4"));
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("빈 칸을 선택하셨습니다");
	}

	@DisplayName("source position 을 상대방 말로 선택했을 경우")
	@Test
	void validateOtherPieceSourcePositionTest() {
		assertThatThrownBy(() -> {
			gameManager.moveFromTo(Position.of("b8"), Position.of("a4"));
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("상대방의 말을 선택하셨습니다");
	}

	@DisplayName("target position 을 자신의 말로 선택했을 경우")
	@Test
	void validateSameColorTargetPositionTest() {
		assertThatThrownBy(() -> {
			gameManager.moveFromTo(Position.of("b1"), Position.of("d2"));
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("target에 자신의 말이 있습니다");
	}

	@DisplayName("target position 을 해당 말로 갈 수 없는 곳으 선택했을 경우")
	@Test
	void validateNotMovablePositionTest() {
		assertThatThrownBy(() -> {
			gameManager.moveFromTo(Position.of("b1"), Position.of("a4"));
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("갈 수 없는 곳을 선택하셨습니다");
	}

	@DisplayName("초기화 상태일 때 King이 안 죽었는지 확인하는 테스트")
	@Test
	void isKingDeadTest() {
		assertThat(gameManager.isKingDead()).isFalse();
	}
}
