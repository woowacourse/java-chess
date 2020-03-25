package domain.board;

import java.util.List;

public class Board {
	private List<Rank> ranks;

	public Board(List<Rank> ranks) {
		this.ranks = ranks;
	}

	public List<Rank> getRanks() {
		return ranks;
	}
}
