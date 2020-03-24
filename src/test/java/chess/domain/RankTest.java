package chess.domain;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class RankTest {

	@Test
	void rankCheck() {
		Rank rank = new Rank();
		for (Optional<Piece> piece : rank.getPieces()) {
			System.out.println(piece.map(Piece::toString).orElse("."));
		}
	}
}
