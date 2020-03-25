package chess.domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
	private static final int SIZE = 8;

	private List<Rank> ranks;

	public Board(List<Rank> ranks) {
		validate(ranks);
		this.ranks = new ArrayList<>(ranks);
	}

	private void validate(List<Rank> ranks) {
		if (ranks.size() != SIZE) {
			throw new IllegalArgumentException("체스판은 8개 랭크로 이루어져야 합니다.");
		}
	}
}
