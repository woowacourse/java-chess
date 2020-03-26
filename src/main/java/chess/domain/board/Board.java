package chess.domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class Board {
	private List<Rank> ranks;

	public Board() {
		this.ranks = new ArrayList<>();
	}

	public void initialize() {
		ranks.add(Rank.createPieces(0, Color.WHITE));
		ranks.add(Rank.createPawns(1, Color.WHITE));
		ranks.add(Rank.createBlanks(2));
		ranks.add(Rank.createBlanks(3));
		ranks.add(Rank.createBlanks(4));
		ranks.add(Rank.createBlanks(5));
		ranks.add(Rank.createPawns(6, Color.BLACK));
		ranks.add(Rank.createPieces(7, Color.BLACK));
	}

	private void updatePosition(Position source, Position target, Piece piece) {
		ranks.get(source.getY()).changePiece(source.getX(), new Blank(source));
		ranks.get(target.getY()).changePiece(target.getX(), piece);
	}

	private Piece findPiece(Position position) {
		return ranks.get(position.getY()).findPiece(position.getX());
	}

	public List<Rank> getRanks() {
		return Collections.unmodifiableList(ranks);
	}

	public List<Rank> getReverseRanks() {
		ListIterator<Rank> reverseIterator = ranks.listIterator(ranks.size());
		List<Rank> reverseRanks = new ArrayList<>();
		while (reverseIterator.hasPrevious()) {
			reverseRanks.add(reverseIterator.previous());
		}
		return Collections.unmodifiableList(reverseRanks);
	}
}
