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
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.PieceType;
import chess.domain.position.Position;

class ChessGameTest {

	private static final String MOVE_COMMAND = "move";

	private static Stream<Arguments> provideChessBoardAndMoveExpectedChessBoard() {
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("b1"), new ChessPiece(PieceType.WHITE_PAWN));

		final ChessBoard chessBoard = new ChessBoard(value);
		final ChessGame chessGame = ChessGame.from(chessBoard);

		final Map<Position, ChessPiece> expectedChessBoard = new HashMap<>();
		expectedChessBoard.put(Position.of("b2"), new ChessPiece(PieceType.WHITE_PAWN));
		final ChessBoard expected = new ChessBoard(expectedChessBoard);

		return Stream.of(Arguments.of(chessGame, expected));
	}

	@Test
	void from_ChessBoard_GenerateInstance() {
		assertThat(ChessGame.from(new ChessBoard(ChessBoardInitializer.create()))).isInstanceOf(ChessGame.class);
	}

	@ParameterizedTest
	@NullSource
	void from_NullChessBoard_ExceptionThrown(final ChessBoard chessBoard) {
		assertThatThrownBy(() -> ChessGame.from(chessBoard))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 보드가 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void move_NullChessBoard_ExceptionThrown(final ChessCommand chessCommand) {
		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));

		assertThatThrownBy(() -> chessGame.move(chessCommand))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 명령이 null입니다.");
	}

	@ParameterizedTest
	@MethodSource("provideChessBoardAndMoveExpectedChessBoard")
	void move_ChessBoard_MoveChessPieceOnSourcePosition(final ChessGame chessGame, final ChessBoard expected) {
		final ChessCommand chessCommand = ChessCommand.of(Arrays.asList(MOVE_COMMAND, "b1", "b2"));
		chessGame.move(chessCommand);

		assertThat(chessGame).extracting("chessBoard").isEqualTo(expected);
	}

	@Test
	void checkChessPieceExistOn_NotOnSourcePosition_ExceptionThrown() {
		final ChessCommand chessCommand = ChessCommand.of(Arrays.asList(MOVE_COMMAND, "b1", "b2"));
		final Map<Position, ChessPiece> value = new HashMap<>();
		value.put(Position.of("b3"), new ChessPiece(PieceType.WHITE_QUEEN));
		final ChessBoard chessBoard = new ChessBoard(value);
		final ChessGame chessGame = ChessGame.from(chessBoard);

		assertThatThrownBy(() -> chessGame.move(chessCommand))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이동할 체스 피스가 존재하지 않습니다.");
	}

	@Test
	void checkCorrectChessTurn_NotCorrectPieceColorSourcePosition_ExceptionThrown() {
		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));
		final ChessCommand chessCommand = ChessCommand.of(Arrays.asList(MOVE_COMMAND, "b7", "b5"));

		assertThatThrownBy(() -> chessGame.move(chessCommand))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("순서에 맞지 않은 말을 이동하였습니다.");
	}

	@ParameterizedTest
	@MethodSource("provideChessBoardAndMoveExpectedChessBoard")
	void shiftGameStatusBy_NotKingOnTargetPosition_ShiftNextPieceColorState(final ChessGame chessGame,
		final ChessBoard expected) {
		final ChessCommand chessCommand = ChessCommand.of(Arrays.asList(MOVE_COMMAND, "b1", "b2"));
		chessGame.move(chessCommand);

		assertThat(chessGame).extracting("gameState").isInstanceOf(BlackTurnState.class);
	}

	@Test
	void shiftGameStatusBy_KingOnTargetPosition_ShiftKingCaughtState() {
		final Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("b1"), new ChessPiece(PieceType.WHITE_KING));
		initialChessBoard.put(Position.of("b2"), new ChessPiece(PieceType.BLACK_KING));
		final ChessBoard chessBoard = new ChessBoard(initialChessBoard);
		final ChessGame chessGame = ChessGame.from(chessBoard);
		final ChessCommand chessCommand = ChessCommand.of(Arrays.asList(MOVE_COMMAND, "b1", "b2"));
		chessGame.move(chessCommand);

		assertThat(chessGame).extracting("gameState").isInstanceOf(KingCaughtState.class);
	}

	@ParameterizedTest
	@NullSource
	void status_NullChessCommand_ExceptionThrown(final ChessCommand chessCommand) {
		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));

		assertThatThrownBy(() -> chessGame.status(chessCommand))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 명령이 null입니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"status,white", "status,black"})
	void status_ChessCommand_CalculateStatusResult(final String commandType, final String argument) {
		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));

		assertThat(chessGame.status(ChessCommand.of(Arrays.asList(commandType, argument)))).isEqualTo(38.0);
	}

	@Test
	void end_ChessCommandEnd_ShiftEndState() {
		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));
		chessGame.end();

		assertThat(chessGame).extracting("gameState").isInstanceOf(EndState.class);
	}

	@Test
	void isEndState_EndState_ResultTrue() {
		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));
		chessGame.end();

		assertThat(chessGame.isEndState()).isTrue();
	}

	@Test
	void isEndState_NotEndState_ResultFalse() {
		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));

		assertThat(chessGame.isEndState()).isFalse();
	}

	@Test
	void isKingCaught_KingCaughtState_ReturnTrue() {
		final Map<Position, ChessPiece> initialChessBoard = new HashMap<>();
		initialChessBoard.put(Position.of("b1"), new ChessPiece(PieceType.WHITE_KING));
		initialChessBoard.put(Position.of("b2"), new ChessPiece(PieceType.BLACK_KING));
		final ChessBoard chessBoard = new ChessBoard(initialChessBoard);
		final ChessGame chessGame = ChessGame.from(chessBoard);
		final ChessCommand chessCommand = ChessCommand.of(Arrays.asList(MOVE_COMMAND, "b1", "b2"));
		chessGame.move(chessCommand);

		assertThat(chessGame.isKingCaught()).isTrue();
	}

	@Test
	void isKingCaught_NotKingCaughtState_ResultFalse() {
		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));

		assertThat(chessGame.isKingCaught()).isFalse();
	}

	@Test
	void getCurrentPieceColor_InitGame_ReturnWhitePieceColor() {
		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));

		assertThat(chessGame.getCurrentPieceColor()).isEqualTo(PieceColor.WHITE);
	}

	@Test
	void getCurrentPieceColor_MoveOnce_ReturnBlackPieceColor() {
		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));
		chessGame.move(ChessCommand.of(Arrays.asList(MOVE_COMMAND, "b2", "b4")));

		assertThat(chessGame.getCurrentPieceColor()).isEqualTo(PieceColor.BLACK);
	}

}