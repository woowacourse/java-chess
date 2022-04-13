package chess.domain;

import static org.assertj.core.api.Assertions.*;

import chess.domain.board.BoardInitializer;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.board.Board;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;

@SuppressWarnings("OptionalGetWithoutIsPresent")
class BoardTest {

	@ParameterizedTest
	@CsvSource(value = {"1:5", "8:5"}, delimiter = ':')
	@DisplayName("체스판 좌표로 체스말을 찾는다.")
	void findPiece(int row, int column) {
		Board board = new Board(BoardInitializer.generate());
		Piece piece = board.findPiece(new Position(row, column));
		assertThat(piece).isInstanceOf(King.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:3", "4:7"}, delimiter = ':')
	@DisplayName("체스판 좌표에 체스말이 없으면 예외가 발생한다.")
	void findSymbolAtException(int row, int column) {
		Board board = new Board(BoardInitializer.generate());
		assertThatThrownBy(() -> board.findPiece(new Position(row, column)))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"4:2", "3:2"}, delimiter = ':')
	@DisplayName("화이트폰은 기물이 없는 위치로 이동할 수 있다.")
	void moveWhitePawnNotCatch(int row, int column) {
		Map<Position, Piece> pieces = Map.of(new Position(2, 2), Pawn.createWhite());
		Board board = new Board(pieces);

		board.movePiece(new Position(2, 2), new Position(row, column));

		Piece findPiece = board.getPieces().get(new Position(row, column));
		assertThat(findPiece).isInstanceOf(Pawn.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:1", "3:3"}, delimiter = ':')
	@DisplayName("화이트폰은 적이 없는한 대각선으로 갈 수 없다.")
	void notMoveWhitePawnDiagonal(int row, int column) {
		Map<Position, Piece> pieces = Map.of(new Position(2, 2), Pawn.createWhite());
		Board board = new Board(pieces);

		assertThatThrownBy(() -> board.movePiece(new Position(2, 2), new Position(row, column)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:2", "4:2"}, delimiter = ':')
	@DisplayName("화이트폰은 적이 정면에 있는 경우 적을 잡을 수 없다.")
	void notMoveWhiteNorth(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Pawn.createBlack(),
			new Position(2, 2), Pawn.createWhite());
		Board board = new Board(pieces);

		assertThatThrownBy(() -> board.movePiece(new Position(2, 2), new Position(row, column)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"6:1", "6:3"}, delimiter = ':')
	@DisplayName("블랙폰은 적이 없는한 대각선으로 갈 수 없다.")
	void notMoveBlackPawnDiagonal(int row, int column) {
		Map<Position, Piece> pieces = Map.of(new Position(7, 2), Pawn.createBlack());
		Board board = new Board(pieces);

		assertThatThrownBy(() -> board.movePiece(new Position(7, 2), new Position(row, column)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"6:2", "5:2"}, delimiter = ':')
	@DisplayName("블랙폰은 기물이 없는 위치로 이동할 수 있다.")
	void moveBlackPawnNotCatch(int row, int column) {
		Map<Position, Piece> pieces = Map.of(new Position(7, 2), Pawn.createBlack());
		Board board = new Board(pieces);

		board.movePiece(new Position(7, 2), new Position(row, column));

		Piece findPiece = board.getPieces().get(new Position(row, column));
		assertThat(findPiece).isInstanceOf(Pawn.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:2", "4:2", "3:1", "3:3"}, delimiter = ':')
	@DisplayName("폰이갈 수 있는 범위에 아군이 있으면 갈 수 없다.")
	void movePieceToSameColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Pawn.createWhite(),
			new Position(2, 2), Pawn.createWhite());
		Board board = new Board(pieces);

		assertThatThrownBy(() ->
			board.movePiece(
				new Position(2, 2),
				new Position(row, column)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:1", "3:3"}, delimiter = ':')
	@DisplayName("화이트폰이 갈 수 있는 대각선 범위에 적군이 있으면 갈 수 있다.")
	void moveWhitePawnToDifferentColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Pawn.createBlack(),
			new Position(2, 2), Pawn.createWhite());
		Board board = new Board(pieces);

		board.movePiece(
			new Position(2, 2),
			new Position(row, column));

		Piece findPiece = board.getPieces().get(new Position(row, column));

		assertThat(findPiece.isSameColor(Pawn.createWhite())).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"6:1", "6:3"}, delimiter = ':')
	@DisplayName("블랙폰이 갈 수 있는 대각선 범위에 적군이 있으면 갈 수 있다.")
	void moveBlackPawnToDifferentColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Pawn.createWhite(),
			new Position(7, 2), Pawn.createBlack());
		Board board = new Board(pieces);

		board.movePiece(
			new Position(7, 2),
			new Position(row, column));

		Piece findPiece = board.getPieces().get(new Position(row, column));

		assertThat(findPiece.isSameColor(Pawn.createBlack())).isTrue();
	}

	@Test
	@DisplayName("폰은 중간에 말이 존재하는 경우 갈 수 없다")
	void movePieceBetweenPiece() {
		Map<Position, Piece> pieces = Map.of(
			new Position(3, 2), Pawn.createBlack(),
			new Position(2, 2), Pawn.createWhite());
		Board board = new Board(pieces);

		assertThatThrownBy(() ->
			board.movePiece(
				new Position(2, 2),
				new Position(4, 2)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:3", "5:5", "2:2", "1:1", "6:6", "7:7",
		"3:5", "2:6", "1:7", "5:3", "6:2", "7:1"}, delimiter = ':')
	@DisplayName("비숍이 갈 수 있는 위치에 아군이 있으면 갈 수 없다.")
	void moveBishopToSameColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Queen.createWhite(),
			new Position(4, 4), Bishop.createWhite());
		Board board = new Board(pieces);

		assertThatThrownBy(() ->
			board.movePiece(
				new Position(4, 4),
				new Position(row, column)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:3:1:1", "5:5:7:7", "2:2:1:1", "6:6:7:7",
		"3:5:1:7", "2:6:1:7", "5:3:7:1", "6:2:7:1"}, delimiter = ':')
	@DisplayName("비숍이 가려는 방향에 기물이 있으면 갈 수 없다.")
	void moveBishopException(int midRow, int midColumn, int toRow, int toColumn) {
		Map<Position, Piece> pieces = Map.of(
			new Position(toRow, toColumn), Queen.createWhite(),
			new Position(midRow, midColumn), Queen.createBlack(),
			new Position(4, 4), Bishop.createWhite());
		Board board = new Board(pieces);

		assertThatThrownBy(() ->
			board.movePiece(
				new Position(4, 4),
				new Position(toRow, toColumn)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:3", "5:5", "2:2", "1:1", "6:6", "7:7",
		"3:5", "2:6", "1:7", "5:3", "6:2", "7:1"}, delimiter = ':')
	@DisplayName("비숍이 갈 수 있는 위치에 적군이 있으면 먹는다.")
	void moveBishopToDifferentColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Queen.createBlack(),
			new Position(4, 4), Bishop.createWhite());
		Board board = new Board(pieces);

		board.movePiece(
			new Position(4, 4),
			new Position(row, column));

		Piece findPiece = board.getPieces().get(new Position(row, column));

		assertThat(findPiece).isInstanceOf(Bishop.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"6:5", "5:6", "3:6", "2:5", "2:3", "3:2", "5:2", "6:3"}, delimiter = ':')
	@DisplayName("나이트가 갈 수 있는 위치에 아군이 있으면 갈 수 없다.")
	void moveKnightToSameColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Queen.createWhite(),
			new Position(4, 4), Knight.createWhite());
		Board board = new Board(pieces);

		assertThatThrownBy(() ->
			board.movePiece(
				new Position(4, 4),
				new Position(row, column)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"6:5", "5:6", "3:6", "2:5", "2:3", "3:2", "5:2", "6:3"}, delimiter = ':')
	@DisplayName("나이트가 갈 수 있는 위치에 적군이 있으면 먹는다.")
	void moveKnightToDifferentColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Queen.createBlack(),
			new Position(4, 4), Knight.createWhite());
		Board board = new Board(pieces);

		board.movePiece(
			new Position(4, 4),
			new Position(row, column));

		Piece piece = board.getPieces().get(new Position(row, column));
		assertThat(piece).isInstanceOf(Knight.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:4", "2:4", "1:4", "5:4", "6:4", "7:4",
		"4:3", "4:2", "4:1", "4:5", "4:6", "4:7"}, delimiter = ':')
	@DisplayName("룩이 갈 수 있는 위치에 아군이 있으면 못간다.")
	void moveRookToSameColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Pawn.createWhite(),
			new Position(4, 4), Rook.createWhite());
		Board board = new Board(pieces);

		assertThatThrownBy(() ->
			board.movePiece(
				new Position(4, 4),
				new Position(row, column)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:4", "2:4", "1:4", "5:4", "6:4", "7:4",
		"4:3", "4:2", "4:1", "4:5", "4:6", "4:7"}, delimiter = ':')
	@DisplayName("룩이갈 수 있는 위치에 적군이 있으면 갈 수 있다.")
	void moveRookToDifferentColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Pawn.createBlack(),
			new Position(4, 4), Rook.createWhite());
		Board board = new Board(pieces);

		board.movePiece(new Position(4, 4),
			new Position(row, column));

		Piece piece = board.getPieces().get(new Position(row, column));
		assertThat(piece).isInstanceOf(Rook.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:4:1:4", "2:4:1:4", "5:4:7:4", "6:4:7:4",
		"4:3:4:1", "4:2:4:1", "4:5:4:7", "4:6:4:7"}, delimiter = ':')
	@DisplayName("룩이갈 수 있는 위치에 말이 있으면 갈 수 없다.")
	void moveRookToSameColor(int midRow, int midColumn, int toRow, int toColumn) {
		Map<Position, Piece> pieces = Map.of(
			new Position(toRow, toColumn), Pawn.createBlack(),
			new Position(midRow, midColumn), Pawn.createWhite(),
			new Position(4, 4), Rook.createWhite());
		Board board = new Board(pieces);

		assertThatThrownBy(() ->
			board.movePiece(
				new Position(4, 4),
				new Position(toRow, toColumn)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:4", "2:4", "1:4", "5:4", "6:4", "7:4",
		"4:3", "4:2", "4:1", "4:5", "4:6", "4:7",
		"3:3", "5:5", "2:2", "1:1", "6:6", "7:7",
		"3:5", "2:6", "1:7", "5:3", "6:2", "7:1"}, delimiter = ':')
	@DisplayName("퀸이 갈 수 있는 위치에 아군이 있으면 못간다.")
	void moveQueenToSameColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Pawn.createWhite(),
			new Position(4, 4), Queen.createWhite());
		Board board = new Board(pieces);

		assertThatThrownBy(() ->
			board.movePiece(
				new Position(4, 4),
				new Position(row, column)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:4", "2:4", "1:4", "5:4", "6:4", "7:4",
		"4:3", "4:2", "4:1", "4:5", "4:6", "4:7",
		"3:3", "5:5", "2:2", "1:1", "6:6", "7:7",
		"3:5", "2:6", "1:7", "5:3", "6:2", "7:1"}, delimiter = ':')
	@DisplayName("퀸이 갈 수 있는 위치에 적군이 있으면 먹는다")
	void moveQueenToDifferentColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Pawn.createBlack(),
			new Position(4, 4), Queen.createWhite());
		Board board = new Board(pieces);

		board.movePiece(
			new Position(4, 4),
			new Position(row, column));

		Piece piece = board.getPieces().get(new Position(row, column));
		assertThat(piece).isInstanceOf(Queen.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:4:1:4", "2:4:1:4", "5:4:7:4", "6:4:7:4",
		"4:3:4:1", "4:2:4:1", "4:5:4:7", "4:6:4:7",
		"3:3:1:1", "5:5:7:7", "2:2:1:1", "6:6:7:7",
		"3:5:1:7", "2:6:1:7", "5:3:7:1", "6:2:7:1"}, delimiter = ':')
	@DisplayName("룩이갈 수 있는 위치에 말이 있으면 갈 수 없다.")
	void moveQueenException(int midRow, int midColumn, int toRow, int toColumn) {
		Map<Position, Piece> pieces = Map.of(
			new Position(toRow, toColumn), Pawn.createBlack(),
			new Position(midRow, midColumn), Pawn.createWhite(),
			new Position(4, 4), Queen.createWhite());
		Board board = new Board(pieces);

		assertThatThrownBy(() ->
			board.movePiece(
				new Position(4, 4),
				new Position(toRow, toColumn)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:4", "4:5", "5:5", "5:4", "3:3", "4:3", "3:5", "5:3"}, delimiter = ':')
	@DisplayName("킹이 갈 수 있는 위치에 아군이 있으면 못간다.")
	void moveKingToSameColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Pawn.createWhite(),
			new Position(4, 4), King.createWhite());
		Board board = new Board(pieces);

		assertThatThrownBy(() ->
			board.movePiece(
				new Position(4, 4),
				new Position(row, column)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:4", "4:5", "5:5", "5:4", "3:3", "4:3", "3:5", "5:3"}, delimiter = ':')
	@DisplayName("킹이 갈 수 있는 위치에 적군이 있으면 먹는다")
	void moveKingToDifferentColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Pawn.createBlack(),
			new Position(4, 4), King.createWhite());
		Board board = new Board(pieces);

		board.movePiece(
			new Position(4, 4),
			new Position(row, column));
		Piece piece = board.getPieces().get(new Position(row, column));
		assertThat(piece).isInstanceOf(King.class);
	}
}