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
import chess.domain.command.Command;

class KingTest {
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

	@DisplayName("King 클래스가 PieceScore에 있는 Enum King 동일한지 테스트")
	@Test
	void isSameNameTest() {
		King king = new King(Color.WHITE, "k");
		assertThat(king.isSameName(PieceScore.KING)).isTrue();
	}

	@DisplayName("왕이 대각선으로 가는지 테스트")
	@Test
	void goBlockTest() {
		King king = new King(Color.WHITE, "k");
		pieces.addPiece(Position.of("b2"), king);

		gameManager.moveFromTo(new Command("move b2 c3"));

		assertThat(pieces.getPieceByPosition(Position.of("c3"))).isEqualTo(king);
	}

	@DisplayName("왕이 못 가는 곳 테스트")
	@Test
	void goExceptionTest() {
		King king = new King(Color.WHITE, "k");
		pieces.addPiece(Position.of("b2"), king);

		assertThatThrownBy(() -> {
			gameManager.moveFromTo(new Command("move b2 h2"));
		}).isInstanceOf(IllegalArgumentException.class);
	}
}