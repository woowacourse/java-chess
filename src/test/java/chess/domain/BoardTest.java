package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

class BoardTest {

	@ParameterizedTest
	@CsvSource(value = {"1:5", "8:5"}, delimiter = ':')
	@DisplayName("체스판 좌표로 체스말을 찾는다.")
	void findPiece(int row, int column) {
		Board board = new Board();
		Optional<Piece> piece = board.findPiece(new Position(row, column));
		assertThat(piece.get()).isInstanceOf(King.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:3", "4:7"}, delimiter = ':')
	@DisplayName("체스판 좌표에 체스말이 없으면 예외가 발생한다.")
	void findSymbolAtException(int row, int column) {
		Board board = new Board();
		Optional<Piece> piece = board.findPiece(new Position(row, column));
		assertThat(piece.isEmpty()).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"4:2", "3:2"}, delimiter = ':')
	@DisplayName("기물이 없는 위치로 이동할 수 있다.")
	void movePieceNotCatch(int row, int column) {
		Map<Position, Piece> pieces = Map.of(new Position(2, 2), Pawn.createWhite());
		Board board = new Board(pieces);

		board.movePiece(new Position(2, 2), new Position(row, column));

		Optional<Piece> piece = board.findPiece(new Position(row, column));
		assertThat(piece.isPresent()).isTrue();
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
	@DisplayName("폰이갈 수 있는 대각선 범위에 적군이 있으면 갈 수 있다.")
	void movePieceToDifferentColor(int row, int column) {
		Map<Position, Piece> pieces = Map.of(
			new Position(row, column), Pawn.createBlack(),
			new Position(2, 2), Pawn.createWhite());
		Board board = new Board(pieces);

		board.movePiece(
			new Position(2, 2),
			new Position(row, column));

		Optional<Piece> piece = board.findPiece(new Position(row, column));
		Piece findPiece = piece.get();

		assertThat(findPiece.isSameColor(Pawn.createWhite())).isTrue();
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
}