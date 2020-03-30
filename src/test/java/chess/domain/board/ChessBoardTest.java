package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class ChessBoardTest {
	private ChessBoard chessBoard;
	private Map<Position, Piece> pieces;

	@BeforeEach
	void setUp() {
		pieces = new HashMap<>();
		chessBoard = new ChessBoard(pieces);
	}

	@DisplayName("체스판을 생성하면 초기화 상태로 들어가있는지 테스트")
	@Test
	void initializeBoardTest() {

		assertThat(chessBoard.getPiece("e1")).isInstanceOf(King.class);
		assertThat(chessBoard.getPiece("d1")).isInstanceOf(Queen.class);
		assertThat(chessBoard.getPiece("b1")).isInstanceOf(Knight.class);
		assertThat(chessBoard.getPiece("g1")).isInstanceOf(Knight.class);
		assertThat(chessBoard.getPiece("c1")).isInstanceOf(Bishop.class);
		assertThat(chessBoard.getPiece("f1")).isInstanceOf(Bishop.class);
		assertThat(chessBoard.getPiece("a1")).isInstanceOf(Rook.class);
		assertThat(chessBoard.getPiece("h1")).isInstanceOf(Rook.class);

		for (File file : File.values()) {
			assertThat(chessBoard.getPiece(file.getFile() + "2")).isInstanceOf(Pawn.class);
		}

		assertThat(chessBoard.getPiece("e8")).isInstanceOf(King.class);
		assertThat(chessBoard.getPiece("d8")).isInstanceOf(Queen.class);
		assertThat(chessBoard.getPiece("b8")).isInstanceOf(Knight.class);
		assertThat(chessBoard.getPiece("g8")).isInstanceOf(Knight.class);
		assertThat(chessBoard.getPiece("c8")).isInstanceOf(Bishop.class);
		assertThat(chessBoard.getPiece("f8")).isInstanceOf(Bishop.class);
		assertThat(chessBoard.getPiece("a8")).isInstanceOf(Rook.class);
		assertThat(chessBoard.getPiece("h8")).isInstanceOf(Rook.class);

		for (File file : File.values()) {
			assertThat(chessBoard.getPiece(file.getFile() + "7")).isInstanceOf(Pawn.class);
		}
	}

	@DisplayName("갈 수 있는 위치들 안에 target이 들어있는지 확인하는 테스트")
	@Test
	void isMovableTest() {
		List<Position> positions = Arrays.asList(Position.of("a4"), Position.of("b2"), Position.of("d3"),
			Position.of("e1"));
		Position target = Position.of("a4");
		assertThat(chessBoard.isMovable(positions, target)).isTrue();
	}

	@DisplayName("폰이 대각선으로 상대방 말이 있을 경우, 먹으면서 이동하는 경우 테스트")
	@Test
	void pawnCatchDiagonalTest() {
		ChessBoard chessBoard;
		Map<Position, Piece> pieces;
		pieces = new HashMap<>();

		Pawn whitePawn = new Pawn(Color.WHITE, "p");
		Pawn blackPawn = new Pawn(Color.BLACK, "P");

		pieces.put(Position.of("d4"), whitePawn);
		pieces.put(Position.of("e5"), blackPawn);

		chessBoard = new ChessBoard(pieces);

		chessBoard.moveFromTo(Color.WHITE, Position.of("d4"), Position.of("e5"));
		assertThat(chessBoard.getPiece("e5")).isEqualTo(whitePawn);
		assertThat(chessBoard.getPiece("d4")).isNull();
	}

	@DisplayName("해당 위치에 있는 piece가 원하는 target으로 갈 수 있는 List를 반환하는 것을 테스트")
	@Test
	void moveFromToTest() {
		chessBoard.moveFromTo(Color.WHITE, Position.of("b1"), Position.of("a3"));
		assertThat(chessBoard.getPiece("a3")).isInstanceOf(Knight.class);
		assertThat(chessBoard.getPiece("b1")).isNull();
	}

	@DisplayName("source position 을 빈 칸으로 선택했 경우")
	@Test
	void validateEmptySourcePositionTest() {
		assertThatThrownBy(() -> {
			chessBoard.moveFromTo(Color.WHITE, Position.of("b3"), Position.of("a4"));
		}).isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("빈 칸을 선택하셨습니다");
	}

	@DisplayName("source position 을 상대방 말로 선택했을 경우")
	@Test
	void validateOtherPieceSourcePositionTest() {
		assertThatThrownBy(() -> {
			chessBoard.moveFromTo(Color.BLACK, Position.of("b1"), Position.of("a4"));
		}).isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("상대방의 말을 선택하셨습니다");
	}

	@DisplayName("target position 을 자신의 말로 선택했을 경우")
	@Test
	void validateSameColorTargetPositionTest() {
		assertThatThrownBy(() -> {
			chessBoard.moveFromTo(Color.WHITE, Position.of("b1"), Position.of("d2"));
		}).isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("target에 자신의 말이 있습니다");
	}

	@DisplayName("target position 을 해당 말로 갈 수 없는 곳으 선택했을 경우")
	@Test
	void validateNotMovablePositionTest() {
		assertThatThrownBy(() -> {
			chessBoard.moveFromTo(Color.WHITE, Position.of("b1"), Position.of("a4"));
		}).isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("갈 수 없는 곳을 선택하셨습니다");
	}
}
