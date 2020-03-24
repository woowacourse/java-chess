package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private final List<Rank> ranks;

	public Board() {
		List<Rank> ranks = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			ranks.add(new Rank());
		}
		this.ranks = ranks;
	}

	public List<Rank> getRanks() {
		return ranks;
	}
}
