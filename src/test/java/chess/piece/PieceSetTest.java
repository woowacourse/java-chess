package chess.piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.board.Location;
import chess.piece.type.Piece;

class PieceSetTest {
	@DisplayName("피스 세트 생성")
	@Test
	void makePieceSet() {
		PieceSet pieceSet = new PieceSet(true);
		Map<Location, Piece> expect = new HashMap<>();

		for (PieceType pieceType : PieceType.values()) {
			makeExpectMap(expect, pieceType);
		}

		assertThat(pieceSet.getPieceSet()).isEqualTo(expect);
	}

	private void makeExpectMap(Map<Location, Piece> expect, PieceType pieceType) {
		List<Location> initialLocation = pieceType.reverseInitialLocation();

		for (Location location : initialLocation) {
			expect.put(location, Piece.of(pieceType, true));
		}
	}
}