package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.piece.King;
import chess.domain.piece.Piece;

class BoardTest {

	@ParameterizedTest
	@CsvSource(value = {"1:5", "8:5"}, delimiter = ':')
	@DisplayName("체스판 좌표로 체스말을 찾는다.")
	void findPiece(int row, int column) {
		Board board = new Board();
		Piece piece = board.findPiece(new Position(row, column));
		assertThat(piece).isInstanceOf(King.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:3", "4:7"}, delimiter = ':')
	@DisplayName("체스판 좌표에 체스말이 없으면 예외가 발생한다.")
	void findSymbolAtException(int row, int column) {
		Board board = new Board();
		assertThatThrownBy(() -> board.findPiece(new Position(row, column)))
			.isInstanceOf(IllegalArgumentException.class);
	}
}