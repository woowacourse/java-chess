package chess.domain.chessGame;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardInitializer;
import chess.domain.chessGame.gameState.BlackTurnState;
import chess.domain.chessGame.gameState.EndState;
import chess.domain.chessGame.gameState.KingCaughtState;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.nonLeapablePieceType.Queen;
import chess.domain.chessPiece.pieceType.nonLeapablePieceType.pawn.WhitePawn;
import chess.domain.position.Position;

class ChessGameTest {

	@Test
	void from_ChessBoard_GenerateInstance() {
		assertThat(ChessGame.from(new ChessBoard(ChessBoardInitializer.create()))).isInstanceOf(ChessGame.class);
	}

	@ParameterizedTest
	@NullSource
	void from_NullChessBoard_ExceptionThrown(ChessBoard chessBoard) {
		assertThatThrownBy(() -> ChessGame.from(chessBoard))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 보드가 null입니다.");
	}

	private static Stream<Arguments> provideChessBoardAndMoveExpectedChessBoard() {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("b1"), new WhitePawn());

		ChessBoard chessBoard = new ChessBoard(initialChessBoard);
		ChessGame chessGame = ChessGame.from(chessBoard);

		Map<Position, ChessPiece> expectedChessBoard = new HashMap<>();
		expectedChessBoard.put(Position.of("b2"), new WhitePawn());
		ChessBoard expected = new ChessBoard(expectedChessBoard);

		return Stream.of(Arguments.of(chessGame, expected));
	}

	@ParameterizedTest
	@MethodSource("provideChessBoardAndMoveExpectedChessBoard")
	void move_ChessBoard_MoveChessPieceOnSourcePosition(ChessGame chessGame, ChessBoard expected) {
		ChessCommand chessCommand = ChessCommand.of(Arrays.asList("move", "b1", "b2"));
		chessGame.move(chessCommand);

		assertThat(chessGame).extracting("chessBoard").isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void move_NullChessBoard_ExceptionThrown(ChessCommand chessCommand) {
		ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));

		assertThatThrownBy(() -> chessGame.move(chessCommand))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 명령이 null입니다.");
	}

	@Test
	void move_NotOnSourcePosition_ExceptionThrown() {
		ChessCommand chessCommand = ChessCommand.of(Arrays.asList("move", "b1", "b2"));
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("b3"), new Queen(PieceColor.WHITE));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);
		ChessGame chessGame = ChessGame.from(chessBoard);

		assertThatThrownBy(() -> chessGame.move(chessCommand))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이동할 체스 피스가 존재하지 않습니다.");
	}

	@ParameterizedTest
	@MethodSource("provideChessBoardAndMoveExpectedChessBoard")
	void shiftGameStatusBy_NotKingOnTargetPosition_ShiftNextPieceColorState(ChessGame chessGame, ChessBoard expected) {
		ChessCommand chessCommand = ChessCommand.of(Arrays.asList("move", "b1", "b2"));
		chessGame.move(chessCommand);

		assertThat(chessGame).extracting("gameState").isInstanceOf(BlackTurnState.class);
	}

	@Test
	void shiftGameStatusBy_KingOnTargetPosition_ShiftKingCaughtState() {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("b1"), new King(PieceColor.WHITE));
		initialChessBoard.put(Position.of("b2"), new King(PieceColor.BLACK));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);
		ChessGame chessGame = ChessGame.from(chessBoard);
		ChessCommand chessCommand = ChessCommand.of(Arrays.asList("move", "b1", "b2"));
		chessGame.move(chessCommand);

		assertThat(chessGame).extracting("gameState").isInstanceOf(KingCaughtState.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"status,white", "status,black"})
	void status_ChessCommand_CalculateStatusResult(String commandType, String argument) {
		ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));

		assertThat(chessGame.status(ChessCommand.of(Arrays.asList(commandType, argument)))).isEqualTo(38.0);
	}

	@ParameterizedTest
	@NullSource
	void status_NullChessCommand_ExceptionThrown(ChessCommand chessCommand) {
		ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));

		assertThatThrownBy(() -> chessGame.status(chessCommand))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 명령이 null입니다.");
	}

	@Test
	void end_ChessCommandEnd_ShiftEndState() {
		ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));
		chessGame.end();

		assertThat(chessGame).extracting("gameState").isInstanceOf(EndState.class);
	}

	@Test
	void isEndState_EndState_ResultTrue() {
		ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));
		chessGame.end();

		assertThat(chessGame.isEndState()).isTrue();
	}

	@Test
	void isEndState_NotEndState_ResultFalse() {
		ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));

		assertThat(chessGame.isEndState()).isFalse();
	}

	@Test
	void isKingCaught_KingCaughtState_ReturnTrue() {
		Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("b1"), new King(PieceColor.WHITE));
		initialChessBoard.put(Position.of("b2"), new King(PieceColor.BLACK));
		ChessBoard chessBoard = new ChessBoard(initialChessBoard);
		ChessGame chessGame = ChessGame.from(chessBoard);
		ChessCommand chessCommand = ChessCommand.of(Arrays.asList("move", "b1", "b2"));
		chessGame.move(chessCommand);

		assertThat(chessGame.isKingCaught()).isTrue();
	}

	@Test
	void isKingCaught_NotKingCaughtState_ResultFalse() {
		ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));
		chessGame.end();

		assertThat(chessGame.isKingCaught()).isFalse();
	}

}