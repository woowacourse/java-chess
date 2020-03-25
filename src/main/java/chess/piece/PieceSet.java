package chess.piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import chess.board.Location;

public class PieceSet {
	private final boolean isBlack;
	private final Map<Location, Piece> pieceSet;

	public PieceSet(boolean isBlack) {
		this.isBlack = isBlack;
		this.pieceSet = new HashMap<>();
		makePieceSet();
	}

	// 팀별 위치, 피스를 갖는 체스세트를 만든다.
	private void makePieceSet() {
		if (isBlack) {
			for (PieceType pieceType : PieceType.values()) {
				Queue<Location> locations = new LinkedList<>(pieceType.reverseInitialLocation());
				putPieceLocations(pieceType, locations);
			}
			return;
		}

		for (PieceType pieceType : PieceType.values()) {
			Queue<Location> locations = new LinkedList<>(pieceType.getInitialLocation());
			putPieceLocations(pieceType, locations);
		}
	}

	private void putPieceLocations(PieceType pieceType, Queue<Location> locations) {
		for (Piece piece : find(pieceType)) {
			pieceSet.put(locations.poll(), piece);
		}
	}

	private List<Piece> find(PieceType pieceType) {
		return makePieces().stream()
			.filter(piece -> piece.is(pieceType))
			.collect(Collectors.toList());
	}

	private List<Piece> makePieces() {
		List<Piece> pieces = new ArrayList<>();

		for (PieceType pieceType : PieceType.values()) {
			addPieces(pieces, pieceType);
		}
		return Collections.unmodifiableList(pieces);
	}

	private void addPieces(List<Piece> pieces, PieceType pieceType) {
		for (int i = 0; i < pieceType.getSize(); i++) {
			pieces.add(Piece.of(pieceType, isBlack));
		}
	}

	public Map<Location, Piece> getPieceSet() {
		return pieceSet;
	}

	@Override
	public String toString() {
		return "PieceSet{" +
			"isBlack=" + isBlack +
			", pieceSet=" + pieceSet.values().stream().map(piece -> piece.toString()).collect(Collectors.joining(",")) +
			'}';
	}
}
