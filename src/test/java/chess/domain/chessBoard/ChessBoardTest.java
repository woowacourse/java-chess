package chess.domain.chessBoard;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessGame.ChessStatus;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.PieceType;
import chess.domain.position.Position;

public class ChessBoardTest {

	@ParameterizedTest
	@NullSource
	void ChessBoard_NullMapOfPositionAndChessPiece_ExceptionThrown(final Map<Position, ChessPiece> chessBoard) {
		assertThatThrownBy(() -> new ChessBoard(chessBoard))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 보드가 null입니다.");
	}

	@Test
	void ChessBoard_MapOfPositionAndChessPiece_GenerateInstance() {
		assertThat(new ChessBoard(ChessBoardInitializer.create())).isInstanceOf(ChessBoard.class);
	}

	@ParameterizedTest
	@NullSource
	void validate_NullPosition_ExceptionThrown(final Position position) {
		assertThatThrownBy(() -> new ChessBoard(ChessBoardInitializer.create()).isKingOn(position))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 위치가 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void validate_NullSourcePosition_ExceptionThrown(final Position sourcePosition) {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("c4"), new ChessPiece(PieceType.WHITE_KING));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThatThrownBy(() -> chessBoard.checkChessPieceExistInRoute(sourcePosition, Position.of("d5")))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("소스 위치가 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void validate_NullTargetPosition_ExceptionThrown(final Position targetPosition) {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("c4"), new ChessPiece(PieceType.WHITE_KING));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThatThrownBy(() -> chessBoard.checkChessPieceExistInRoute(Position.of("d5"), targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("타겟 위치가 null입니다.");
	}

	@Test
	void isKingOn_KingExistPosition_ReturnTrue() {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("b1"), new ChessPiece(PieceType.WHITE_KING));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThat(chessBoard.isKingOn(Position.of("b1"))).isTrue();
	}

	@Test
	void isKingOn_KingNotExistPosition_ReturnFalse() {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("b1"), new ChessPiece(PieceType.WHITE_QUEEN));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThat(chessBoard.isKingOn(Position.of("b1"))).isFalse();
	}

	@Test
	void isChessPieceOn_ChessPieceExistPosition_ReturnTrue() {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("b1"), new ChessPiece(PieceType.WHITE_QUEEN));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThat(chessBoard.isChessPieceOn(Position.of("b1"))).isTrue();
	}

	@Test
	void isChessPieceOn_ChessPieceNotExistPosition_ReturnFalse() {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("b2"), new ChessPiece(PieceType.WHITE_QUEEN));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThat(chessBoard.isChessPieceOn(Position.of("b1"))).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,true", "WHITE,false"})
	void isSamePieceColorOn_SameSourcePositionAndPieceColor_ReturnCompareResult(final PieceColor pieceColor,
		boolean expected) {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("c3"), new ChessPiece(PieceType.BLACK_KING));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThat(chessBoard.isSamePieceColorOn(Position.of("c3"), pieceColor)).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void isSamePieceColorOn_NullPieceColor_ExceptionThrown(final PieceColor pieceColor) {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("c3"), new ChessPiece(PieceType.BLACK_KING));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThatThrownBy(() -> chessBoard.isSamePieceColorOn(Position.of("b1"), pieceColor))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("피스 색상이 null입니다.");
	}

	@Test
	void isLeapableChessPieceOn_LeapableChessPiece_ReturnTrue() {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("c4"), new ChessPiece(PieceType.WHITE_KING));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThat(chessBoard.isLeapableChessPieceOn(Position.of("c4"))).isTrue();
	}

	@Test
	void isLeapableChessPieceOn_NonLeapableChessPiece_ReturnFalse() {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("c4"), new ChessPiece(PieceType.WHITE_ROOK));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThat(chessBoard.isLeapableChessPieceOn(Position.of("c4"))).isFalse();
	}

	@Test
	void checkChessPieceExistInRoute_InvalidMoveDirection_ExceptionThrown() {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("c4"), new ChessPiece(PieceType.WHITE_KING));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThatThrownBy(() -> chessBoard.checkChessPieceExistInRoute(Position.of("b3"), Position.of("c8")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 피스가 이동할 수 없는 위치를 입력하였습니다.");
	}

	@Test
	void checkChessPieceExistInRoute_ChessPieceExistInRoute_ExceptionThrown() {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("d5"), new ChessPiece(PieceType.WHITE_KING));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThatThrownBy(() -> chessBoard.checkChessPieceExistInRoute(Position.of("b3"), Position.of("e6")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 피스의 이동 경로에 다른 체스 피스가 존재합니다.");
	}

	@Test
	void checkCanMove_CanNotMovableChessPiece_ExceptionThrown() {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("c4"), new ChessPiece(PieceType.WHITE_ROOK));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThatThrownBy(() -> chessBoard.checkCanMove(Position.of("c4"), Position.of("b3")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 피스가 이동할 수 없습니다.");
	}

	@Test
	void checkCanCatch_SamePieceColor_ExceptionThrown() {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("c4"), new ChessPiece(PieceType.WHITE_ROOK));
		value.put(Position.of("c6"), new ChessPiece(PieceType.WHITE_QUEEN));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThatThrownBy(() -> chessBoard.checkCanCatch(Position.of("c4"), Position.of("c6")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 피스를 잡을 수 없습니다.");
	}

	@Test
	void checkCanCatch_CanNotCatchTargetPosition_ExceptionThrown() {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("c4"), new ChessPiece(PieceType.WHITE_ROOK));
		value.put(Position.of("d5"), new ChessPiece(PieceType.BLACK_QUEEN));
		final ChessBoard chessBoard = new ChessBoard(value);

		assertThatThrownBy(() -> chessBoard.checkCanCatch(Position.of("c4"), Position.of("d5")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 피스를 잡을 수 없습니다.");
	}

	@Test
	void moveChessPiece_SourcePositionAndTargetPosition_MoveChessPiece() {
		final ChessPiece chessPiece = new ChessPiece(PieceType.BLACK_ROOK);
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("c4"), chessPiece);
		final ChessBoard chessBoard = new ChessBoard(value);
		chessBoard.moveChessPiece(Position.of("c4"), Position.of("b2"));

		final Map<Position, ChessPiece> expected = new HashMap<>();
		expected.put(Position.of("b2"), chessPiece);
		assertThat(chessBoard).extracting("chessBoard").isEqualTo(expected);
	}

	@Test
	void calculateStatus_ChessBoard_CalculateStatusOfEachPieceColor() {
		final ChessBoard chessBoard = new ChessBoard(ChessBoardInitializer.create());

		final ChessStatus expected = ChessStatus.of(ChessBoardInitializer.create());
		assertThat(chessBoard.calculateStatus()).isEqualTo(expected);
	}

	@Test
	void getChessPieceNameOn_Position_ReturnChessNameOnPosition() {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("c4"), new ChessPiece(PieceType.WHITE_ROOK));
		final ChessBoard chessBoard = new ChessBoard(value);

		final String expected = "r";
		assertThat(chessBoard.getChessPieceNameOn(Position.of("c4"))).isEqualTo(expected);
	}

}
