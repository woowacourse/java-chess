package chess.domain.chessBoard;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.RuleStrategy.KingRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.QueenRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.RookRuleStrategy;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.Queen;
import chess.domain.chessPiece.pieceType.Rook;
import chess.domain.position.Position;

public class ChessBoardTest {

	@Test
	void ChessBoard_MapOfPositionAndChessPiece_GenerateInstance() {
		assertThat(new ChessBoard(ChessBoardInitializer.create())).isInstanceOf(ChessBoard.class);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void validate_EmptyMapOfPositionAndChessPiece_ExceptionThrown(Map<Position, ChessPiece> chessBoard) {
		assertThatThrownBy(() -> new ChessBoard(chessBoard))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 보드가 존재하지 않습니다.");
	}

	@Test
	void isKingOn_TargetPosition_ReturnTrue() {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("b1"), new King(PieceColor.WHITE, new InitialState(new KingRuleStrategy())));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);

		assertThat(chessBoard.isKingOn(Position.of("b1"))).isTrue();
	}

	@ParameterizedTest
	@NullSource
	void isKingOn_NullTargetPosition_ExceptionThrown(Position targetPosition) {
		assertThatThrownBy(() -> new ChessBoard(ChessBoardInitializer.create()).isKingOn(targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 위치가 null입니다.");
	}

	@Test
	void isChessPieceOn_Position_ReturnTrue() {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("b1"),
			new Queen(PieceColor.WHITE, new InitialState(new QueenRuleStrategy())));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);

		assertThat(chessBoard.isChessPieceOn(Position.of("b1"))).isTrue();
	}

	@ParameterizedTest
	@NullSource
	void isChessPieceOn_NullPosition_ExceptionThrown(Position targetPosition) {
		assertThatThrownBy(() -> new ChessBoard(ChessBoardInitializer.create()).isChessPieceOn(targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 위치가 null입니다.");
	}

	@Test
	void isLeapableChessPieceOn_ExistLeapableChessPiece_ReturnTrue() {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("c4"), new King(PieceColor.WHITE, new InitialState(new KingRuleStrategy())));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);

		assertThat(chessBoard.isLeapableChessPieceOn(Position.of("c4"))).isTrue();
	}

	@Test
	void isLeapableChessPieceOn_NotLeapableChessPiece_ReturnFalse() {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("c4"), new Rook(PieceColor.WHITE, new InitialState(new RookRuleStrategy())));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);

		assertThat(chessBoard.isLeapableChessPieceOn(Position.of("c4"))).isFalse();
	}

	@ParameterizedTest
	@NullSource
	void isLeapableChessPieceOn_NullPosition_ExceptionThrown(Position position) {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("c4"), new Rook(PieceColor.WHITE, new InitialState(new RookRuleStrategy())));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);

		assertThatThrownBy(() -> chessBoard.isLeapableChessPieceOn(position))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 위치가 null입니다.");
	}

	@Test
	void checkChessPieceExistInRoute_InvalidMoveDirection_ExceptionThrown() {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("c4"), new King(PieceColor.WHITE, new InitialState(new KingRuleStrategy())));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);

		assertThatThrownBy(() -> chessBoard.checkChessPieceExistInRoute(Position.of("b3"), Position.of("c8")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 피스가 이동할 수 없는 위치를 입력하였습니다.");
	}

	@Test
	void checkChessPieceExistInRoute_ChessPieceExistInRoute_ExceptionThrown() {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("c4"), new King(PieceColor.WHITE, new InitialState(new KingRuleStrategy())));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);

		assertThatThrownBy(() -> chessBoard.checkChessPieceExistInRoute(Position.of("b3"), Position.of("d5")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 피스의 이동 경로에 다른 체스 피스가 존재합니다.");
	}

	@ParameterizedTest
	@NullSource
	void validate_NullSourcePosition_ExceptionThrown(Position sourcePosition) {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("c4"), new King(PieceColor.WHITE, new InitialState(new KingRuleStrategy())));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);

		assertThatThrownBy(() -> chessBoard.checkChessPieceExistInRoute(sourcePosition, Position.of("d5")))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("소스 위치가 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void validate_NullTargetPosition_ExceptionThrown(Position targetPosition) {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("c4"), new King(PieceColor.WHITE, new InitialState(new KingRuleStrategy())));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);

		assertThatThrownBy(() -> chessBoard.checkChessPieceExistInRoute(Position.of("d5"), targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("타겟 위치가 null입니다.");
	}

	@Test
	void checkCanMove_CanNotMovableChessPiece_ExceptionThrown() {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("c4"), new Rook(PieceColor.WHITE, new InitialState(new RookRuleStrategy())));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);

		assertThatThrownBy(() -> chessBoard.checkCanMove(Position.of("c4"), Position.of("b3")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 피스가 이동할 수 없습니다.");
	}

	@Test
	void checkCanCatch_SamePieceColor_ExceptionThrown() {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("c4"), new Rook(PieceColor.WHITE, new InitialState(new RookRuleStrategy())));
		initialChessBoard.put(Position.of("c6"),
			new Queen(PieceColor.WHITE, new InitialState(new QueenRuleStrategy())));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);

		assertThatThrownBy(() -> chessBoard.checkCanCatch(Position.of("c4"), Position.of("c6")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 피스를 잡을 수 없습니다.");
	}

	@Test
	void checkCanCatch_CanNotCatchTargetPosition_ExceptionThrown() {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("c4"), new Rook(PieceColor.WHITE, new InitialState(new RookRuleStrategy())));
		initialChessBoard.put(Position.of("d5"),
			new Queen(PieceColor.BLACK, new InitialState(new QueenRuleStrategy())));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);

		assertThatThrownBy(() -> chessBoard.checkCanCatch(Position.of("c4"), Position.of("d5")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 피스를 잡을 수 없습니다.");
	}

}
